package com.anabiozzze.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clouds {
    class Cloud {
        Texture img;
        Vector2 position;
        float speed;
        int offset;

        public Cloud(Vector2 pos) {
            position = pos;
            speed = 2;
            offset = new Random().nextInt(150);
        }

        public void update() {
            position.x -= speed;
            if (position.x < -330) {
                position.x = 800;
            }
        }
    }

    Cloud[] clouds;
    int align;

    public Clouds() {
        clouds = new Cloud[3];
        align = 350;
        int firstPos = 500;

        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(new Vector2(firstPos,400));
            firstPos += align;
        }

        addPics();
    }

    // добавляет все картинки гор в массив картинок в случайном порядке
    public void addPics() {
        List imgs = new ArrayList();
        imgs.add(new Texture("cloud1.png"));
        imgs.add(new Texture("cloud2.png"));
        imgs.add(new Texture("cloud3.png"));

        Random rnd = new Random();
        for (int i = 0; i < clouds.length; i++) {
            int j = rnd.nextInt(imgs.size());
            clouds[i].img = (Texture)imgs.remove(j);
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < clouds.length; i++) {
            batch.draw(clouds[i].img, clouds[i].position.x, clouds[i].position.y);
            batch.draw(clouds[i].img, clouds[i].position.x, clouds[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].update();
        }
    }
}
