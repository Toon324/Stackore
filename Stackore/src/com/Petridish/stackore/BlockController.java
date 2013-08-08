package com.Petridish.stackore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class BlockController {

	private boolean left, active, usesImage;
	private int position, row, numOfBlocks, updateSpeed, timeSinceUpdate,
			outerColor, innerColor;
	private Point size, playSize, playCorner;
	private Block block;
	private static final String logTag = "BlockController";
	private Paint innerPaint, outerPaint;

	public BlockController(Context context) {
		left = true;
		active = true;
		playSize = new Point(100, 100);
		playCorner = new Point(0, 0);
		updateSpeed = 500;
		timeSinceUpdate = 0;
		numOfBlocks = 3;
		timeSinceUpdate = 0;
		position = 3;
		row = 1;
		
		innerPaint = new Paint();
		innerPaint.setColor(Color.BLACK);
		
		outerPaint = new Paint();
		outerPaint.setColor(Color.RED);

		block = new Block(context.getResources());
		size = new Point(block.bitmap.getWidth(), block.bitmap.getHeight());
		block.setSize(size);
	}

	public void setPlaySize(float screenWidth, float screenHeight) {
		playSize = new Point((int) screenWidth, (int) screenHeight);
		// Log.i(logTag, "Playsize set to " + screenWidth + "x" + screenHeight);
	}

	public void setPlayCorner(int x, int y) {
		playCorner = new Point(x, y);
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

	public void setImageUse(boolean toUse) {
		usesImage = toUse;
	}

	public void update(float deltaTime) {
		if (!active)
			return;

		timeSinceUpdate += deltaTime;

		if (timeSinceUpdate >= updateSpeed) {
			move();
			timeSinceUpdate = 0;
		}
	}

	public boolean isBlockSupported(int otherPos) {

		if (otherPos == position && !middleUnsupported)
			return true;

		else if ((otherPos == position - 1) && (numOfBlocks >= 2)
				&& !leftUnsupported)
			return true;

		else if ((otherPos == position + 1) && (numOfBlocks >= 3)
				&& !rightUnsupported)
			return true;

		return false;
	}

	private void move() {
		if (left)
			position -= 1;
		else
			position += 1;

		if (position >= 5 || position <= 1)
			left = !left;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int newRow) {
		row = newRow;
	}

	public void draw(Canvas canvas, Paint paint) {
		if (usesImage) {
			if (numOfBlocks >= 1 && !middleUnsupported)
				block.draw(canvas, position * size.x + playCorner.x, playSize.y
						- (row - 1) * size.y - playCorner.y);

			if (numOfBlocks >= 2 && !leftUnsupported && position != 1)
				block.draw(canvas, (position - 1) * size.x + playCorner.x,
						playSize.y - (row - 1) * size.y - playCorner.y);

			if (numOfBlocks == 3 && !rightUnsupported && position != 5)
				block.draw(canvas, (position + 1) * size.x + playCorner.x,
						playSize.y - (row - 1) * size.y - playCorner.y);
		} else {
			if (numOfBlocks >= 1 && !middleUnsupported) {
				canvas.drawRect(position * size.x + playCorner.x, playSize.y
						- (row - 1) * size.y - playCorner.y, position * size.x
						+ playCorner.x + 50, playSize.y - (row - 1) * size.y
						- playCorner.y + 50, outerPaint);

			}

			if (numOfBlocks >= 2 && !leftUnsupported && position != 1)
				canvas.drawRect((position - 1) * size.x + playCorner.x,
						playSize.y - (row - 1) * size.y - playCorner.y,
						(position - 1) * size.x + playCorner.x + 50, playSize.y
								- (row - 1) * size.y - playCorner.y + 50,
								outerPaint);

			if (numOfBlocks == 3 && !rightUnsupported && position != 5)
				canvas.drawRect((position + 1) * size.x + playCorner.x,
						playSize.y - (row - 1) * size.y - playCorner.y,
						(position + 1) * size.x + playCorner.x + 50, playSize.y
								- (row - 1) * size.y - playCorner.y + 50,
								outerPaint);
		}
	}

	public int getPosition() {
		return position;
	}

	public int getNumberOfBlocks() {
		return numOfBlocks;
	}

	private boolean middleUnsupported = false;
	private boolean leftUnsupported = false;
	private boolean rightUnsupported = false;

	public void setBlockUnsupported(int unsupported) {

		if (unsupported == position)
			middleUnsupported = true;

		else if (unsupported == position - 1)
			leftUnsupported = true;

		else
			rightUnsupported = true;

	}

	public int getSpeed() {
		return updateSpeed;
	}

	public void setDirection(boolean dir) {
		left = dir;
	}

	public void setOuterColor(int oc) {
		outerColor = oc;
		outerPaint.setColor(oc);
	}

	public void copyData(BlockController newBlockRow) {
		newBlockRow.setPlaySize(playSize.x, playSize.y);
		newBlockRow.setPlayCorner(playCorner.x, playCorner.y);
		newBlockRow.setImageUse(usesImage);
		newBlockRow.setOuterColor(outerColor);
		newBlockRow.setInnerColor(innerColor);
	}

	public void setInnerColor(int ic) {
		innerColor = ic;
		innerPaint.setColor(ic);
	}
}
