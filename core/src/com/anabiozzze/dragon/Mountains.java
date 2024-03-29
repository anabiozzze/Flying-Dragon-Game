package com.anabiozzze.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mountains {

    // вложенный класс гор и вулканов, задающий скорость их движения и положение на карте
    class Mount {
        Texture img;
        Vector2 position;
        float speed;
        Rectangle damagePlace; // зона внутри фигуры, отвечающая за солкновление с другой фигурой

        public Mount(Vector2 pos) {
            position = pos;
            speed = 2;
            damagePlace = new Rectangle(position.x, position.y, 70, 100);
    }

        // если картинка полностью ушла за кадр (-350), новая появляется перед кадром (800)
        public void update() {
            position.x -= speed;
            if (position.x < -350) {
                position.x = 800;
            }

            // обновляем координаты зоны внутри фигуры одновременно с самой фигурой
            if (img != null) {
                damagePlace.setPosition(position.x + img.getWidth()/2, position.y + img.getHeight()/2);
            }
        }
    }

    static Mount[] mounts; // массив для всех экземпляров гор
    int align; // отступ гор друг от друга при первом появлении на экане

    // в конструкторе заполняем массив горами и задаём им позиции
    public Mountains() {
        mounts = new Mount[11];
        align = 350;
        int firstPos = 500;

        for (int i = 0; i < mounts.length; i++) {
            mounts[i] = new Mount(new Vector2(firstPos,0));
            firstPos += align;
        }

        addPics();
    }

    // добавляет все картинки гор в массив картинок в случайном порядке
    public void addPics() {
        List imgs = new ArrayList();
        imgs.add(new Texture("mount1.png"));
        imgs.add(new Texture("mount2.png"));
        imgs.add(new Texture("mount3.png"));
        imgs.add(new Texture("mount4.png"));
        imgs.add(new Texture("mount5.png"));
        imgs.add(new Texture("mount6.png"));
        imgs.add(new Texture("mount7.png"));
        imgs.add(new Texture("mount8.png"));
        imgs.add(new Texture("mount9.png"));
        imgs.add(new Texture("mount10.png"));
        imgs.add(new Texture("mount11.png"));

        Random rnd = new Random();
        for (int i = 0; i < mounts.length; i++) {
            int j = rnd.nextInt(imgs.size());
            mounts[i].img = (Texture)imgs.remove(j);
        }
    }

    // метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
    public void render(SpriteBatch batch) {
        for (int i = 0; i < mounts.length; i++) {
            batch.draw(mounts[i].img, mounts[i].position.x, mounts[i].position.y);
        }
    }

    // прогоняем все экземпляры гор через обновление координат
    public void update() {
        for (int i = 0; i < mounts.length; i++) {
            mounts[i].update();
        }
    }

    // возвращет горы на стартовую позицию для начала новой игры
    public void recreate() {
        int firstPos = 500;

        for (int i = 0; i < mounts.length; i++) {
            mounts[i] = new Mount(new Vector2(firstPos,0));
            firstPos += align;
        }

        addPics();
    }
}
