package ca.acadiau.cs.comp4583.fish.data;

/**
 * An exception occurring while authenticating a user.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class AuthenticationException extends Exception
{
    private static final long serialVersionUID = 1L;

    private int messageRes;

    /**
     * Construct the exception with a message.
     * 
     * @param messageRes the message resource
     */
    public AuthenticationException(int messageRes)
    {
        super();

        this.messageRes = messageRes;
    }

    /**
     * @return the message resource
     */
    public int getMessageRes()
    {
        return this.messageRes;
    }
}