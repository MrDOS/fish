package ca.acadiau.cs.comp4583.fish;

import java.util.ArrayList;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;

public class SessionDataActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.session_data);
		final Button end_session_button = (Button) findViewById(R.id.submit_new_session_data_button);
		final Spinner location_spinner = (Spinner) findViewById(R.id.location_text_spinner);
		
		 ArrayList<String> location_options = new ArrayList<String>();
    	 location_options.add("Bridgewater");
		 location_options.add("Wolfville");	  
		 
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item, location_options);
                 dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                 location_spinner.setAdapter(dataAdapter);
      
		Intent thisIntent = getIntent();
		final FishingSession session = (FishingSession) thisIntent.getSerializableExtra("Session");
//					location_spinner.set
		final EditText anglersText = (EditText) findViewById(R.id.num_anglers_text_edit);
		anglersText.setText(Integer.toString(session.getAnglers()));
		
		final CheckBox anglersEstimatedChbx = (CheckBox) findViewById(R.id.checkBox1);
		anglersEstimatedChbx.setChecked(!session.isExactAnglers());
		
		final EditText linesText = (EditText) findViewById(R.id.num_rods_text_edit);
		linesText.setText(Integer.toString(session.getLines()));
		 end_session_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getApplicationContext(),SubmitFishActivity.class);
            	i.putExtra("Session", session);
           	 startActivity(i);
		 
            }
		 });

     }
	

	
}
