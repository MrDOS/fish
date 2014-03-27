package ca.acadiau.cs.comp4583.fish;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class SessionDataActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.session_data);
		final Button end_session_button = (Button) findViewById(R.id.submit_new_session_data_button);
		
		Intent thisIntent = getIntent();
		final FishingSession session = (FishingSession) thisIntent.getSerializableExtra("Session");
		 end_session_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getApplicationContext(),SubmitFishActivity.class);
           	 startActivity(i);
		 
            }
		 });

     }
	

	
}
