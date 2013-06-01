package com.Petridish.stackore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class BlockController {

	private boolean left, active;
	private int position, row, numOfBlocks, updateSpeed, timeSinceUpdate;
	private Point size, playSize, playCorner;
	private Block leftB, middleB, rightB;
	
	public BlockController(Context context) {
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
		
		leftB = new Block(context.getResources());
		middleB = new Block(context.getResources());
		rightB = new Block(context.getResources());
		
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
	
	public void draw(Canvas canvas, Paint paint) {
		middleB.draw(canvas, position * size.x + playCorner.x, playSize.y - row
				* size.y - playCorner.y, paint);

		if (numOfBlocks >= 2)
			leftB.draw(canvas, (position - 1) * size.x
					+ playCorner.x, playSize.y - row * size.y - playCorner.y, paint);

		if (numOfBlocks == 3)
			rightB.draw(canvas, (position + 1) * size.x
					+ playCorner.x, playSize.y - row * size.y - playCorner.y, paint);
	}
}
