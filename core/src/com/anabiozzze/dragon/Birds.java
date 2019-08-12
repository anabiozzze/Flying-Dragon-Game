package com.anabiozzze.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Birds {

    // вложенный класс птиц, задающий скорость их движения и положение на карте
    class Bird {
        Texture img;
        Vector2 position;
        float speed;
        int offset;

        // скорость в 2 раза выше скорости облаков
        public Bird(Vector2 pos) {
            position = pos;
            speed = 4.5f;
            offset = new Random().nextInt(150);
        }

        // если картинка полностью ушла за кадр (-350), новая появляется перед кадром (800)
        // offset - позволяет расставлять объекты по небу на разных высотах
        public void update() {
            position.x -= speed;
            if (position.x < -600) {
                position.x = 1000;
                position.y = 400-offset;
            }
        }
    }

    Bird[] birds;
    int align;

    // в конструкторе создаем массив птиц (пока одна) и определяем первую позицию и отступ
    public Birds() {
        birds = new Bird[1];
        align = 600;
        int firstPos = 500;

        for (int i = 0; i < birds.length; i++) {
            birds[i] = new Bird(new Vector2(firstPos,400));
            birds[i].img = new Texture("bird.png");
            firstPos += align;
        }
    }

    // обновляет картинку на экране 60 раз в секунду, рисуя движение
    public void render(SpriteBatch batch) {
        for (int i = 0; i < birds.length; i++) {
            batch.draw(birds[i].img, birds[i].position.x, birds[i].position.y);
        }
    }

    // прогоняем все экземпляры птиц через обновление координат
    public void update() {
        for (int i = 0; i < birds.length; i++) {
            birds[i].update();
        }
    }
}
