package ca.acadiau.cs.comp4583.fish.data.persistence;

import ca.acadiau.cs.comp4583.fish.data.AuthenticationException;
import ca.acadiau.cs.comp4583.fish.data.User;

/**
 * Defines the interface by which users are authenticated.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public interface LoginProvider
{
    /**
     * Authenticate a set of user credentials.
     * 
     * @param username the username
     * @param password the password
     * @return the authenticated user
     * @throws AuthenticationException if the credentials are rejected
     */
    public User validate(String username, String password) throws AuthenticationException;
}