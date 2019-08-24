package com.anabiozzze.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FireBlast {

    Texture img;
    Vector2 position;
    int speed; // скорость движения пламени после запуска
    boolean isStarted;
    static Rectangle damagePlace; // зона внутри фигуры, отвечающая за солкновление с другой фигурой


    // ставит объект на стартовую позицию для начала новой игры
    public FireBlast() {
        isStarted = false;
        speed = 9;
        img = new Texture("fire.png");
        position = new Vector2(Dragon.position.x, Dragon.position.y);
        damagePlace = new Rectangle(position.x,
                position.y, 70, 80);
    }

    // метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
    public void render(SpriteBatch batch) {
        batch.draw(img, position.x, position.y);
    }


    // метод изменяет положение объекта в пространстве, при необходимости
    public void update() {

        // сверяемся с координатами дракона, чтобы шар появлялся строго из той же точки
        if (!isStarted) {
            position.y = Dragon.position.y;
            position.x = Dragon.position.x;
        }

        // шар начинает ускорятся сразу при появлении
        position.x += speed;

        // обновляем координаты зоны внутри фигуры одновременно с самой фигурой
        damagePlace.setPosition(position.x + img.getWidth()/2, position.y + img.getHeight()/2);

        // шар ушел за поле - создаем новый шар
        if (isStarted && position.x>700) {
            recreate();
        }
    }

    // возвращет огонь на стартовую позицию для следующего выстрела
    public void recreate() {
        isStarted = false;
        position = new Vector2(Dragon.position.x, Dragon.position.y);
        damagePlace = new Rectangle(position.x,
                position.y, 100, 120);
    }
}
