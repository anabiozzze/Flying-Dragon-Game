package com.anabiozzze.dragon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DragonGame extends ApplicationAdapter {

	SpriteBatch batch; // отрисовка объектов
	Background bg;
	Dragon dragon;
	Mountains mountains;
	Clouds clouds;
	Birds birds;
	Hotdogs hotdogs;
	Music sound;
	boolean youLose;
	Texture newGame;
	private int score;
	private String scoreString;
	private BitmapFont font;
	HealthPoints health;
	int healthCouner = 0;
	int timeCounter = 0;

	// метод загружает в память все необходимые элементы и производит первичные рассчеты
	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		dragon = new Dragon();
		mountains = new Mountains();
		clouds = new Clouds();
		birds = new Birds();
		hotdogs = new Hotdogs();
		sound =Gdx.audio.newMusic(Gdx.files.internal("8wiggle.mp3"));
		youLose = false;
		newGame = new Texture("you_lose.png");
		score = 0;
		scoreString = "SCORE: 0";
		font = new BitmapFont();
		health = new HealthPoints();
	}

	// метод 60 раз в секунду выполняет всё, что указано в его теле (в т.ч. отрисовку объектов)
	@Override
	public void render () {
	    update();
		// задаем цвет заливки игрового поля + прозрачность
		Gdx.gl.glClearColor(1, 0, 0, 0.5f);
		// заливаем поле цветом
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		// запускаем основной фон (небо)
		bg.render(batch);
		sound.play();

		// игрок проиграл - динозавр перестаёт отрисовываться
		if (!youLose) {
			dragon.render(batch);
		} else {
			// отрисовываем дракона
			batch.draw(newGame, -90, -80);
		}

		// аналогично с горами, облаками и птицами
		mountains.render(batch);
		clouds.render(batch);
		birds.render(batch);
		hotdogs.render(batch);

		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, scoreString, 700, 490);
		health.render(batch);

		batch.end();
	}

	// обновление происходящего на экране для движения картинок
	public void update() {
	    bg.update();
	    dragon.update();
	    mountains.update();
	    clouds.update();
	    birds.update();
		hotdogs.update();
		health.update();


		// дракон упал за нижнее поле - проигрыш
		if (dragon.position.y < 0) {
			youLose = true;
		}

		// дракон наполовину ушёл за вершнее поле - предел движения
		if (dragon.position.y > 480) {
			dragon.position.y -= 10;

		}

		// дракон коснулся середины облака - проигрыш
//		for (int i = 0; i < Clouds.clouds.length; i++) {
//			if (dragon.damagePlace !=null && Clouds.clouds[i].damagePlace != null) {
//				if (dragon.damagePlace.overlaps(Clouds.clouds[i].damagePlace) && !youLose) {
//					youLose = true;
//					System.out.println("TRUUUUUUE");
//				}
//			}
//		}

//		// дракон коснулся середины горы - проигрыш
//		for (int i = 0; i < Mountains.mounts.length; i++) {
//			if (dragon.damagePlace !=null && Mountains.mounts[i].damagePlace != null) {
//				if (dragon.damagePlace.overlaps(Mountains.mounts[i].damagePlace) && !youLose) {
//					youLose = true;
//					System.out.println("TRUUUUUUE");
//				}
//			}
//		}

		// дракон столкнулся с птицей - проигрыш
		for (int i = 0; i < Birds.birds.length; i++) {
			if (dragon.damagePlace !=null && Birds.birds[i].damagePlace != null) {
				if (dragon.damagePlace.overlaps(Birds.birds[i].damagePlace) && !youLose) {
					Birds.birds[i].damagePlace.set(0,0, 0, 0);
					Birds.birds[i].img = new Texture("empty.png");
					birds.isDead = true;
					health.HP[healthCouner].img = new Texture("empty.png");
					healthCouner++;

					if (healthCouner >= 3) {
						youLose = true;
					}
				}
				if (dragon.damagePlace.overlaps(Hotdogs.hotdogs[i].damagePlace) && !youLose) {
					Hotdogs.hotdogs[i].damagePlace.set(0,0, 0, 0);
					Hotdogs.hotdogs[i].img = new Texture("empty.png");
					Hotdogs.hotdogs[i].caught = true;
					score = score + 100;
					scoreString = "SCORE: " + score;

				}
			}
		}

		if (birds.getShot) {
			score = score + 100;
			scoreString = "SCORE: " + score;
			birds.getShot = false;
		}

		// в случае проигрыша, при нажатии пробела или левой кнопки мыши - начинается новая игра
		if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && youLose) {
			recreate();
		}
    }

	// метод очищает отрисованные элементы
	@Override
	public void dispose () {
		sound.dispose();
		batch.dispose();
	}

	// возвращает все элементы на стартовую позицию для начала новой игры
	public void recreate() {
		dragon.recreate();
		mountains.recreate();
		clouds.recreate();
		birds.recreate();
		health.recreate();
		score = 0;
		scoreString = "SCORE: " + score;
		youLose = false;
		healthCouner = 0;
	}
}
