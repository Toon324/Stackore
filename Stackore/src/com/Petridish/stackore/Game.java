/**
 * 
 */
package com.Petridish.stackore;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Cody
 *
 */
public class Game extends Activity implements GameOverDialog.DialogListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.game);
		
		GameEngine.GiveActivity(this);
	}

	public void showGameOver() {
		DialogFragment dialog = new GameOverDialog();
		dialog.show(getFragmentManager(), "Dialog");
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		finish();
	}
}
