package ca.acadiau.cs.comp4583.fish.data;

/**
 * A Track My Fish user.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class User
{
    private final String username;

    public User(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return this.username;
    }
}