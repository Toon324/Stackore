package com.Petridish.stackore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class BlockController {

	private boolean left, active;
	private int position, row, numOfBlocks, updateSpeed, timeSinceUpdate;
	private Point size, playSize, playCorner;
	private Block block;
	private static final String logTag = "BlockController";
	
	public BlockController(Context context) {
		left = true;
		active = true;
		playSize = new Point(100, 100);
		playCorner = new Point(0,0);
		updateSpeed = 500;
		timeSinceUpdate = 0;
		numOfBlocks = 3;
		timeSinceUpdate = 0;
		position = 3;
		row = 1;
		
		block = new Block(context.getResources());
		size = new Point (block.bitmap.getWidth(), block.bitmap.getHeight());
	}
	
	public void setPlaySize(int x, int y) {
		playSize = new Point(x, y);
		Log.i(logTag, "Playsize set to " + x + "x" + y);
	}
	
	public void setPlayCorner(int x, int y) {
		playCorner = new Point(x,y);
	}
	
	public void setSpeed(int ms) {
		updateSpeed = ms;
	}
	
	public void setNumberOfBlocks(int num) {
		numOfBlocks = num;
	}
	
	public void setActive(boolean toSet) {
		active = toSet;
	}
	
	public boolean isActive() {
		return active;
	}

	public void update(float deltaTime) {
		if ( !active )
			return;
		
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
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int newRow) {
		row = newRow;
	}
	
	public void draw(Canvas canvas, Paint paint) {
		block.drawCornered(canvas, position * size.x + playCorner.x, playSize.y - row
				* size.y - playCorner.y, paint);

		if (numOfBlocks >= 2)
			block.drawCornered(canvas, (position - 1) * size.x
					+ playCorner.x, playSize.y - row * size.y - playCorner.y, paint);

		if (numOfBlocks == 3)
			block.drawCornered(canvas, (position + 1) * size.x
					+ playCorner.x, playSize.y - row * size.y - playCorner.y, paint);
	}
}
