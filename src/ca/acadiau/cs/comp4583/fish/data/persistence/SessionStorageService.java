package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import ca.acadiau.cs.comp4583.fish.R;
import ca.acadiau.cs.comp4583.fish.data.FishException;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;

/**
 * Background service to submit sessions to storage.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class SessionStorageService extends Service
{
    private static final String PERSISTENCE_FILENAME = "persisted_sessions";

    private SessionStorageBinder binder = new SessionStorageBinder();
    private SubmissionThread submissionThread;

    @Override
    public void onCreate()
    {
        (this.submissionThread = new SubmissionThread()).start();
    }

    @Override
    public void onDestroy()
    {
        this.submissionThread.interrupt();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return this.binder;
    }

    public class SessionStorageBinder extends Binder
    {
        /**
         * Enqueue a session for persistent storage. Does not necessarily store
         * the session immediately; it may be held until network access is
         * available. Fish in submitted sessions will be validated. In the event
         * of a validation error, a {@link FishException} is thrown.
         * 
         * @param session the session to be stored
         * @throws FishException if a validation error occurs with one of the
         *             fish
         */
        public void submitSession(FishingSession session) throws FishException
        {
            session.validate(true);

            SessionStorageService.this.submissionThread.sessions.add(session);
        }
    }

    /**
     * Background thread to handle actual submission of sessions to storage.
     * 
     * @since 1.0.0
     * @author Samuel Coleman <105709c@acadiau.ca>
     */
    private class SubmissionThread extends Thread
    {
        private static final int SUBMISSION_INTERVAL = 1000 * 60 * 5; /* 5 min */
        private static final int MAX_RETRIES = 3;

        private int retries;
        private LinkedList<FishingSession> sessions;
        private SessionStorageProvider sessionStorageProvider;

        public SubmissionThread()
        {
            this.retries = 0;
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

            this.sessionStorageProvider = new FishtailSessionStorageProvider(
                    getString(R.string.fishtail_endpoint),
                    secret);
        }

        @Override
        public void run()
        {
            while (!Thread.interrupted() && this.retries < SubmissionThread.MAX_RETRIES)
            {
                if (this.sessions.size() > 0
                        && this.sessionStorageProvider.isProviderAvailable())
                {
                    /* We want to work from a duplicate of the sessions list in
                     * case more session(s) are handed over while we're
                     * submitting the ones we'd already been given. */
                    LinkedList<FishingSession> submissionSessions = new LinkedList<FishingSession>(this.sessions);
                    this.sessions = new LinkedList<FishingSession>();

                    try
                    {
                        this.sessionStorageProvider.submitSessions(submissionSessions);
                    }
                    catch (IOException e)
                    {
                        /* If, for whatever reason, we can't submit the
                         * sessions, we'll hold onto them for later. */
                        this.sessions.addAll(submissionSessions);
                    }
                }

                try
                {
                    Thread.sleep(SubmissionThread.SUBMISSION_INTERVAL);
                }
                catch (InterruptedException e)
                {
                    /* If we've been woken from our slumbers, we need to break
                     * out of the loop, persist any sessions left unsubmitted,
                     * and then die. */
                    Thread.currentThread().interrupt();
                }
            }

            try
            {
                this.persistFishingSessions();
            }
            catch (IOException e)
            {
                /* Can't submit? Too bad, so sad. */
            }

            /* If we're stopping because we've run out of retries, we need to
             * notify the service that we're giving up. */
            if (this.retries >= SubmissionThread.MAX_RETRIES)
                SessionStorageService.this.stopSelf();
        }

        /**
         * Push all sessions out to file.
         * 
         * @throws IOException
         */
        private void persistFishingSessions() throws IOException
        {
            ObjectOutputStream output = new ObjectOutputStream(
                    openFileOutput(
                            SessionStorageService.PERSISTENCE_FILENAME,
                            Context.MODE_PRIVATE));
            output.writeObject(this.sessions);
            output.close();
        }

        /**
         * Read sessions in from file.
         * 
         * @throws IOException
         * @throws ClassNotFoundException
         */
        @SuppressWarnings("unchecked")
        private void loadPersistedFishingSessions() throws IOException, ClassNotFoundException
        {
            ObjectInputStream input = new ObjectInputStream(
                    openFileInput(SessionStorageService.PERSISTENCE_FILENAME));
            this.sessions = (LinkedList<FishingSession>) input.readObject();
            input.close();
        }
    }
}