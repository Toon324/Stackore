/**
 * 
 */
package com.Petridish.stackore;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author Cody
 * 
 */
public class GameEngine {

	public float screenWidth;
	public float screenHeight;
	private Paint blackPaint;
	private Paint textPaint;
	private BlockController blocks;
	private GfxObject background;

	public void Init(Context context) {
		setSurfaceDimensions(540, 960);

		blackPaint = new Paint();
		blackPaint.setColor(Color.BLACK);
		blackPaint.setStyle(Style.FILL);

		textPaint = new Paint();
		textPaint.setColor(Color.LTGRAY);
		textPaint.setTextSize(40);

		background = new GfxObject();
		background.bitmap = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.background_proto);

		blocks = new BlockController(context);

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();

		wm.getDefaultDisplay().getMetrics(dm);

		blocks.setPlaySize(dm.widthPixels, dm.heightPixels);
	}

	public void onDestroy() {
		try {
		} catch (Exception e) {
		}
	}

	public void setSurfaceDimensions(int width, int height) {
		screenWidth = width;
		screenHeight = height;
	}

	public void update(int deltatime) {
		blocks.update(deltatime);
	}

	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		background.drawCornered(canvas, 0, 0, textPaint);
		blocks.draw(canvas, textPaint);
	}
}
