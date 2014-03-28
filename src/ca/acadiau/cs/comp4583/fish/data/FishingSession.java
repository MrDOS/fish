package ca.acadiau.cs.comp4583.fish.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A fishing session.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class FishingSession implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String username;
    private Double latitude;
    private Double longitude;
    private String locationName;
    private long startDate;
    private long endDate;
    private int anglers;
    private boolean exactAnglers;
    private int lines;
    private int catches;
    private boolean exactCatches;

    private ArrayList<Fish> fish;

    /**
     * Set up the session.
     * 
     * @param latitude the lat in which the fish was caught
     * @param logitude the long in which the fish was caught
     * @param startDate the start date of the session
     * @param endDate the end date of the session
     * @param anglers the number of other anglers in the area
     * @param lines the number of lines used by the session party
     */
    public FishingSession(Double latitude, Double longitude,
            long startDate, long endDate, int anglers, int lines)
    {
        super();
        this.username = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.anglers = anglers;
        this.lines = lines;

        this.fish = new ArrayList<Fish>();
    }

    /**
     * @param username the associated TrackMyFish username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the associated TrackMyFish username
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Set the latitude of the location at which the session occurred. Setting
     * this property will set the location name to null.
     * 
     * @param latitude the location at which the session occurred
     */
    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
        this.locationName = null;
    }

    /**
     * @return the location at which the session occurred
     */
    public Double getLatitude()
    {
        return this.latitude;
    }

    /**
     * Set the longitude of the location at which the session occurred. Setting
     * this property will set the location name to null.
     * 
     * @param location the location at which the session occurred
     */
    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
        this.locationName = null;
    }

    /**
     * @return the location at which the session occurred
     */
    public Double getLongitude()
    {
        return this.longitude;
    }

    /**
     * Set the name of the location. Setting this property will set the latitude
     * and longitude to null.
     * 
     * @param locationName the name of the location
     */
    public void setLocationName(String locationName)
    {
        this.latitude = null;
        this.longitude = null;
        this.locationName = locationName;
    }

    /**
     * @return the name of the location
     */
    public String getLocationName()
    {
        return this.locationName;
    }

    /**
     * @param startDate the start date of the session
     */
    public void setStartDate(long startDate)
    {
        this.startDate = startDate;
    }

    /**
     * @return the start date of the session
     */
    public long getStartDate()
    {
        return this.startDate;
    }

    /**
     * @param endDate the end date of the session
     */
    public void setEndDate(long endDate)
    {
        this.endDate = endDate;
    }

    /**
     * @return the end date of the session
     */
    public long getEndDate()
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
     * @param anglers the number of other anglers in the area
     */
    public void setAnglers(int anglers)
    {
        this.anglers = anglers;
    }

    /**
     * Returns the val for if the number of anglers given is an exact amount
     * 
     * @return exactAnglers the bool for if its exact
     */
    public boolean isExactAnglers()
    {
        return exactAnglers;
    }

    /**
     * @param exactAnglers if anglers are exact
     */
    public void setExactAnglers(boolean exactAnglers)
    {
        this.exactAnglers = exactAnglers;
    }

    /**
     * @return the number of lines used by the session party
     */
    public int getLines()
    {
        return this.lines;
    }

    /**
     * @param lines the number of lines used by the session party
     */
    public void setLines(int lines)
    {
        this.lines = lines;
    }

    /**
     * @return catches the given number of fish caught
     */
    public int getCatches()
    {
        return catches;
    }

    /**
     * @param catches the number of fish caught
     */
    public void setCatches(int catches)
    {
        this.catches = catches;
    }

    /**
     * @return exact catches is catches given exact
     */
    public boolean isExactCatches()
    {
        return exactCatches;
    }

    /**
     * @param exactCatches set whether or not catches is exact
     */
    public void setExactCatches(boolean exactCatches)
    {
        this.exactCatches = exactCatches;
    }

    /**
     * Access the array list containing fish. Manipulating this list adds or
     * removes fish from the session.
     * 
     * @return the list containing fish
     */
    public ArrayList<Fish> getFish()
    {
        return this.fish;
    }

    /**
     * Validate all fish within the session. This is equivalent to iterating
     * over all fish and manually calling {@link Fish#validate(boolean)} on each
     * one.
     * 
     * @param onlyFatal only throw an exception upon fatal validation errors
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validate(boolean onlyFatal) throws FishException
    {
        for (Fish fish : this.fish)
            fish.validate(onlyFatal);

        return true;
    }
}