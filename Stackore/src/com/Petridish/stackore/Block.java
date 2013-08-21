package com.Petridish.stackore;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.Petridish.framework.implementation.AndroidGraphics;

public class Block extends GfxObject {

	private int outerColor, innerColor;
	private double margin;
	private boolean useImage = false;
	private Point size;

	public Block(Resources resources) {
		bitmap = BitmapFactory.decodeResource(resources, R.drawable.block1);
		outerColor = Color.RED;
		innerColor = Color.BLACK;
		size = new Point(114, 114);
		margin = .18;
	}

	public void draw(Canvas canvas, int i, int j) {
		if (useImage)
			drawCornered(canvas, i, j, null);
		else {
			Paint p = new Paint();
			p.setColor(outerColor);
			canvas.drawRect(i, j, i + size.x, j + size.y, p);
			canvas.drawRect((int) (i + (margin * size.x)),
					(int) (j + (margin * size.y)),
					(int) (i + size.x - (margin * size.x)),
					(int) (j + size.y - (margin * size.y)), new Paint(innerColor));
		}
	}

	public void setSize(Point s) {
		size = s;
	}
	
	public void setUseImage(boolean use) {
		useImage = use;
	}

}
