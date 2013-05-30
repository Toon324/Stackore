package com.Petridish.stackore;

import java.util.List;

import android.util.Log;

import com.Petridish.framework.Game;
import com.Petridish.framework.Input.TouchEvent;
import com.Petridish.framework.Screen;
import com.Petridish.framework.implementation.AndroidGraphics;

public class MainMenuScreen extends Screen {

	private Block block;
	
	public MainMenuScreen(Game game) {
		super(game);
		block = new Block();
		block.setPlaySize(Assets.getScreenWidth(), Assets.getScreenHeight());
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 0, 250, 250)) {
					// START GAME
					game.setScreen(new GameScreen(game));
				}

			}
		}
		
		block.update(deltaTime);
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		AndroidGraphics g = (AndroidGraphics) game.getGraphics();
		g.drawImage(Assets.background, 0, 0);
		//g.drawSizedImage(Assets.background, 0, 0, Assets.getScreenWidth(), Assets.getScreenHeight());
		block.draw(g);
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
		// Display "Exit Game?" Box

	}

}
