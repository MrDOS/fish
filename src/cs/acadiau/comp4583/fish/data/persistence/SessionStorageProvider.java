package cs.acadiau.comp4583.fish.data.persistence;

import cs.acadiau.comp4583.fish.data.FishingSession;

/**
 * Defines the interface by which sessions are stored.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public interface SessionStorageProvider
{
    /**
     * Determine whether or not the current network situation permits access to
     * the storage provider.
     * 
     * @return whether or not the storage provider is accessible
     */
    public boolean isProviderAvailable();

    /**
     * Submit a session to remote, persistent storage.
     * 
     * @param session the finished session
     */
    public void submitSession(FishingSession session);
}