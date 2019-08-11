package com.anabiozzze.dragon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DragonGame extends ApplicationAdapter {
	// отрисовка объектов
	SpriteBatch batch;
	// класс фоновой картинки
	Background bg;


	// метод загружает в память все необходимые элементы и производит первичные рассчеты
	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
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
		batch.end();
	}

	// обновление происходящего на экране для движения картинок
	public void update() {
	    bg.update();
    }

	// метод очищает отрисованные элементы
	@Override
	public void dispose () {
		batch.dispose();
	}
}