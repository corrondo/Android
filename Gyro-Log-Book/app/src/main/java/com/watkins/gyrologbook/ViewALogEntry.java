package com.watkins.gyrologbook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

	public class ViewALogEntry extends MyListLogActivity {

        private NewLogEntry nLe= new NewLogEntry();
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( this.TAG, this.getClass().getName() + " onCreate() called" );
		super.onCreate( savedInstanceState );
		
		Button btnLogSave = (Button)findViewById( R.id.button_log_save);
        btnLogSave.setVisibility(View.GONE);

       /* Button btnLogCancel = (Button)findViewById( R.id.button_log_cancel);
        btnLogCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){nLe.cancelSaveNewLogEntry(v);}
        });*/

		TextView layoutTitle = (TextView)findViewById( R.id.layout_title );
		layoutTitle.setText( "View a Log Entry" );

		setFormReadOnly();

	}
        public void cancelSaveNewCustomer( View view ) {

            // User pressed cancel, call finish() to get out of here!
            finish();
        }
}