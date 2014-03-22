package ca.acadiau.cs.comp4583.fish;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FishActivity extends Activity implements LocationListener
{
    /**
     * Minimum time between location updates in milliseconds.
     */
    private static final int MINIMUM_UPDATE_TIME = 0;
    /**
     * Minimum distance between location updates in meters.
     */
    private static final int MINIMUM_UPDATE_DISTANCE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try
        {
            locationManager.requestLocationUpdates(
                    (Build.PRODUCT.equals("google_sdk"))
                            ? LocationManager.GPS_PROVIDER
                            : LocationManager.NETWORK_PROVIDER,
                    FishActivity.MINIMUM_UPDATE_TIME,
                    FishActivity.MINIMUM_UPDATE_DISTANCE,
                    this);
        }
        catch (IllegalArgumentException e)
        {
            this.showError(R.string.provider_error);
        }
    }

    private void showError(String error)
    {
        TextView errorTextView = (TextView) super.findViewById(R.id.errorTextView);
        errorTextView.setText(error);
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void showError(int resId)
    {
        this.showError(super.getString(resId));
    }

    @Override
    public void onLocationChanged(Location location)
    {
        ((TextView) super.findViewById(R.id.locationTextView)).setText(
                location.getLatitude() + "/" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider)
    {
    }

    @Override
    public void onProviderEnabled(String provider)
    {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }
}
