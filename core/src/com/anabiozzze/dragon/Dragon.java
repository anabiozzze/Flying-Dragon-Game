package com.anabiozzze.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Dragon {

    Texture img;
    Vector2 position;
    float fall; // текущая скорость падения дракона
    float grav; // ускорение падения


    public Dragon() {
        img = new Texture("dragon.png");
        position = new Vector2(100, 280);
        fall = 0;
        grav = -0.7f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(img, position.x, position.y);
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || (Gdx.input.isButtonPressed(Input.Buttons.LEFT)))  {
            fall = 8;
        }

        fall += grav;
        position.y += fall;
    }

    public void recreate() {
        position = new Vector2(100, 280);
        fall = 0;
    }
}
