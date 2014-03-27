package ca.acadiau.cs.comp4583.fish.data.persistence;

import ca.acadiau.cs.comp4583.fish.data.User;

/**
 * Defines the interface by which users are authenticated.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public interface LoginHandler
{
    /**
     * Called when the login was successful.
     * 
     * @param user the authenticated user
     */
    public void onLoginSuccess(User user);

    /**
     * Called when the login was unsuccessful.
     * 
     * @param messageRes the message resource indicating the reason for failure
     */
    public void onLoginFailure(int messageRes);
}