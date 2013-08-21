package com.Petridish.stackore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

/**
 * @author Cody
 * 
 */
public class MainMenu extends Activity {

	private MediaPlayer player;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		try {
		AssetFileDescriptor afd = getAssets().openFd("title1.mp3");
	    player = new MediaPlayer();
	    player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
	    player.prepare();
	    player.start();
		}
		catch (Exception e) {
			Log.v("Player", "error occured");
			Log.e("Player", e.getMessage());
		}

		AdView adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest();
		//adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		adRequest.addTestDevice("E213A781A27DEE785F5CCD8A07BF21BF");
		adView.loadAd(adRequest);
	}

	public void onClickGame(View v) {
		Intent intent = new Intent(this, Game.class);
		startActivity(intent);
		player.release();
	}

	public void onClickHowto(View v) {
		Intent intent = new Intent(this, HowTo.class);
		startActivity(intent);
	}

	public void onClickOptions(View v) {
		Intent intent = new Intent(this, Options.class);
		startActivity(intent);
	}

}
