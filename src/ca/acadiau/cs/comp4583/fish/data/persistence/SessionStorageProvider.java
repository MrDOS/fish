package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.util.List;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;

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
     * Submit a set of sessions to remote, persistent storage.
     * 
     * @param sessions the finished sessions
     * @throws IOException in the event of a failure during submission
     */
    public void submitSessions(List<FishingSession> sessions) throws IOException;
}