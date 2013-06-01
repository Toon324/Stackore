/**
 * 
 */
package com.Petridish.stackore;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * @author Cody
 *
 */
public class GameEngine {

	 public float screenWidth;
	  public float screenHeight;
	  private Paint blackPaint;
	  private Paint textPaint;
	  private String currentTimeString;
	  private BlockController blocks;

	  public void Init(Context context) {
	    setSurfaceDimensions(540, 960);

	    blackPaint = new Paint();
	    blackPaint.setColor(Color.BLACK);
	    blackPaint.setStyle(Style.FILL);

	    textPaint = new Paint();
	    textPaint.setColor(Color.LTGRAY);
	    textPaint.setTextSize(40);
	    
	    blocks = new BlockController(context);
	    blocks.setPlaySize((int) screenWidth, (int) screenHeight);
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
	    currentTimeString = new SimpleDateFormat("HH:mm:ss").format(new Date());
	    blocks.update(deltatime);
	  }

	  public void draw(Canvas canvas) {
		  canvas.drawColor(Color.BLACK);
		  blocks.draw(canvas, textPaint);
	  }
}
