package cs.acadiau.comp4583.fish.data;

import java.sql.Date;
import java.util.ArrayList;

import android.location.Location;
import cs.acadiau.comp4583.fish.R;

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

    /**
     * Makes sure that if the fish is tagged that the tag color and id are
     * filled out.
     * 
     * @param fish the current fish
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateTagged(Fish fish) throws FishException
    {
        /* if tagged is true, but tagId or tagColor is not set... */
        if (fish.isTagged() &&
                (fish.getTagId() == null || fish.getTagColor() == null))
            throw new FishException(R.string.validation_tagged_error, true);
        return true;
    }

    /**
     * Validates the tag ID to ensure its format is correct.
     * 
     * @param fish the current fish
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateTagId(Fish fish) throws FishException
    {
        if (!fish.isTagged()) return true;

        if (false) /* if the tagId does not follow our expected format... */
            throw new FishException(R.string.validation_tagid_format_unrecognised, false);
        return true;
    }

    /**
     * Validates the tag color against the fish species.
     * 
     * @param fish the current fish
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateTagColor(Fish fish) throws FishException
    {
        if (!fish.isTagged()) return true;

        if (
        /* Pink is expected to be found on skate. */
        (fish.getTagColor() == TagColor.PINK && fish.getSpecies() != Species.SKATE)
                /* Blue is expected to be found on striped bass. */
                || (fish.getTagColor() == TagColor.BLUE && fish.getSpecies() != Species.STRIPED_BASS)
                /* Yellow is expected to be found on skates or bass. */
                || (fish.getTagColor() == TagColor.YELLOW && fish.getSpecies() != Species.STRIPED_BASS && fish
                        .getSpecies() != Species.SKATE)
        /* Orange and green can be found on any */
        )
            throw new FishException(R.string.validation_tagcolor_mismatch, false);

        return true;
    }

    /**
     * Validates the fish length to ensure that its length is not outside the
     * expected range.
     * 
     * @param fish the current fish
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateFishLength(Fish fish) throws FishException
    {
        if (false) /* if the given length is too large... */
            throw new FishException(R.string.validation_length_too_large,
                    false);
        else if (false) /* if the given length is too small... */
            throw new FishException(R.string.validation_length_too_small,
                    false);
        return true;
    }
}