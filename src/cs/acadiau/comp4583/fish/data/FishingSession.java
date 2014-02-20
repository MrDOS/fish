package cs.acadiau.comp4583.fish.data;

import java.sql.Date;
import java.util.LinkedList;

import android.location.Location;
import cs.acadiau.comp4583.fish.R;

/**
 * A fishing session.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class FishingSession
{
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int HOURS_PER_DAY = 24;

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

    /**
     * Validate the location of the current fish against the last fish.
     * 
     * @param fish the current fish
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateLocation(Fish fish) throws FishException
    {
        if (this.fish.size() == 0) return true;

        Location current = fish.getLocation();
        Location last = this.fish.getLast().getLocation();
        if (last.distanceTo(current) > 50 * 1000) /* if distance from
                                                   * this.fish.getLast() to the
                                                   * location in
                                                   * this fish is >50km... */
            throw new FishException(R.string.validation_location_distant,
                    false);
        else if (last.distanceTo(current) > 100 * 1000) /* if distance from
                                                         * this.fish.getLast()
                                                         * to the location
                                                         * in this fish is
                                                         * >100km... */
            throw new FishException(R.string.validation_location_incorrect,
                    true);

        return true;
    }

    /**
     * Validate the date of the current fish against the last fish.
     * 
     * @param fish the current fish
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateDate(Fish fish) throws FishException
    {
        if (this.fish.size() == 0) return true;

        Date current = fish.getDate();
        Date last = this.fish.getLast().getDate();

        /* if date/time from this.fish.getLast() is far before... */
        if ((current.getTime() - last.getTime() / 1000) < 1 * SECONDS_PER_HOUR)
            throw new FishException(R.string.validation_date_far_before, false);
        /* if date/time from this.fish.getLast() is far after... */
        else if ((current.getTime() - last.getTime() / 1000) > 3 * SECONDS_PER_HOUR)
            throw new FishException(R.string.validation_date_far_after, false);

        return true;
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

    /**
     * Validates the fish weight to ensure that its weight is not outside the
     * expected range.
     * 
     * @param fish the current fish
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateFishWeight(Fish fish) throws FishException
    {
        if (false) /* if the given weight is too large... */
            throw new FishException(R.string.validation_weight_too_large,
                    false);
        else if (false) /* if the given weight is too small... */
            throw new FishException(R.string.validation_weight_too_small,
                    false);
        return true;
    }
}