package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.util.LinkedList;
import java.util.List;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;

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
    public void submitSessions(List<FishingSession> sessions)
    {
        this.sessions.addAll(sessions);
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