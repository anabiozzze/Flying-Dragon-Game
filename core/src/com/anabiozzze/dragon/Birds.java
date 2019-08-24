package com.anabiozzze.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Birds {

    static Bird[] birds;
    int align;
    static boolean getShot;
    static boolean isDead;

    // в конструкторе создаем массив птиц (пока одна, т.к. одн дизайн) и определяем первую позицию и отступ
    public Birds() {
        birds = new Bird[1];
        align = 600;
        recreate();
    }


    // вложенный класс птиц, задающий скорость их движения и положение на карте
    class Bird {
        Texture img;
        Vector2 position;
        float speed;
        int offset; // случайная переменная, которая будет изменять положение по вертикали новых птиц
        int counter;
        Rectangle damagePlace; // зона внутри фигуры, отвечающая за солкновление с другой фигурой
        Animation<TextureRegion> animation;
        float elapsed;

        // скорость в 2 раза выше скорости облаков
        public Bird(Vector2 pos) {
            getShot = false;
            isDead = false;
            position = pos;
            speed = 4.5f;
            counter = 0;
            offset = new Random().nextInt(150);
            damagePlace = new Rectangle(position.x, position.y, 50, 50);
            animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.NORMAL, Gdx.files.internal("explosion.gif").read());
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
            if (img != null && !isDead) {
                damagePlace.setPosition(position.x, position.y + img.getHeight());
            }

            // при регистрации попадания огннным шаром - переносим птицу в начало игровой зоны
            if (FireBlast.damagePlace.contains(damagePlace)) {
                getShot = true;
                isDead = true;
                damagePlace.setPosition(0,0);
            }

            counter++;

            // меняем положение крыльев дракона каждые 15 миллисекунд
            if (!isDead) {
                switch (counter) {
                    case 15 : img = new Texture("bird2.png"); break;
                    case 30 : img = new Texture("bird.png"); counter =0; break;
                }
            }

        }
    }

    // метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
    public void render(SpriteBatch batch) {
        for (int i = 0; i < birds.length; i++) {

            Bird temp = birds[i];

            batch.draw(temp.img, temp.position.x, temp.position.y);

            if (isDead) {
                temp.elapsed += Gdx.graphics.getDeltaTime();
                temp.img = new Texture("empty.png");
                batch.draw(temp.animation.getKeyFrame(temp.elapsed), temp.position.x, temp.position.y);
                temp.damagePlace.set(0,0, 0, 0);
            }
        }
    }

    // прогоняем все экземпляры птиц через обновление координат
    public void update() {
        for (int i = 0; i < birds.length; i++) {
            birds[i].update();
        }
    }

    // возвращет птицу на стартовую позицию для начала новой игры
    public void recreate() {
        int firstPos = 1000;

        for (int i = 0; i < birds.length; i++) {
            birds[i] = new Bird(new Vector2(firstPos,400));
            birds[i].offset = new Random().nextInt(150);
            birds[i].img = new Texture("bird.png");
            birds[i].position.x = 1100 + birds[i].offset;
            birds[i].position.y = 400-birds[i].offset;
            firstPos += align;
        }
    }
}
