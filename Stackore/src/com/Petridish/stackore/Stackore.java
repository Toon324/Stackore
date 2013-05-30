package com.Petridish.stackore;

import android.os.Bundle;

import com.Petridish.framework.Screen;
import com.Petridish.framework.implementation.AndroidGame;

public class Stackore extends AndroidGame{
	
	boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {
		if (firstTimeCreate) {
			Assets.loadAssets(this);
			firstTimeCreate = false;
		}
		
		return new LoadingScreen(this);
	}
	
	@Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }

}
