/**
 * 
 */
package com.Petridish.stackore;

import java.util.ArrayList;
import java.util.Random;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * @author Cody
 * 
 */
public class GameEngine {

	private final int INCREASE_SPEED = 50;
	
	public float screenWidth;
	public float screenHeight;
	private Paint blackPaint;
	private Paint textPaint;
	private ArrayList<BlockController> blocks;
	private int activeBlockRow, outterColor, innerColor;
	private double margin;
	private GfxObject background;
	private Context engineContext;
	private static final String logTag = "GameEngine";
	private static Game gameActivity;

	public void Init(Context context) {
		setSurfaceDimensions(540, 960);

		engineContext = context;

		blackPaint = new Paint();
		blackPaint.setColor(Color.BLACK);
		blackPaint.setStyle(Style.FILL);

		textPaint = new Paint();
		textPaint.setColor(Color.LTGRAY);
		textPaint.setTextSize(40);

		background = new GfxObject();
		background.bitmap = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.background_proto);

		blocks = new ArrayList<BlockController>();

		blocks.add(new BlockController(context));

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();

		wm.getDefaultDisplay().getMetrics(dm);

		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		activeBlockRow = 1;
		//blocks.get(0).setPlaySize(screenWidth, screenHeight);
		blocks.get(0).setPlaySize(570, 1246);
		blocks.get(0).setPlayCorner(15, 130);
		
		SharedPreferences pref = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
		outterColor = pref.getInt("OutterColor", Color.RED);
		innerColor = pref.getInt("InnerColor", Color.BLACK);
		margin = pref.getFloat("Margin", .18f);
	}

	public void onDestroy() {
		try {
		} catch (Exception e) {
		}
	}

	public void setSurfaceDimensions(int width, int height) {
		screenWidth = width;
		screenHeight = height;

		if (blocks == null)
			return;

		for (BlockController c : blocks.toArray(new BlockController[0]))
			c.setPlaySize(screenWidth, screenHeight);
	}

	public void update(int deltatime) {
		blocks.toArray(new BlockController[0])[activeBlockRow - 1]
				.update(deltatime);
	}

	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		background.drawCornered(canvas, 0, 0, textPaint);
		for (BlockController c : blocks.toArray(new BlockController[0]))
			c.draw(canvas, textPaint);
	}

	public void onTap() {
		
		//Creates a new row and updates the engine's references
		BlockController lastBlockRow = blocks.get(activeBlockRow - 1);
		lastBlockRow.setActive(false);
		BlockController newBlockRow = new BlockController(engineContext);
		
		//Copies size of play area over to new row
		lastBlockRow.copyPlaySizeTo(newBlockRow);
		lastBlockRow.copyPlayCornerTo(newBlockRow);
		
		//Increases the speed of the row
		newBlockRow.setSpeed(lastBlockRow.getSpeed() - INCREASE_SPEED);
		
		//Sets the row to go either left or right.
		Random gen = new Random();
		newBlockRow.setDirection(gen.nextBoolean()); //True is left, false is right.

		if (activeBlockRow - 2 >= 0) {
			int nextRowNum = lastBlockRow.getNumberOfBlocks();

			if (!blocks.get(activeBlockRow - 2).isBlockSupported(
					lastBlockRow.getPosition())) {

				lastBlockRow.setBlockUnsupported(lastBlockRow.getPosition());

				nextRowNum--;
				Log.d(logTag, "Next Row Size: " + nextRowNum);
			}

			if (lastBlockRow.getNumberOfBlocks() >= 2
					&& !blocks.get(activeBlockRow - 2).isBlockSupported(
							lastBlockRow.getPosition() - 1)) {

				lastBlockRow
						.setBlockUnsupported(lastBlockRow.getPosition() - 1);
				nextRowNum--;
				Log.d(logTag, "Next Row Size: " + nextRowNum);
			}

			if (lastBlockRow.getNumberOfBlocks() >= 3
					&& !blocks.get(activeBlockRow - 2).isBlockSupported(
							lastBlockRow.getPosition() + 1)) {

				lastBlockRow
						.setBlockUnsupported(lastBlockRow.getPosition() + 1);
				nextRowNum--;
				Log.d(logTag, "Next Row Size: " + nextRowNum);
			}
			
			if (nextRowNum == 0)
				if (gameActivity != null) {
					gameActivity.showGameOver();
				}

			newBlockRow.setNumberOfBlocks(nextRowNum);
		}
		
		int lastRow = blocks.get(activeBlockRow - 1).getRow();
		activeBlockRow++;

		newBlockRow.setRow(lastRow + 1);
		newBlockRow.setPlaySize(screenWidth, screenHeight);

		blocks.add(newBlockRow);
	}
	
	public BlockController getCurrentRow() {
		return blocks.get(activeBlockRow-1);
	}

	public static void GiveActivity(Game game) {
		gameActivity = game;
	}
}
