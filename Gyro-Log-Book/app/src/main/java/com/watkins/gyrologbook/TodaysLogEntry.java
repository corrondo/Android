package com.watkins.gyrologbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TodaysLogEntry extends MyActivity {

	protected ListView lv;
	
	private void initAdapter( ) {
		new AlertDialog.Builder(this)
		.setTitle( "Display with air port?" )
		.setPositiveButton( "Yes",
							new DialogInterface.OnClickListener() {
								public void onClick( DialogInterface dialog,
												int whichButton) {
									showListWithAllFields();
								}
						})
		.setNegativeButton( "No",
							new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
												int whichButton) {
								showListWithoutAllFields();
								}
						})
		.show();
	}
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( this.TAG, "onCreate() called" );
		super.onCreate( savedInstanceState );
		setContentView( R.layout.list_log_entries);

		TextView layoutTitle = (TextView)findViewById( R.id.layout_title );
		layoutTitle.setText( "Today's Schedule" );

		lv = (ListView)findViewById( android.R.id.list );
		initAdapter();
	}
	
	private void showListWithoutAllFields() {
		Cursor todaysSchedule = myDatabase.getTodaysLogEntries();

		showList( todaysSchedule );
	}
	
	
	private void showListWithAllFields() {
		Cursor todaysLogEntries = myDatabase.getTodaysLogEntryWithAll();

		showList( todaysLogEntries );
	}

	@SuppressWarnings("deprecation")
	private void showList( Cursor todaysLe ) {

		ListAdapter adapter = new SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            // Pass in the cursor to bind to
            todaysLe,
            // Array of cursor columns to bind to
            new String[] {	LogEntryDatabase.TIME_DB,
            				LogEntryDatabase.N_NUM,},
            new int[] { android.R.id.text1, android.R.id.text2,  }
            );  // Parallel array of which template objects to bind to those columns.

        // Bind to our new adapter.
		lv.setAdapter( adapter );
	}
}
