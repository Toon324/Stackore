package com.Petridish.stackore;

import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.Petridish.framework.Graphics.ImageFormat;
import com.Petridish.framework.Image;
import com.Petridish.framework.Sound;
import com.Petridish.framework.implementation.AndroidImage;

public class Assets {

	public static Image menu;
	public static Sound click;
	public static AndroidImage background, block;
	private static int screenWidth, screenHeight;
	private static float density;

	public static void loadAssets(Stackore stackoreGame) {

		try {
			Bitmap map1 = BitmapFactory.decodeResource(stackoreGame.getResources(), R.drawable.background_proto);
			Bitmap map2 = BitmapFactory.decodeResource(stackoreGame.getResources(), R.drawable.block);
			
			WindowManager wm = (WindowManager) stackoreGame
					.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics dm = new DisplayMetrics();
			
			wm.getDefaultDisplay().getMetrics(dm);
			
			screenWidth = dm.widthPixels;
			screenHeight = dm.heightPixels;
			
			/*
			screenWidth = (int) (dm.widthPixels * dm.density);
			screenHeight = (int) (dm.heightPixels * dm.density);
			*/
			density = dm.density / 160;
			
			Log.d("Screen", "Width: " + screenWidth + " Height:" + screenHeight + " Density: " + density);
			

			background = new AndroidImage(map1, ImageFormat.RGB565);
			Log.d("Screen", "Background Width: " + map1.getWidth() + " Height: " + map1.getHeight() + " Density: " + (map1.getDensity()/160));
			Log.d("Screen", "Scaled Width: " + map1.getScaledWidth(dm) + " Height: " + map1.getScaledHeight(dm));
			block = new AndroidImage(map2, ImageFormat.ARGB4444);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}
	
	public static float getDensity() {
		return density;
	}
}
