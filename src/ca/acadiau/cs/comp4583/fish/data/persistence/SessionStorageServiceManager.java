package ca.acadiau.cs.comp4583.fish.data.persistence;

import ca.acadiau.cs.comp4583.fish.data.FishException;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;

/**
 * Mediate access between the session storage service and the UI.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class SessionStorageServiceManager
{
    /**
     * Convenience wrapper around submission to the session storage service.
     * 
     * @see SessionStorageService#submitSession(FishingSession)
     * @param session the session to be stored
     * @throws FishException if a validation error occurs with one of the fish
     */
    public void submitSession(FishingSession session) throws FishException
    {
        /* TODO: Bind service, submit session. */
    }
}