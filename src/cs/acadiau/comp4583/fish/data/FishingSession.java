package cs.acadiau.comp4583.fish.data;

import java.sql.Date;
import java.util.ArrayList;

import android.location.Location;

/**
 * A fishing session.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class FishingSession extends ArrayList<Fish>
{
    private static final long serialVersionUID = 1L;

    private Location location;
    private Date startDate;
    private Date endDate;
    private int anglers;
    private int lines;

    /**
     * Set up the session.
     * 
     * @param location the location in which the fish was caught
     * @param startDate the start date of the session
     * @param endDate the end date of the session
     * @param anglers the number of other anglers in the area
     * @param lines the number of lines used by the session party
     */
    public FishingSession(Location location, Date startDate, Date endDate, int anglers, int lines)
    {
        super();
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.anglers = anglers;
        this.lines = lines;
    }

    /**
     * @param location the location at which the session occurred
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * @return the location at which the session occurred
     */
    public Location getLocation()
    {
        return this.location;
    }

    /**
     * @param startDate the start date of the session
     */
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * @return the start date of the session
     */
    public Date getStartDate()
    {
        return this.startDate;
    }

    /**
     * @param endDate the end date of the session
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * @return the end date of the session
     */
    public Date getEndDate()
    {
        return this.endDate;
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
}