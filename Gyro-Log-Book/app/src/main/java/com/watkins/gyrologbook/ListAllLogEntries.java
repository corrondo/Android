package com.watkins.gyrologbook;


import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ListAllLogEntries extends MyActivity {
	public static final int MENU_VIEW	= Menu.FIRST+1;
	public static final int MENU_EDIT	= Menu.FIRST+2;
	public static final int MENU_DELETE	= Menu.FIRST+3;
    private Cursor lEcursor=null;
    private LogEntryDatabase.DBHelper dbh1 = null;
    Intent objIntent;


	protected ListView lv;
	protected ArrayList<LogEntryDatabase.ID_nNum> myArrLogE;
	
	private void initAdapter( ) {
		ArrayList<String> theNnum = new ArrayList<String>();
        //ArrayAdapter<String> listAdapter;
        myArrLogE = myDatabase.getLogEntries1();
        ListAdapter listAdapter;




		
		for( LogEntryDatabase.ID_nNum c : myArrLogE) {
			theNnum.add(c.nNumV);
            theNnum.add(c.airP);
            theNnum.add(c.airC);
            theNnum.add(c.land);
            theNnum.add(c.pilot);
            theNnum.add(c.night);
            theNnum.add(c.dual);
            theNnum.add(c.date);
            theNnum.add(c.time);
            theNnum.add(c.actualT);
            theNnum.add(c.xc);

		}

        objIntent = getIntent();
        String filter = objIntent.getStringExtra("doit");
        String selectQuery = "SELECT * FROM log_entries";
        if(filter!=null){selectQuery = selectQuery +" WHERE N_num LIKE '%"+filter+"%' ORDER BY time_db";}

        dbh1=new LogEntryDatabase.DBHelper(this);
        lEcursor=dbh1.getReadableDatabase().rawQuery(selectQuery,null);

        //listAdapter = new ArrayAdapter<String>(this, R.id.realThugz, theNnum);



		listAdapter = new SimpleCursorAdapter( this,
				R.layout.horizontal_scroll, lEcursor, new String[]{"N_num", "air_port", "air_craft",
                "landing", "pilot_ic",  "night", "dual", "date_db", "time_db", "actual_time", "xc"},
                new int[]{R.id.nNum, R.id.aPort, R.id.aCraft, R.id.land, R.id.pilot, R.id.night, R.id.dual,
                R.id.date, R.id.time, R.id.actual, R.id.xc} );
		
	    lv.setAdapter(listAdapter);
	}
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( this.TAG, "onCreate() called" );
		super.onCreate( savedInstanceState );
		setContentView( R.layout.list_log_entries);

		lv = (ListView)findViewById( android.R.id.list );
		initAdapter();
		registerForContextMenu( lv );
	}
	
	@Override
	public void onCreateContextMenu( ContextMenu menu, View v,
	                                 ContextMenuInfo menuInfo ) {
/*	    super.onCreateContextMenu( menu, v, menuInfo );
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.city_list_context_menu, menu ) ;
*/
		menu.add( Menu.NONE, MENU_VIEW, Menu.NONE, "View" );
		menu.add( Menu.NONE, MENU_EDIT, Menu.NONE, "Edit" );
		menu.add( Menu.NONE, MENU_DELETE, Menu.NONE, "Delete" );
	}

	@Override
	public boolean onContextItemSelected( MenuItem item ) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    Intent intent;
	    
	    switch( item.getItemId() ) {
	        case MENU_VIEW:
	        	intent = new Intent( this, ViewALogEntry.class );

	        	// Tell EditAcustomer which user to work on by position in list
	        	intent.putExtra( "POSITION", info.position );
	        	  
	        	// Start ViewAcustomer
	        	startActivity( intent );
	            return true;

	        case MENU_EDIT:
	        	intent = new Intent( this, EditAnEntry.class );
		      	
	        	// Tell EditAcustomer which user to work on by position in list
	        	intent.putExtra( "POSITION", info.position );
	        	
	        	// Start EditAcustomer
	        	startActivity( intent );
	            return true;

	        case MENU_DELETE:
	        	long deleted = myDatabase.deleteCustomer( myArrLogE.get(info.position).id );

	        	Toast.makeText( this, deleted + " records deleted", 
	        			Toast.LENGTH_SHORT ).show();

	        	initAdapter();
	            return true;

	        default:
	            return super.onContextItemSelected( item );
	    }
	}
}
