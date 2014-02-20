package cs.acadiau.comp4583.fish.data;

/**
 * Validation exception for fish.
 * 
 * @author Jeremy Wheaton <105823w@acadiau.ca>
 */
public class FishException extends Exception
{
    private static final long serialVersionUID = 1L;

    private int messageRes;
    private boolean fatal;

    public FishException(int messageRes, boolean fatal)
    {
        super();

        this.messageRes = messageRes;
        this.fatal = fatal;
    }

    public int getMessageRes()
    {
        return this.messageRes;
    }

    public boolean isFatal()
    {
        return this.fatal;
    }
}