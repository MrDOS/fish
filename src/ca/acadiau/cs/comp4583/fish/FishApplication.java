package ca.acadiau.cs.comp4583.fish;

import ca.acadiau.cs.comp4583.fish.data.persistence.SessionStorageService;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * Application or sumthun'. Primarily used to provide global access to the
 * application context in order to access resources outside of activities.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class FishApplication extends Application
{
    private static Context applicationContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        FishApplication.applicationContext = this;

        /* In case there are sessions sitting around unsubmitted, we'll start
         * the session storage service along with the app. */
        startService(new Intent(this, SessionStorageService.class));
    }

    /**
     * Obtain access to the application context. Useful for accessing resources
     * from outside of activities.
     * 
     * @return the application context
     */
    public static Context getContext()
    {
        return FishApplication.applicationContext;
    }
}