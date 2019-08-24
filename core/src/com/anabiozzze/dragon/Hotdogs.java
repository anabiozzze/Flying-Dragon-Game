package com.anabiozzze.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Hotdogs {

    static Hotdog[] hotdogs;
    int align;

    // в конструкторе создаем массив птиц (пока одна, т.к. одн дизайн) и определяем первую позицию и отступ
    public Hotdogs() {
        hotdogs = new Hotdog[1];
        align = 1400;
        recreate();
    }


    // вложенный класс птиц, задающий скорость их движения и положение на карте
    class Hotdog {
        Texture img;
        Vector2 position;
        float speed;
        int offset; // случайная переменная, которая будет изменять положение по вертикали новых птиц
        int counter;
        Rectangle damagePlace; // зона внутри фигуры, отвечающая за солкновление с другой фигурой
        boolean caught;

        // скорость в 2 раза выше скорости облаков
        public Hotdog(Vector2 pos) {
            caught = false;
            position = pos;
            speed = 2f;
            counter = 0;
            offset = new Random().nextInt(300);
            damagePlace = new Rectangle(position.x, position.y, 50, 50);
        }


        // если картинка полностью ушла за кадр (-350), новая появляется перед кадром (800)
        // offset - позволяет расставлять объекты по небу на разных высотах
        public void update() {
            position.x -= speed;

            // следим за уходом птицы из игровой зоны, переносим её в начало игровой зоны
            if (position.x < -600) {
                recreate();
            }

            // обновляем координаты зоны внутри фигуры одновременно с самой фигурой
            if (img != null && !caught) {
                damagePlace.setPosition(position.x, position.y + img.getHeight());
            }

            counter++;

            // меняем положение крыльев дракона каждые 15 миллисекунд
            if (!caught) {
                switch (counter) {
                    case 15 : img = new Texture("hotdog.png"); break;
                    case 30 : img = new Texture("hotdog1.png"); counter =0; break;
                }
            }

        }
    }

    // метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
    public void render(SpriteBatch batch) {
        for (int i = 0; i < hotdogs.length; i++) {
            Hotdog temp = hotdogs[i];
            batch.draw(temp.img, temp.position.x, temp.position.y);
        }
    }

    // прогоняем все экземпляры птиц через обновление координат
    public void update() {
        for (int i = 0; i < hotdogs.length; i++) {
            hotdogs[i].update();
        }
    }

    // возвращет птицу на стартовую позицию для начала новой игры
    public void recreate() {
        int firstPos = 1600;

        for (int i = 0; i < hotdogs.length; i++) {
            hotdogs[i] = new Hotdog(new Vector2(firstPos,270));
            hotdogs[i].offset = new Random().nextInt(300);
            hotdogs[i].img = new Texture("hotdog.png");
            hotdogs[i].position.x = 1100 + hotdogs[i].offset;
            hotdogs[i].position.y = 400-hotdogs[i].offset;
            firstPos += align;
        }
    }
}
