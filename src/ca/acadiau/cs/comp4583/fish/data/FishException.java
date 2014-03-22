package ca.acadiau.cs.comp4583.fish.data;

/**
 * Validation exception for fish.
 * 
 * @author Jeremy Wheaton <105823w@acadiau.ca>
 */
public class FishException extends Exception
{
    private static final long serialVersionUID = 1L;

    private int messageRes;
    private Fish fish;
    private boolean fatal;

    public FishException(int messageRes, Fish fish, boolean fatal)
    {
        super();

        this.messageRes = messageRes;
        this.fish = fish;
        this.fatal = fatal;
    }

    public int getMessageRes()
    {
        return this.messageRes;
    }

    public Fish getFish()
    {
        return this.fish;
    }

    public boolean isFatal()
    {
        return this.fatal;
    }
}