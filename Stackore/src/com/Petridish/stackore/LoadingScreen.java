package com.Petridish.stackore;

import com.Petridish.framework.Game;
import com.Petridish.framework.Graphics;
import com.Petridish.framework.Graphics.ImageFormat;
import com.Petridish.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}

}
