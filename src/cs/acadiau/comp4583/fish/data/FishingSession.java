package cs.acadiau.comp4583.fish.data;

import java.util.LinkedList;

/**
 * A fishing session.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class FishingSession
{
    private int anglers;
    private int lines;
    private LinkedList<Fish> fish;

    /**
     * Set up the session.
     * 
     * @param anglers the number of other anglers in the area
     * @param lines the number of lines used by the session party
     */
    public FishingSession(int anglers, int lines)
    {
        this.anglers = anglers;
        this.lines = lines;
        this.fish = new LinkedList<Fish>();
    }

    /**
     * @return the number of other anglers in the area
     */
    public int getAnglers()
    {
        return this.anglers;
    }

    /**
     * @return the number of lines used by the session party
     */
    public int getLines()
    {
        return this.lines;
    }

    /**
     * Get fish in the session.
     * 
     * @return fish in the session
     */
    public LinkedList<Fish> getFish()
    {
        return this.fish;
    }

    /**
     * Add a fish to the session.
     * 
     * @param fish the fish to add to the session
     */
    public void addFish(Fish fish)
    {
        this.fish.add(fish);
    }
}