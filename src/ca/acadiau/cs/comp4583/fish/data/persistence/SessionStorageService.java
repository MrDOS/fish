package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import ca.acadiau.cs.comp4583.fish.R;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;

/**
 * Background service to submit sessions to storage.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class SessionStorageService extends Service
{
    private LinkedList<FishingSession> sessions;
    private SessionStorageProvider sessionStorageProvider;

    @Override
    public void onCreate()
    {
        this.loadPersistedFishingSessions();

        String secret = null;
        /* The Fishtail secret token is stored in an asset file. */
        try
        {
            InputStream secretStream = getResources().getAssets().open("fishtail_secret");
            secret = FishtailSessionStorageProvider.loadSecret(secretStream);
            secretStream.close();
        }
        catch (IOException e)
        {
        }

        this.sessionStorageProvider = new FishtailSessionStorageProvider(getString(R.string.fishtail_endpoint), secret);
    }

    @Override
    public void onDestroy()
    {
        this.persistFishingSessions();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return 0;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void submitSession(FishingSession session)
    {
        this.sessions.add(session);
    }

    private void loadPersistedFishingSessions()
    {
        /* TODO: restore this.sessions from file */
    }

    private void persistFishingSessions()
    {
        /* TODO: persist this.sessions to file */
    }
}