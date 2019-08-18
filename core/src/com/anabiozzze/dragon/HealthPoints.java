package com.anabiozzze.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class HealthPoints {

    HealthPoint HP[];

    class HealthPoint {
        Vector2 position;
        Texture img;

        public HealthPoint() {
            img = new Texture("health.png");
            position = new Vector2(600, 470);
        }
    }

    public HealthPoints() {
        recreate();
    }

    // метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
    public void render(SpriteBatch batch) {
        for (int i = 0; i < HP.length; i++) {
            batch.draw(HP[i].img, HP[i].position.x, HP[i].position.y);
        }
    }

    // метод изменяет положение объекта в пространстве, при необходимости
    public void update() {
    }


    // возвращет иконки на стартовую позицию для начала новой игры без создания нового объекта
    public void recreate() {
        int x = 600; int y = 470;
        HP = new HealthPoint[3];
        for (int i = 0; i < HP.length; i++) {
            HP[i] = new HealthPoint();
            HP[i].position = new Vector2(x, y);
            x += 30;
        }
    }
}

