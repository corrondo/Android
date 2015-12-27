package com.watkins.gyrologbook;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

public class LogEntryDatabase {

	private static final String TAG = "LogEntryDatabase";

	/** Filename of the actual SQLite database */
	private static final String DATABASE_NAME = "log_entry.db";
	/** Table nNumV where the customers will be stored */
	private static final String LOGBOOK_TABLE_NAME = "log_entries";
	/** Version of the customers database */
	private static final int DATABASE_VERSION = 3;

	public static final String _ID				= "_id";
	public static final String N_NUM = "N_num";
	public static final String AIR_PORT = "air_port";
	public static final String AIR_CRAFT = "air_craft";
	public static final String LANDING = "landing";
	public static final String PILOT_IC = "pilot_ic";
	public static final String NIGHT = "night";
	public static final String DUAL = "dual";
	public static final String DATE_DB = "date_db";
	public static final String TIME_DB = "time_db";
	public static final String ACTUAL_TIME = "actual_time";
	public static final String XC = "xc";
	
	/** This will be the query used to create the ad logs table with all it's fields */
	private static final String DATABASE_CREATE =
		"CREATE TABLE " + LOGBOOK_TABLE_NAME + " (" +
			_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                N_NUM + " TEXT, " +
                AIR_PORT + " TEXT, " +
                AIR_CRAFT + " TEXT, " +
                LANDING + " TEXT, " +
                PILOT_IC + " TEXT, " +
                NIGHT + " TEXT, " +
                DUAL + " TEXT, " +
                DATE_DB + " TEXT, " +
                TIME_DB + " TEXT, " +
                ACTUAL_TIME + " TEXT, " +
                XC + " TEXT " +	");";
	
	private SQLiteDatabase DB = null; 
	private DBHelper DBHelper = null;
	private final Context mContext;

    protected class ID_nNum {
        protected long id;
        protected String nNumV, airP, airC, land, pilot, night, dual, date, time, actualT, xc ;

        protected ID_nNum( long id, String nNumV, String airP, String airC,String land,String pilot,
                           String night,String dual,String date,String time,String actualT,String xc) {
            this.id = id;
            this.nNumV = nNumV;
            this.airP=airP;
            this.airC=airC;
            this.land=land;
            this.pilot=pilot;
            this.night=night;
            this.dual=dual;
            this.date = date;
            this.time = time;
            this.actualT=actualT;
            this.xc=xc;
        }
    }
	
	/**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
	public LogEntryDatabase(Context context) {
		this.mContext = context;
		this.DBHelper = new DBHelper( mContext );
	}
	
	public static class DBHelper extends SQLiteOpenHelper {
		public DBHelper( Context context )
		{
			super( context, DATABASE_NAME, null, DATABASE_VERSION );
		}
	
		@Override
		public void onCreate( SQLiteDatabase database ) 
		{
			Log.d( TAG, "oncreate() execSQL( " + DATABASE_CREATE + " )" );
			database.execSQL( DATABASE_CREATE );
		}
		
		@Override
		public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) 
		{ 
			Log.w( TAG, "Upgrading database from version " + oldVersion + " to " +
	                newVersion + ", which will destroy all old data, then recover it" );
			
	        db.execSQL( "DROP TABLE IF EXISTS " + LOGBOOK_TABLE_NAME);
	        onCreate( db );
		}
	}
	
	public LogEntryDatabase open() throws SQLException {
		DB = DBHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
    	DBHelper.close();
	}
	
	public long insertLogEntry(ContentValues values) {
		
		return DB.insert(LOGBOOK_TABLE_NAME, null, values );
	}
	
	public long updateLogEntry(long id, ContentValues values) {
    	Log.d( TAG, "updateLogEntry( values ) _ID = " + id );
		
		return DB.update(LOGBOOK_TABLE_NAME, values, _ID + "="
				+ id, null );
	}
	
	public Cursor getTodaysLogEntries() {
		
		// select N_NUM, TIME_DB from LOGBOOK_TABLE_NAME
		//			where DATE_DB = <today's date>
		
		return DB.query(LOGBOOK_TABLE_NAME,
				new String[] { _ID, N_NUM, TIME_DB, },
				DATE_DB + "=?",
				new String[] { DateFormat.format( "MM/dd/yyy", new Date() ).toString() },
				null,
				null,
                TIME_DB);
	}
	
	public Cursor getTodaysLogEntryWithAll() {
		
		// select N_NUM, AIR_PORT, TIME_DB from LOGBOOK_TABLE_NAME
		//			where DATE_DB = <today's date>
		
		return DB.rawQuery( "SELECT " + _ID + ", " + TIME_DB + ", " + N_NUM
				+ " || \", \" || " + AIR_PORT +" AS " + N_NUM + " FROM " + LOGBOOK_TABLE_NAME + " WHERE "
				+ DATE_DB + " = \"" + DateFormat.format( "MM/dd/yyy", new Date() ).toString()
				+ "\" ORDER BY " + TIME_DB,
				null );
	}
	
	public ContentValues getLogEntry(long id) {
		ContentValues values = new ContentValues();
		
		Cursor c = DB.query(LOGBOOK_TABLE_NAME, null,
								_ID + "=?", 
								new String[] { Long.toString( id ) },
								null, null, null );
		
		// You must call moveToFirst() before calling cursorRowToContentValues!!
		if( c != null && c.getCount() > 0 && c != null && c.moveToFirst() )
			DatabaseUtils.cursorRowToContentValues( c, values );
		
		return values;
	}
	
	public long deleteCustomer( long id ) {
		
		return DB.delete(LOGBOOK_TABLE_NAME, _ID + "=" + id, null );
	}
	
	public ArrayList<ID_nNum> getLogEntries1() {
		ArrayList<ID_nNum> results = new ArrayList<ID_nNum>();
		
	    Cursor c = DBHelper.getReadableDatabase().query(
                LOGBOOK_TABLE_NAME,
	    		new String[] { _ID, N_NUM, AIR_PORT, AIR_CRAFT, LANDING, PILOT_IC, NIGHT, DUAL, DATE_DB,
                TIME_DB, AIR_CRAFT, XC}, null, null, null, null, null );
	    
	    while( c.moveToNext() ) {
//	    	Log.d( TAG, "_ID = " + String.valueOf( c.getLong( c.getColumnIndex( _ID ) ) )
//	    			+ " N_NUM = " + c.getString( c.getColumnIndex( N_NUM ) ) );
	    	
	    	results.add( new ID_nNum( c.getLong( c.getColumnIndex( _ID ) ),
	    			c.getString( c.getColumnIndex(N_NUM) ),c.getString( c.getColumnIndex(AIR_PORT) ),
                    c.getString( c.getColumnIndex(AIR_CRAFT) ),c.getString( c.getColumnIndex(LANDING) ),
                    c.getString( c.getColumnIndex(PILOT_IC) ),c.getString( c.getColumnIndex(NIGHT) ),
                    c.getString( c.getColumnIndex(DUAL) ),c.getString( c.getColumnIndex(DATE_DB) ),
                    c.getString( c.getColumnIndex(TIME_DB) ),c.getString( c.getColumnIndex(AIR_CRAFT) ),
                    c.getString( c.getColumnIndex(XC) )));
	    }
		
		return results;
	}

	
	public void emptyTable() {
		Log.d( TAG, "emptyTable() execSQL( \"DROP TABLE IF EXISTS \"" + LOGBOOK_TABLE_NAME);
		DB.execSQL( "DROP TABLE IF EXISTS " + LOGBOOK_TABLE_NAME);

		Log.d( TAG, "emptyTable() execSQL( " + DATABASE_CREATE + " )" );
		DB.execSQL( DATABASE_CREATE );
	}
}
