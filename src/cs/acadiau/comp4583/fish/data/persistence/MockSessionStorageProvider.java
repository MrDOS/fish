package cs.acadiau.comp4583.fish.data.persistence;

import java.util.LinkedList;

import cs.acadiau.comp4583.fish.data.FishingSession;

/**
 * A mock session storage mechanism.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class MockSessionStorageProvider implements SessionStorageProvider
{
    private LinkedList<FishingSession> sessions = new LinkedList<FishingSession>();

    @Override
    public boolean isProviderAvailable()
    {
        return true;
    }

    @Override
    public void submitSession(FishingSession session)
    {
        this.sessions.add(session);
    }

    /**
     * Get all sessions that have been submitted to the provider.
     * 
     * @return all sessions that have been submitted to the provider
     */
    public LinkedList<FishingSession> getSessions()
    {
        return this.sessions;
    }
}