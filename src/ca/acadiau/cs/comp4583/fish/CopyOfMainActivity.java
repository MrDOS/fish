package ca.acadiau.cs.comp4583.fish;

import java.sql.Date;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ca.acadiau.cs.comp4583.fish.data.Condition;
import ca.acadiau.cs.comp4583.fish.data.Fish;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import ca.acadiau.cs.comp4583.fish.data.Species;
import ca.acadiau.cs.comp4583.fish.data.persistence.FishtailSessionStorageProvider;

public class CopyOfMainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

  /*      ((Button) super.findViewById(R.id.fishButton)).setOnClickListener(new OnClickListener() {
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
                else { System.out.println("EROOOOR"); return;} //// end comment here ////

                Date start = new Date(System.currentTimeMillis() - 1000*3600);
                Date end = new Date(System.currentTimeMillis());

                //FishingSession(Location location, Date startDate, Date endDate, int anglers, int lines);
                FishingSession ses = new FishingSession(45.67, -64.785, (System.currentTimeMillis() / 1000) - 3600, (System.currentTimeMillis() /1000), 10, 4);
                ses.setCatches(400);
                ses.setExactAnglers(true);
                ses.setExactCatches(true);
                
                Fish first = new Fish(Species.ATLANTIC_STURGEON, 10, Condition.HEALTHY);
                ses.getFish().add(first);
                Fish second = new Fish(Species.DOGFISH, 15, Condition.DEAD);
                ses.getFish().add(second);
                Fish third = new Fish(Species.STRIPED_BASS, 3, Condition.WEAK);
                ses.getFish().add(third);
                
                FishtailSessionStorageProvider hipls = new FishtailSessionStorageProvider();
                //System.out.println("Is available: "+hipls.isProviderAvailable());
                //boolean isavail = hipls.isProviderAvailable();
                //System.out.println(isavail);
                hipls.submitSession(ses);

            }
        }); */
    }
}