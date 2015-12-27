package com.watkins.gyrologbook;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyListLogActivity extends MyActivity implements OnItemSelectedListener {
	protected ArrayList<LogEntryDatabase.ID_nNum> myLogEntry1;
	private boolean firstTimeThrough = true;
	protected int itemOnDisplayPos = 0;
	protected Spinner spinner;
	protected ListView lv;
	
	protected EditText nNum;
	protected EditText airPort;
	protected EditText airCraftM;
	protected EditText landing;
	protected EditText pilotIC;
	protected EditText night;
	protected EditText dual;
	protected EditText date;
	protected EditText time;
	protected EditText actualTime;
	protected EditText xC;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( this.TAG, this.getClass().getName() + " onCreate() called" );
		super.onCreate( savedInstanceState );

		Bundle myExtras = getIntent().getExtras();
		
		// Get the item to display if it is present
		if( myExtras != null )
			itemOnDisplayPos = myExtras.getInt( "POSITION" );
		
		setContentView( R.layout.activity_view_a_log_entry);

		nNum = (EditText)findViewById( R.id.editTextNnum);
    	airPort = (EditText)findViewById( R.id.editTextAirPort );
    	airCraftM = (EditText)findViewById( R.id.EditTextAirCraftM );
    	landing = (EditText)findViewById( R.id.EditTextLanding );
    	pilotIC = (EditText)findViewById( R.id.editTextPilotIC );
    	night = (EditText)findViewById( R.id.editTextNight );
    	dual = (EditText)findViewById( R.id.editTextDual);
    	date = (EditText)findViewById( R.id.editTextDate);
    	time = (EditText)findViewById( R.id.editTextTime);
    	actualTime = (EditText)findViewById( R.id.editTextActualTime);
    	xC = (EditText)findViewById( R.id.editTextXC);
	 
		addItemsOnSpinner();
		addListenerOnSpinnerItemSelection();
/*		
		LayoutInflater inflater = (LayoutInflater) getApplicationContext()
				.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

		ViewGroup insertPoint = (ViewGroup)findViewById( R.id.cust_display );
		inflater.inflate( R.layout.view_log_entry, insertPoint );
*/	
	}

	@Override
	public void onItemSelected( AdapterView<?> parent, View view, int pos, long id ) {
		Log.d( TAG,	"OnItemSelectedListener : " + parent.getItemAtPosition(pos) );
		
		if( firstTimeThrough ) {
			firstTimeThrough = false;
			if( itemOnDisplayPos > 0 )
				parent.setSelection( itemOnDisplayPos );
			
		} else
			itemOnDisplayPos = pos;
		
    	ContentValues values = myDatabase.getLogEntry(myLogEntry1.get(itemOnDisplayPos).id);
    	
    	if( values != null && values.size() > 0 ) {
    		nNum.setText(values.getAsString(LogEntryDatabase.N_NUM));
    		airPort.setText(values.getAsString(LogEntryDatabase.AIR_PORT));
    		airCraftM.setText(values.getAsString(LogEntryDatabase.AIR_CRAFT));
    		landing.setText(values.getAsString(LogEntryDatabase.LANDING));
    		pilotIC.setText(values.getAsString(LogEntryDatabase.PILOT_IC));
    		night.setText(values.getAsString(LogEntryDatabase.NIGHT));
    		dual.setText(values.getAsString(LogEntryDatabase.DUAL));
    		date.setText(values.getAsString(LogEntryDatabase.DATE_DB));
    		time.setText(values.getAsString(LogEntryDatabase.TIME_DB));
    		actualTime.setText(values.getAsString(LogEntryDatabase.ACTUAL_TIME));
    		xC.setText(values.getAsString(LogEntryDatabase.XC));
    		
    	} else
    		Toast.makeText( parent.getContext(), 
    				"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString()
    				+ " id " + myLogEntry1.get( pos ).id + " not found!",
    				Toast.LENGTH_SHORT ).show();
	}
		 
	@Override
	public void onNothingSelected( AdapterView<?> arg0 ) {
		Log.d( TAG,	"default onNothingSelected( AdapterView<?> arg0 ) - called" );
		// TODO Auto-generated method stub
	}
	
    public void saveNewLogEntry(View view) {
		Log.d( TAG,	"default saveNewLogEntry( view ) - called" );
		Toast.makeText( view.getContext(), "default saveNewLogEntry( view ) - called",
				Toast.LENGTH_SHORT ).show();
    }
    
    public void cancelSaveNewLogEntry(View view) {
		//Log.d( TAG,	"default cancelSaveNewLogEntry( view ) - called" );
    	
    	// User pressed cancel, call finish() to get out of here!
    	finish();
    }
    
    public void setFormReadOnly( ) {
    	nNum.setEnabled(false);
    	airPort.setEnabled(false);
    	airCraftM.setEnabled(false);
    	landing.setEnabled(false);
    	pilotIC.setEnabled(false);
    	night.setEnabled(false);
    	dual.setEnabled(false);
    	date.setEnabled(false);
    	time.setEnabled(false);
    	actualTime.setEnabled(false);
    	xC.setEnabled(false);
    }
	
	// add items into spinner dynamically
	public void addItemsOnSpinner() {
		spinner = (Spinner) findViewById( R.id.LOG_ENTRY_LIST);
		List<String> list = new ArrayList<String>();
		myLogEntry1 = myDatabase.getLogEntries1();

		for( LogEntryDatabase.ID_nNum c : myLogEntry1) {
			list.add( c.nNumV);
		}

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>( this,
			R.layout.my_spinner_textview, list );
		
		dataAdapter.setDropDownViewResource( R.layout.my_spinner_textview );
		spinner.setAdapter( dataAdapter );
	}
	 
	public void addListenerOnSpinnerItemSelection() {
		spinner = (Spinner) findViewById( R.id.LOG_ENTRY_LIST);
		spinner.setOnItemSelectedListener( this );
	}

}
