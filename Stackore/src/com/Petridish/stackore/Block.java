package com.Petridish.stackore;

import android.graphics.Point;

import com.Petridish.framework.implementation.AndroidGraphics;

public class Block {

	private boolean left, active;
	private int position, row, numOfBlocks, updateSpeed, timeSinceUpdate;
	private Point size, playSize, playCorner;

	public Block() {
		left = true;
		active = true;
		playSize = new Point(100, 100);
		playCorner = new Point(0,0);
		size = new Point(playSize.x / 5, playSize.y / 10);
		updateSpeed = 100;
		timeSinceUpdate = 0;
		numOfBlocks = 3;
		timeSinceUpdate = 0;
		position = 3;
		row = 1;
	}
	
	public void setPlaySize(int x, int y) {
		playSize = new Point(x, y);
		size = new Point(playSize.x / 5, playSize.y / 10);
	}
	
	public void setPlayCorner(int x, int y) {
		playCorner = new Point(x,y);
	}

	public void update(float deltaTime) {
		timeSinceUpdate += deltaTime;

		if (timeSinceUpdate >= updateSpeed) {
			move();
			timeSinceUpdate = 0;
		}
	}

	private void move() {
		if (left)
			position -= 1;
		else
			position += 1;

		if (position >= 4 || position <= 0)
			left = !left;
	}

	public void draw(AndroidGraphics g) {
		g.drawSizedImage(Assets.block, position * size.x + playCorner.x, playSize.y - row
				* size.y - playCorner.y, size.x, size.y);
		
		if (numOfBlocks >= 2)
			g.drawSizedImage(Assets.block, (position - 1) * size.x
					+ playCorner.x, playSize.y - row * size.y - playCorner.y, size.x, size.y);
		
		if (numOfBlocks == 3)
			g.drawSizedImage(Assets.block, (position + 1) * size.x
					+ playCorner.x, playSize.y - row * size.y - playCorner.y, size.x, size.y);
	}
}
