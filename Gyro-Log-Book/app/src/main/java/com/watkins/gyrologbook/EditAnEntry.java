package com.watkins.gyrologbook;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditAnEntry extends MyListLogActivity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( this.TAG, "onCreate() called" );

		super.onCreate( savedInstanceState );

		Button btnCustSave = (Button)findViewById( R.id.button_log_save);
		btnCustSave.setText( "Save" );

		TextView layoutTitle = (TextView)findViewById( R.id.layout_title );
		layoutTitle.setText( "Edit Log Entry" );
	}
	   
    public void saveNewLogEntry(View view) {
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

    	if( myDatabase.updateLogEntry(myLogEntry1.get(itemOnDisplayPos).id, values) > 0 ) {
    		Toast.makeText( this, "Log Entry updated", Toast.LENGTH_SHORT ).show();
    		finish();
    		
    	} else
    		Toast.makeText( this, "NO Log Entry UPDATED!", Toast.LENGTH_LONG ).show();
    }

    public void cancelSaveNewCustomer( View view ) {

        // User pressed cancel, call finish() to get out of here!
        finish();
    }

}
