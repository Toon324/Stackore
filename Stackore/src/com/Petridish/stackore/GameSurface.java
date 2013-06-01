package com.Petridish.stackore;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	GameEngine gameEngine;
	  SurfaceHolder surfaceHolder;
	  Context context;
	  private GameThread gameThread;
	 
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

}
