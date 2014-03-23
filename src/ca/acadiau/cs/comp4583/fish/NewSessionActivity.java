package ca.acadiau.cs.comp4583.fish;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.view.View;

public class NewSessionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_session);
		 final Button new_session_button = (Button) findViewById(R.id.submit_new_session_data_button);
         new_session_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 FishingSession sessoin = new FishingSession();
             }
         });
      
     }
	

	
}
