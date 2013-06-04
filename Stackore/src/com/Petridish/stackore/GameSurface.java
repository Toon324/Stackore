package com.Petridish.stackore;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	GameEngine gameEngine;
	SurfaceHolder surfaceHolder;
	Context context;
	private GameThread gameThread;
	private float lastTouchX;
	private float lastTouchY;
	private long touchDownTime = 0;
	private static final int INVALID_POINTER_ID = -1;
	private int activePointerId = INVALID_POINTER_ID;

	public GameSurface(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		InitView();
	}

	public GameSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		InitView();
	}

	void InitView() {
		SurfaceHolder surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		gameEngine = new GameEngine();
		gameEngine.Init(context);
		gameThread = new GameThread(surfaceHolder, context, new Handler(),
				gameEngine);
		setFocusable(true);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
		boolean retry = true;
		gameThread.state = GameThread.PAUSED;
		while (retry) {
			try {
				gameThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		if (gameThread.state == GameThread.PAUSED) {
			gameThread = new GameThread(getHolder(), context, new Handler(),
					gameEngine);
			gameThread.start();
		} else {
			gameThread.start();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		gameEngine.setSurfaceDimensions(width, height);
	}

	public boolean onTouchEvent(MotionEvent motionEvent) {
		final int action = motionEvent.getAction();

		switch (action & MotionEvent.ACTION_MASK) {

		case MotionEvent.ACTION_DOWN: {
			final float x = motionEvent.getX();
			final float y = motionEvent.getY();

			lastTouchX = x;
			lastTouchY = y;
			touchDownTime = System.currentTimeMillis();
			activePointerId = motionEvent.getPointerId(0);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			final int pointerIndex = motionEvent
					.findPointerIndex(activePointerId);
			final float x = motionEvent.getX(pointerIndex);
			final float y = motionEvent.getY(pointerIndex);
			final float dX = x - lastTouchX;
			final float dY = y - lastTouchY;

			lastTouchX = x;
			lastTouchY = y;

			if (dY < -20) {
				Log.d("JustRoids", "MotionEvent: Swipe up (Thrust)");
				break;
			}
			if (dX > 20) {
				Log.d("JustRoids", "MotionEvent: Swipe right");
				break;
			}
			if (dX < -20) {
				Log.d("JustRoids", "MotionEvent: Swipe left");
				break;
			}
			break;
		}

		case MotionEvent.ACTION_UP: {
			if (System.currentTimeMillis() < touchDownTime + 150) {
				Log.d("JustRoids", "MotionEvent: Tap (Fire)");
			}
			gameThread.tapped();
			activePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			activePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			final int pointerIndex = (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			final int pointerId = motionEvent.getPointerId(pointerIndex);
			if (pointerId == activePointerId) {
				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
				lastTouchX = motionEvent.getX(newPointerIndex);
				lastTouchY = motionEvent.getY(newPointerIndex);
				activePointerId = motionEvent.getPointerId(newPointerIndex);
			}
			break;
		}
		}

		return true;

	}

}
