package ca.acadiau.cs.comp4583.fish.data.persistence;

import ca.acadiau.cs.comp4583.fish.data.AuthenticationException;
import ca.acadiau.cs.comp4583.fish.data.User;

/**
 * A login provider which approves all credentials.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class MockLoginProvider implements LoginProvider
{
    /**
     * Authenticate any set of user credentials.
     * 
     * @param username the username
     * @param password the password
     * @return an empty user
     * @throws AuthenticationException if the credentials are rejected
     */
    @Override
    public User validate(String username, String password) throws AuthenticationException
    {
        return new User();
    }
}