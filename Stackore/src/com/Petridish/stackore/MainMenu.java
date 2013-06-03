package com.Petridish.stackore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

/**
 * @author Cody
 * 
 */
public class MainMenu extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		AdView adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest();
		//adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		adRequest.addTestDevice("E213A781A27DEE785F5CCD8A07BF21BF");
		adView.loadAd(adRequest);
	}

	public void onClickGame(View v) {
		Toast.makeText(this, "You clicked on Game!", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, Game.class);
		startActivity(intent);
	}

	public void onClickHowto(View v) {
		Toast.makeText(this, "You clicked on Howto!", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, HowTo.class);
		startActivity(intent);
	}

	public void onClickCredits(View v) {
		Toast.makeText(this, "You clicked on Credits!", Toast.LENGTH_LONG)
				.show();
		Intent intent = new Intent(this, Credits.class);
		startActivity(intent);
	}

}
