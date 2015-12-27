package com.watkins.gyrologbook;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/*
* Author: Federico Watkins
* Date: 5/5/2015
* App name: Gyro LogBook*/

 public class MainActivity extends MyActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d( this.TAG, "onCreate() called" );

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    
    public void newLogEntry(View view) {
    	Intent intent = new Intent( this, NewLogEntry.class );
	  
    	// Start New Log Entry Menu
    	startActivity( intent );
    }
    
    public void listAllLogEntry(View view) {
    	Intent intent = new Intent( this, ListAllLogEntries.class );
  	  
    	// Start New Log Entry Menu
    	startActivity( intent );
    }
    public void goToSearch(View view) {
        Intent intent = new Intent( this, SearchActivity.class );

        // Start New Log Entry Menu
        startActivity( intent );
    }
    
    public void viewALogEntry(View view) {
    	Intent intent = new Intent( this, ViewALogEntry.class );
  	  
    	// Start New Customer Menu
    	startActivity( intent );
    }
    
    public void editAnEntry(View view) {
    	Intent intent = new Intent( this, EditAnEntry.class );
  	  
    	// Start New Log Entry Menu
    	startActivity( intent );
    }
    
    public void deleteAnEntry(View view) {
    	Intent intent = new Intent( this, DeleteAnEntry.class );
  	  
    	// Start New Log Entry Menu
    	startActivity( intent );
    }
    
    public void todaysLogEntry(View view) {
    	Intent intent = new Intent( this, TodaysLogEntry.class );
  	  
    	// Start New Log Entry Menu
    	startActivity( intent );
    }
}
