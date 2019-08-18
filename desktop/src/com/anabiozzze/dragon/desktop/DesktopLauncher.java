package com.anabiozzze.dragon.desktop;

import com.anabiozzze.dragon.DragonGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 500;
		new LwjglApplication(new DragonGame(), config);
	}
}
