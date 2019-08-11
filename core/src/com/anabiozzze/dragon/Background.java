package com.anabiozzze.dragon;

import  com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {


    // превращаем картинку в класс, чтобы быстро создавать два и более экземпляров
    class BackgroundPic {
        private Texture tx;
        private Vector2 pos;

        public BackgroundPic(Vector2 pos) {
            tx = new Texture("sky bg.png");
            this.pos = pos;
        }
    }

    private int speed; // скорость смещения фона при каждом обновлении
    private BackgroundPic[] bgs; // две одинаковые картинки, которые будут сменять друг друга, имитируя движение

    public Background() {
        speed = 4;
        bgs = new BackgroundPic[2];
        bgs[0] = new BackgroundPic(new Vector2(0,0));
        bgs[1] = new BackgroundPic(new Vector2(1200, 0));
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < bgs.length; i++) {
            batch.draw(bgs[i].tx, bgs[i].pos.x, bgs[i].pos.y);
        }
    }

    // метод меняет положение картинки на заданную величину при каждом вызове
    public void update() {

        // сдвигаем каждую из двух картинок на 4 пикселя при каждом запуске метода
        for (int i = 0; i < bgs.length; i++) {
            bgs[i].pos.x -= speed;
        }

        // возвращем картинки на исходную, если они полностью прошли экран
        if (bgs[0].pos.x< -1200) {
            bgs[0].pos.x = 0;
            bgs[1].pos.x = 1200;
        }
    }
}
