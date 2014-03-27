package ca.acadiau.cs.comp4583.fish;

import android.app.Application;
import android.content.Context;

public class FishApplication extends Application
{
    private static Context applicationContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        FishApplication.applicationContext = this;
    }

    public static Context getContext()
    {
        return FishApplication.applicationContext;
    }
}