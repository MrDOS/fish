package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import android.app.Service;
import android.content.Context;
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
    private static final int SUBMISSION_INTERVAL = 1000 * 60 * 5; /* 5 min */
    private static final String PERSISTENCE_FILENAME = "persisted_sessions";

    private LinkedList<FishingSession> sessions;
    private SessionStorageProvider sessionStorageProvider;

    @Override
    public void onCreate()
    {
        this.sessions = new LinkedList<FishingSession>();
        try
        {
            this.loadPersistedFishingSessions();
        }
        catch (IOException e)
        {
            /* If we can't load old sessions, we'll abandon them. Yay for
             * flagrant data loss! */
        }
        catch (ClassNotFoundException e)
        {
        }

        String secret = null;
        try
        {
            /* The Fishtail secret token is stored in an asset file. */
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
        try
        {
            this.persistFishingSessions();
        }
        catch (IOException e)
        {
        }
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

    private void persistFishingSessions() throws IOException
    {
        ObjectOutputStream output = new ObjectOutputStream(
                openFileOutput(
                        SessionStorageService.PERSISTENCE_FILENAME,
                        Context.MODE_PRIVATE));
        output.writeObject(this.sessions);
        output.close();
    }

    @SuppressWarnings("unchecked")
    private void loadPersistedFishingSessions() throws IOException, ClassNotFoundException
    {
        ObjectInputStream input = new ObjectInputStream(
                openFileInput(SessionStorageService.PERSISTENCE_FILENAME));
        this.sessions = (LinkedList<FishingSession>) input.readObject();
        input.close();
    }
}