package ca.acadiau.cs.comp4583.fish;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
<<<<<<< HEAD

import android.view.View;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 final Button begin_new_session_button = (Button) findViewById(R.id.begin_session_button);
         begin_new_session_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 Intent i = new Intent(getApplicationContext(),NewSessionActivity.class);
            	 startActivity(i);
             }
         });
      
     }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
=======
import ca.acadiau.cs.comp4583.fish.data.Condition;
import ca.acadiau.cs.comp4583.fish.data.Fish;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import ca.acadiau.cs.comp4583.fish.data.Species;
import ca.acadiau.cs.comp4583.fish.data.persistence.FishtailSessionStorageProvider;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        ((Button) super.findViewById(R.id.fishButton)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent fishIntent = new Intent(getApplicationContext(), FishActivity.class);
                //startActivity(fishIntent);

                LocationManager locationManager = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);

                /*Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                long GPSLocationTime = 0;
                long NetLocationTime = 0;
                if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }
                else if (null != locationNet) { NetLocationTime = locationNet.getTime();}
                else { System.out.println("EROOOOR"); return;}*/

                Date start = new Date(System.currentTimeMillis() - 1000*3600);
                Date end = new Date(System.currentTimeMillis());

                //FishingSession(Location location, Date startDate, Date endDate, int anglers, int lines);
                FishingSession ses = new FishingSession("bob", 45.67, -64.785, (System.currentTimeMillis() / 1000) - 3600, (System.currentTimeMillis() /1000), 10, 4);
                ses.setCatches(400);
                ses.setExactAnglers(true);
                ses.setExactCatches(true);
                
                Fish first = new Fish(Species.ATLANTIC_STURGEON, 10, false, Condition.HEALTHY, Condition.HEALTHY);
                ses.getFish().add(first);
                Fish second = new Fish(Species.DOGFISH, 15, false, Condition.DEAD, Condition.DEAD);
                ses.getFish().add(second);
                Fish third = new Fish(Species.STRIPED_BASS, 3, false, Condition.WEAK, Condition.DEAD);
                ses.getFish().add(third);
                
                FishtailSessionStorageProvider hipls = new FishtailSessionStorageProvider(getString(R.string.fishtail_endpoint), "");
                //System.out.println("Is available: "+hipls.isProviderAvailable());
                //boolean isavail = hipls.isProviderAvailable();
                //System.out.println(isavail);
                LinkedList<FishingSession> sessions = new LinkedList<FishingSession>();
                sessions.add(ses);
                try
                {
                    hipls.submitSessions(sessions);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }
}
>>>>>>> General implementation of the session storage service.
