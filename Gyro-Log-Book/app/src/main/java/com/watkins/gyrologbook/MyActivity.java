package com.watkins.gyrologbook;

import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MyActivity extends Activity {
	protected Context mContext;
	protected LogEntryDatabase myDatabase;
	protected final String TAG = this.getClass().getSimpleName();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
		String fontPref = prefs.getString( "font_preference", 
												getString( R.string.font_medium ) );
        
		if( fontPref.equals( getString(R.string.font_medium) ) )
			setTheme( R.style.font_medium );
		
		else if( fontPref.equals( getString(R.string.font_small) ) )
			setTheme( R.style.font_small );
        
		else if( fontPref.equals( getString(R.string.font_large) ) )
			setTheme( R.style.font_large );
		
		else
			Log.d( TAG, "Unknown font_preference encountered =" + fontPref);
        
        
        // Save a reference to our context
        mContext = getApplicationContext();
 		
 		if( myDatabase == null ) {
 			Log.d( TAG, "onCreate() - new LogEntryDatabase()");
 			myDatabase = new LogEntryDatabase( mContext );
 			Log.d( TAG, "onCreate() - myDatabase.open()" );
			myDatabase.open();
 		}
    }
	
    @Override
 	public void onResume() {
    	Log.d( TAG, this.getClass().getName() + " onResume() - called" );
 		super.onResume();

 		if( myDatabase != null ) {
 			Log.d( TAG, "onResume() - myDatabase.open()" );
 			myDatabase.open();
 		}
 	}
 	
 	@Override
 	public void onPause() {
    	Log.d( TAG, this.getClass().getName() + " onPause() - called" );
 		super.onPause();
 		
 		if( myDatabase !=null ) {
 	 		Log.d( TAG, "onPause() - myDatabase.close()" );
			myDatabase.close();
 		}
 	}
 	
 	@Override
 	public void onDestroy() {
 		super.onDestroy();
 		
 		if( myDatabase != null ) {
 	 		Log.d( TAG, "onDestroy() - myDatabase.close()" );
 			myDatabase.close();
 			myDatabase = null;
 		}
 	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.activity_main, menu );
		return true;
	}
	
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
    	String myString;
    	
        switch ( item.getItemId() ) {
        case R.id.menu_about:
        	try {
        		PackageInfo packageInfo = getPackageManager().getPackageInfo( getPackageName(), 0 );

        		myString = "Name: Federico Watkins\nMobile Computing\nFinal Project\nCurrent date: "
    					+ DateFormat.format( "yyyy-MM-dd", new Date() ).toString() + "\n"
    					+ "Package nNumV: "		+ packageInfo.packageName + "\n"
    					+ "Process Name: "		+ packageInfo.applicationInfo.processName + "\n"
    					+ "Source dir: "		+ packageInfo.applicationInfo.sourceDir + "\n"
    					+ "Last updated: "		+ DateFormat.format( "yyyy-MM-dd hh:mm:ss", 
    							packageInfo.lastUpdateTime ).toString() + "\n"
    					+ "Version Name: "		+ packageInfo.versionName + "\n"
    					+ "Version Code: "		+ String.valueOf( packageInfo.versionCode + "\n"
    					+ "Activity nNumV: "		+ this.getClass().getSimpleName() );
        		
        	} catch (NameNotFoundException e) {
        		// Print stack trace and default string to error message
        		e.printStackTrace();
        		myString = "Cannot load Version!";
        	} 
        	
        	Toast.makeText( this, myString, Toast.LENGTH_LONG ).show();

        	return true;
        	
		case R.id.menu_preferences:
				startActivity(new Intent(this, EditPreferences.class));
				return(true);

        default:
        	return super.onOptionsItemSelected(item);
       }
   }    
}
