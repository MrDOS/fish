package cs.acadiau.comp4583.fish.data.persistence;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Background service to submit sessions to storage.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class SessionStorageService extends Service
{
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        /* TODO: Load previously-unsubmitted sessions from local persistence;
         * construct SessionStorageProvider. */

        return 0;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}