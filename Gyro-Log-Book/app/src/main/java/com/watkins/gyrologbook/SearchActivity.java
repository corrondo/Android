package com.watkins.gyrologbook;

import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.watkins.gyrologbook.R;

public class SearchActivity extends Activity {


    private LogEntryDatabase.DBHelper myDatabase = null;
    private LogEntryDatabase db=null;
    Button searchBt, cancelBt;
    EditText searchForMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        searchForMe = (EditText)findViewById(R.id.searchFor);


    }
    public void cancelSaveNewLogEntry(View view) {

        // User pressed cancel, call finish() to get out of here!
        finish();
    }

    public void searchPrime(View v){


        Intent i = new Intent(SearchActivity.this, ListAllLogEntries.class);
        i.putExtra("doit", searchForMe.getText().toString());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
