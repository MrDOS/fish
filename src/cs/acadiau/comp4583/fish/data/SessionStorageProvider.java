package cs.acadiau.comp4583.fish.data;

/**
 * Defines the interface by which sessions are stored.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public interface SessionStorageProvider
{
    /**
     * Submit a session to remote, persistent storage.
     * 
     * @param session the finished session
     */
    public void submitSession(FishingSession session);
}