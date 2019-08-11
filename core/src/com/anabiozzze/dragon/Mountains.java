package com.anabiozzze.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mountains {
    class Mount {
        Texture img;
        Vector2 position;
        float speed;

        public Mount(Vector2 pos) {
            position = pos;
            speed = 2;
        }

        public void update() {
            position.x -= speed;
            if (position.x < -270) {
                position.x = 800;
            }
        }
    }

    Mount[] mounts;
    int align;

    public Mountains() {
        mounts = new Mount[5];
        align = 250;
        int firstPos = 500;

        for (int i = 0; i < mounts.length; i++) {
            mounts[i] = new Mount(new Vector2(firstPos,0));
            firstPos += 220;
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


        Random rnd = new Random();
        for (int i = 0; i < mounts.length; i++) {
            int j = rnd.nextInt(imgs.size());
            mounts[i].img = (Texture)imgs.remove(j);
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < mounts.length; i++) {
            batch.draw(mounts[i].img, mounts[i].position.x, mounts[i].position.y);
            batch.draw(mounts[i].img, mounts[i].position.x, mounts[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < mounts.length; i++) {
            mounts[i].update();
        }
    }
}
