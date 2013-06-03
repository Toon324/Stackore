/**
 * 
 */
package com.Petridish.stackore;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Cody
 * 
 */
public class GfxObject {

	protected Bitmap bitmap;
	protected float x;
	protected float y;
	private double velocity = 0;
	private double direction = 0;
	private double dX = 0;
	private double dY = 0;

	public void setVelocity(float velocity) {
		this.velocity = velocity;
		calculateDXDY();
	}

	public void setDirection(float direction) {
		this.direction = direction;
		calculateDXDY();
	}

	private void calculateDXDY() {
		double radians = Math.toRadians(direction);
		dX = Math.cos(radians) * velocity;
		dY = Math.sin(radians) * velocity;
	}

	public void move(float screenWidth, float screenHeight) {
		float minX = 0 - bitmap.getWidth() / 2;
		float maxX = screenWidth + bitmap.getWidth() / 2;
		float minY = 0 - bitmap.getHeight() / 2;
		float maxY = screenHeight + bitmap.getHeight() / 2;

		x += dX;
		y += dY;
		if (x > maxX)
			x = minX;
		if (x < minX)
			x = maxX;
		if (y > maxY)
			y = minY;
		if (y < minY)
			y = maxY;
	}

	public void drawCentered(Canvas canvas, float x, float y, Paint paint) {
		canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2,
				y - bitmap.getHeight() / 2, paint);
	}
	
	public void drawCornered(Canvas canvas, int x, int y, Paint paint) {
		canvas.drawBitmap(bitmap, x, y, paint);
	}
}
