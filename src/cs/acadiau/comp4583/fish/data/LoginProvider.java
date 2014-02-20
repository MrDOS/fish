package cs.acadiau.comp4583.fish.data;

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