package com.anabiozzze.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dragon {

    Texture img;
    static Vector2 position;
    float fall; // текущая скорость падения дракона
    float grav; // ускорение падения
    Rectangle damagePlace; // зона внутри фигуры, отвечающая за солкновление с другой фигурой

    FireBlast fire;


    // возвращет дракона на стартовую позицию для начала новой игры
    public Dragon() {

        img = new Texture("dragon.png");
        position = new Vector2(100, 280);
        fall = 0;
        grav = -0.7f;
        damagePlace = new Rectangle(position.x,
                position.y, 50, 50);

        fire = new FireBlast();
    }

    // метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
    public void render(SpriteBatch batch) {

        // по нажатию на правую кнопку запускаем огненный шар
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))  {
            fire.isStarted = true;
        }

        // отрисовываем огненный шар, как только он получил команду на запуск
        if (fire.isStarted) {
            fire.render(batch);
        }

        batch.draw(img, position.x, position.y);
    }

    // метод изменяет положение объекта в пространстве, при необходимости
    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || (Gdx.input.isButtonPressed(Input.Buttons.LEFT)))  {
            fall = 8;

//            // отображает координаты дракона при каждом клике мыши или нажатии на пробел
//            System.out.println(damagePlace.x + "   " + damagePlace.y + "   " +
//                    damagePlace.width + damagePlace.height + damagePlace.area());
        }

        fall += grav;
        position.y += fall;

        // обновляем координаты зоны внутри фигуры одновременно с самой фигурой
        damagePlace.setPosition(position.x + img.getWidth(), position.y + img.getHeight()/2);

        // обновляем состояние огненного шара (в т.ч. положение в пространстве)
        fire.update();
    }


    // возвращет дракона на стартовую позицию для начала новой игры
    public void recreate() {
        position = new Vector2(100, 280);
        fall = 0;
    }
}
