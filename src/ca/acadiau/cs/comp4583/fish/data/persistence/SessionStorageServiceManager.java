package ca.acadiau.cs.comp4583.fish.data.persistence;

import ca.acadiau.cs.comp4583.fish.data.Fish;
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
     * Enqueue a session for persistant storage. Does not necessarily store the
     * session immediately; it may be held until network access is available.
     * Fish in submitted sessions will be validated. In the event of a
     * validation error, a {@link FishException} is thrown.
     * 
     * @param session the session to be stored
     * @throws FishException if a validation error occurs with one of the fish
     */
    public void submitSession(FishingSession session) throws FishException
    {
        for (Fish fish : session.getFish())
        {
            fish.validate(true);
        }

        /* TODO: Bind service, submit session. */
    }
}