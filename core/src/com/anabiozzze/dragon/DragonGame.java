package com.anabiozzze.dragon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DragonGame extends ApplicationAdapter {

	SpriteBatch batch; // отрисовка объектов
	Background bg;
	Dragon dragon;
	Mountains mountains;
	Clouds clouds;
	Birds birds;
	boolean youLose;
	Texture newGame;

	// метод загружает в память все необходимые элементы и производит первичные рассчеты
	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		dragon = new Dragon();
		mountains = new Mountains();
		clouds = new Clouds();
		birds = new Birds();
		youLose = false;
		newGame = new Texture("you_lose.png");
	}

	// отрисовка 60 раз в сек. того, что задано в методе
	@Override
	public void render () {
	    update();
		// задаем цвет заливки игрового поля + прозрачность
		Gdx.gl.glClearColor(1, 0, 0, 0.5f);
		// заливаем поле цветом
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		// указываем изображение и коордитаты для отрисовки
		bg.render(batch);

		// игрок проиграл - динозавтр перестаёт отрисовываться
		if (!youLose) {
			dragon.render(batch);
		} else {
			batch.draw(newGame, -90, -80);
		}
		mountains.render(batch);
		clouds.render(batch);
		birds.render(batch);
		batch.end();
	}

	// обновление происходящего на экране для движения картинок
	public void update() {
	    bg.update();
	    dragon.update();
	    mountains.update();
	    clouds.update();
	    birds.update();

		for (int i = 0; i < Mountains.mounts.length; i++) {
			if ((dragon.position.x+dragon.img.getWidth()) > (Mountains.mounts[i].position.x)
					&& (dragon.position.y<(Mountains.mounts[i].position.y+30))) {
				youLose = true;
			}

			if (dragon.position.y < 0) {
				youLose = true;
			}

			if (dragon.position.y > 480) {
				dragon.position.y -= 5;
			}

			if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && youLose) {
				recreate();
			}
		}
    }

	// метод очищает отрисованные элементы
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void recreate() {
		dragon.recreate();
		mountains.recreate();
		clouds.recreate();
		birds.recreate();
		youLose = false;
	}
}
