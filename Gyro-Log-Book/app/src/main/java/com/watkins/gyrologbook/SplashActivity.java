package com.watkins.gyrologbook;

import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SplashActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();
	private static final long SPLASH_DISPLAY_TIME = 1500;	// Three seconds (in ms)

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
        Log.d( TAG, "OnCreate() called" );

        super.onCreate( savedInstanceState );

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
        
		String splashTheme = prefs.getString( "splash_preference", 
												getString( R.string.default_theme_value ) );
        
		if( splashTheme.equals( getString(R.string.default_theme_value) ) )
			setTheme( R.style.SplashThemeDefault );
		
		else if( splashTheme.equals( getString(R.string.splash_theme_1) ) )
			setTheme( R.style.SplashTheme1 );
        
		else if( splashTheme.equals( getString(R.string.splash_theme_2) ) )
			setTheme( R.style.SplashTheme2 );
		
		else
			Log.d( TAG, "Unknown splash_preference encountered =" + splashTheme);
        
		setContentView( R.layout.activity_splash );
        
        final Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
          @Override
          public void run() {
        	  Context ctx = getApplicationContext();
        	  Intent intent = new Intent( ctx, MainActivity.class );
        	  
        	  // Start our real activity
        	  startActivity( intent  );
        	  
        	  // Finish this activity so user doesn't return via back button
        	  finish();
          }
        }, SPLASH_DISPLAY_TIME );
	}
}
