/**
 * 
 */
package com.Petridish.stackore;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * @author Cody
 * 
 */
public class Game extends Activity implements GameOverDialog.DialogListener {

	private MediaPlayer player;
	private AssetFileDescriptor afd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.game);

		GameEngine.GiveActivity(this);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		try {
			afd = getAssets().openFd("normal.mp3");
			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
					afd.getLength());
			player.setLooping(true);
			player.prepare();
			player.start();
		} catch (Exception e) {
			Log.v("Player", "error occured");
			Log.e("Player", e.getMessage());
		}
	}

	@SuppressLint("NewApi")
	public void showGameOver() {
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			DialogFragment dialog = new GameOverDialog();
			dialog.show(getFragmentManager(), "Dialog");
		} else {
			Intent intent = new Intent(this, GameOver.class);
			startActivity(intent);
			finish();
		}
	}

	public void playRowSound(int row) {
		try {
			try {
				player.pause();
				player.stop();
			} catch (Exception e) {
			}
			
			player = new MediaPlayer();
			afd = getAssets().openFd("row" + row + ".mp3");
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
					afd.getLength());
			player.prepare();
			player.start();
		} catch (Exception e) {
			Log.e("RowPlayer", "Error: " + e.getMessage());
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		finish();
	}

	public void onStop() {
		super.onStop();
		player.release();
	}

	public void onPause() {
		super.onPause();
		player.release();
	}
}
