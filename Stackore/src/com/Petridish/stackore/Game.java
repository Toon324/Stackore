/**
 * 
 */
package com.Petridish.stackore;

import android.annotation.SuppressLint;
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
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.game);

		GameEngine.GiveActivity(this);
	}

	@SuppressLint("NewApi")
	public void showGameOver() {
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			DialogFragment dialog = new GameOverDialog();
			dialog.show(getFragmentManager(), "Dialog");
		}
		else {
			Intent intent = new Intent(this, GameOver.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		finish();
	}
}
