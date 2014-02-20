package cs.acadiau.comp4583.fish.data;

import java.util.LinkedList;

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