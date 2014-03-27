package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.util.HashSet;

import ca.acadiau.cs.comp4583.fish.data.User;

/**
 * Provides login handler registration facilities for login providers.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public abstract class AbstractLoginProvider implements LoginProvider
{
    private HashSet<LoginHandler> handlers = new HashSet<LoginHandler>();

    /**
     * Add a login event handler.
     * 
     * @param handler the login event handler to be added
     */
    public void addLoginHandler(LoginHandler handler)
    {
        this.handlers.add(handler);
    }

    /**
     * Remove a login event handler.
     * 
     * @param handler the login event handler to remove
     */
    public void removeLoginHandler(LoginHandler handler)
    {
        this.handlers.remove(handler);
    }

    /**
     * Notify associated handlers of login success.
     * 
     * @param user the authenticated user
     */
    protected void notifyLoginSuccess(User user)
    {
        for (LoginHandler handler : this.handlers)
            handler.onLoginSuccess(user);
    }

    /**
     * Notify associated handlers of login failure.
     * 
     * @param messageRes the message resource indicating the reason for failure
     */
    protected void notifyLoginFailure(int messageRes)
    {
        for (LoginHandler handler : this.handlers)
            handler.onLoginFailure(messageRes);
    }
}