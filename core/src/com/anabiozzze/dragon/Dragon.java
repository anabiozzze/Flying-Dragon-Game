package com.anabiozzze.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dragon {

    Texture img;
    Vector2 position;
    float fall; // текущая скорость падения дракона
    float grav; // ускорение падения
    Rectangle damagePlace; // зона внутри фигуры, отвечающая за солкновление с другой фигурой


    // возвращет дракона на стартовую позицию для начала новой игры
    public Dragon() {
        img = new Texture("dragon.png");
        position = new Vector2(100, 280);
        fall = 0;
        grav = -0.7f;
        damagePlace = new Rectangle(position.x,
                position.y, 50, 50);
    }

    public void render(SpriteBatch batch) {
        batch.draw(img, position.x, position.y);
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || (Gdx.input.isButtonPressed(Input.Buttons.LEFT)))  {
            fall = 8;

            // отображает координаты динозавтр при каждом клике мыши или нажатии на пробел
            System.out.println(damagePlace.x + "   " + damagePlace.y + "   " +
                    damagePlace.width + damagePlace.height + damagePlace.area());
        }

        fall += grav;
        position.y += fall;

        // обновляем координаты зоны внутри фигуры одновременно с самой фигурой
        damagePlace.setPosition(position.x + img.getWidth()/2, position.y + img.getHeight()/2);
    }

    public void recreate() {
        position = new Vector2(100, 280);
        fall = 0;
    }
}
