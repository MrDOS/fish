package ca.acadiau.cs.comp4583.fish;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class SubmitFishActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_fish);
		Intent thisIntent = getIntent();
		FishingSession session = (FishingSession) thisIntent.getSerializableExtra("Session");
		 final Button end_session_button = (Button) findViewById(R.id.end_session_button);
		 
		 final Button edit_session_button = (Button) findViewById(R.id.edit_session_button);
		 
		 final Button submit_fish_button = (Button) findViewById(R.id.submit_fish_button);
		 
		 end_session_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 Intent i = new Intent(getApplicationContext(),EndSessionActivity.class);
            	 startActivity(i);
		 
             }
		 });
		 edit_session_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 Intent i = new Intent(getApplicationContext(),SessionDataActivity.class);
            	 startActivity(i);
		 
             }
		 });
		 submit_fish_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
		 //Do Fish Submit Stuff Here
		 
             }
		 });
      
     }
	

	
}
