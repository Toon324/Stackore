package com.Petridish.stackore;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.Petridish.framework.implementation.AndroidGraphics;

public class Block extends GfxObject {

	public Block(Resources resources) {
		bitmap = BitmapFactory.decodeResource(resources, R.drawable.block);
	}
	
}
