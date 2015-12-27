package com.watkins.gyrologbook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteAnEntry extends MyListLogActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d( this.TAG, this.getClass().getName() + " onCreate() called" );

		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_view_a_log_entry);
		
		Button btnCustSave = (Button)findViewById( R.id.button_log_save);
		btnCustSave.setText( "Delete" );

		TextView layoutTitle = (TextView)findViewById( R.id.layout_title );
		layoutTitle.setText( "Delete Log Entry");
		this.setFormReadOnly();
	}

	public void saveNewLogEntry(View view) {
		Log.d( TAG,	this.getClass().getName() + "saveNewLogEntry( view ) - called" );
		
    	long deleted = myDatabase.deleteCustomer( myLogEntry1.get( itemOnDisplayPos ).id );

    	Toast.makeText( this, deleted + " Log Entry(ies) deleted",
    			Toast.LENGTH_SHORT ).show();
    	
    	addItemsOnSpinner();
    }


    public void cancelSaveNewLogEntry(View view) {

        // User pressed cancel, call finish() to get out of here!
        finish();
    }
}
