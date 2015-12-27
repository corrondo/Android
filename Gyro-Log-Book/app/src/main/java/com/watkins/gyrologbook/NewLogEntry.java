package com.watkins.gyrologbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewLogEntry extends MyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d( this.TAG, "onCreate() called" );

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_log_entry);
		
    	EditText logEntryDate	= (EditText)findViewById( R.id.editTextDate);
    	logEntryDate.setText(DateFormat.format("MM/dd/yyy", new Date()));

    	// use SimpleDataFormat and "HH" for hour to get 24-hour clock
    	EditText logEntryTime = (EditText)findViewById( R.id.editTextTime);
    	SimpleDateFormat sdf = new SimpleDateFormat( "HH:ss", Locale.US );
    	logEntryTime.setText(sdf.format(new Date()));
	}
   
    public void saveNewLogEntry(View view) {
      EditText  nNum = (EditText)findViewById( R.id.editTextNnum);
        EditText  airPort = (EditText)findViewById( R.id.editTextAirPort );
        EditText airCraftM = (EditText)findViewById( R.id.EditTextAirCraftM );
        EditText  landing = (EditText)findViewById( R.id.EditTextLanding );
        EditText  pilotIC = (EditText)findViewById( R.id.editTextPilotIC );
        EditText  night = (EditText)findViewById( R.id.editTextNight );
        EditText  dual = (EditText)findViewById( R.id.editTextDual);
        EditText  date = (EditText)findViewById( R.id.editTextDate);
        EditText  time = (EditText)findViewById( R.id.editTextTime);
        EditText  actualTime = (EditText)findViewById( R.id.editTextActualTime);
        EditText  xC = (EditText)findViewById( R.id.editTextXC);

		ContentValues values = new ContentValues();
		values.put( LogEntryDatabase.N_NUM,		nNum.getText().toString() );
		values.put( LogEntryDatabase.AIR_PORT,	airPort.getText().toString() );
		values.put( LogEntryDatabase.AIR_CRAFT,		airCraftM.getText().toString() );
		values.put( LogEntryDatabase.LANDING,		landing.getText().toString() );
		values.put( LogEntryDatabase.PILOT_IC,		pilotIC.getText().toString() );
		values.put( LogEntryDatabase.NIGHT,		night.getText().toString() );
		values.put( LogEntryDatabase.DUAL,		dual.getText().toString() );
		values.put( LogEntryDatabase.DATE_DB,	date.getText().toString() );
		values.put( LogEntryDatabase.TIME_DB,	time.getText().toString() );
		values.put( LogEntryDatabase.ACTUAL_TIME,		actualTime.getText().toString() );
		values.put( LogEntryDatabase.XC,	xC.getText().toString() );

    	if( myDatabase.insertLogEntry(values) > 0 )
    		Toast.makeText( this, "New customer created", Toast.LENGTH_SHORT ).show();
    	else
    		Toast.makeText( this, "NO RECORD CREATED!", Toast.LENGTH_LONG ).show();
    	
    	// Return to previous activity by calling finish
    	finish();
    }
    
    public void cancelSaveNewLogEntry( View view ) {

        // User pressed cancel, call finish() to get out of here!
        finish();
    }
}
