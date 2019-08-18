package com.anabiozzze.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clouds {

    // вложенный класс облаков, задающий скорость их движения и положение на карте
    class Cloud {
        Texture img;
        Vector2 position;
        float speed;
        int offset;
        Rectangle damagePlace; // зона внутри фигуры, отвечающая за солкновление с другой фигурой

        public Cloud(Vector2 pos) {
            position = pos;
            speed = 2;
            offset = new Random().nextInt(250);
            damagePlace = new Rectangle(position.x, position.y, 50, 50);

        }

        // если картинка полностью ушла за кадр (-350), новая появляется перед кадром (800)
        // offset - позволяет расставлять объекты по небу на разных высотах
        public void update() {
            position.x -= speed;
            if (position.x < -600) {
                position.x = 1000;
                position.y = 400-offset;
            }

            // обновляем координаты зоны внутри фигуры одновременно с самой фигурой
            if (img != null) {
                damagePlace.setPosition(position.x + img.getWidth()/2, position.y + img.getHeight()/2);
            }
        }
    }

    static Cloud[] clouds; // массив для всех экземпляров облаков
    int align; // отступ облаков друг от друга при первом появлении на экане

    // в конструкторе создаем массив облаков и определяем первую позицию и отступ
    public Clouds() {
        clouds = new Cloud[6];
        align = 600;
        int firstPos = 500;

        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(new Vector2(firstPos,400));
            firstPos += align;
        }

        addPics(); // наполняем картинками
    }

    // добавляет все картинки облаков в массив картинок в случайном порядке
    public void addPics() {
        List imgs = new ArrayList();
        imgs.add(new Texture("cloud1.png"));
        imgs.add(new Texture("cloud2.png"));
        imgs.add(new Texture("cloud3.png"));
        imgs.add(new Texture("cloud4.png"));
        imgs.add(new Texture("cloud5.png"));
        imgs.add(new Texture("cloud6.png"));

        Random rnd = new Random();
        for (int i = 0; i < clouds.length; i++) {
            int j = rnd.nextInt(imgs.size());
            clouds[i].img = (Texture)imgs.remove(j);
        }
    }

    // метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
    public void render(SpriteBatch batch) {
        for (int i = 0; i < clouds.length; i++) {
            batch.draw(clouds[i].img, clouds[i].position.x, clouds[i].position.y);
        }
    }

    // прогоняем все экземпляры облаков через обновление координат
    public void update() {
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].update();
        }
    }

    // возвращет облака на стартовую позицию для начала новой игры
    public void recreate() {
        int firstPos = 500;

        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(new Vector2(firstPos,400));
            firstPos += align;
        }

        addPics();
    }
}
