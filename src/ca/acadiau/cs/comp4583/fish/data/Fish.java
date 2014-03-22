package ca.acadiau.cs.comp4583.fish.data;

import java.io.Serializable;

import ca.acadiau.cs.comp4583.fish.R;

/**
 * A caught fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 * @author Jeremy Wheaton <105823w@acadiau.ca>
 */
public class Fish implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Species species;
    private boolean tagged;
    private String tagId;
    private TagColor tagColor;
    private boolean tookSample;
    private int length;
    private Condition condition;

    /**
     * Define a caught fish.
     * 
     * @param species the species of fish
     * @param length the length of the fish in millimetres
     * @param condition the condition of the fish
     */
    public Fish(Species species, int length, Condition condition)
    {
        this.species = species;
        this.tagged = false;
        this.tagId = null;
        this.tagColor = null;
        this.tookSample = false;
        this.length = length;
        this.condition = condition;
    }

    /**
     * @param species the species of fish
     */
    public void setSpecies(Species species)
    {
        this.species = species;
    }

    /**
     * @return the species of fish
     */
    public Species getSpecies()
    {
        return this.species;
    }

    /**
     * @param tagged whether or not the fish is tagged
     */
    public void setTagged(boolean tagged)
    {
        this.tagged = tagged;
    }

    /**
     * @return whether or not the fish is tagged
     */
    public boolean isTagged()
    {
        return this.tagged;
    }

    /**
     * @param tagId the fish tag ID
     */
    public void setTagId(String tagId)
    {
        this.tagId = tagId;
    }

    /**
     * @return the fish tag ID
     */
    public String getTagId()
    {
        return this.tagId;
    }

    /**
     * @param tagColor the colour of the fish tag
     */
    public void setTagColor(TagColor tagColor)
    {
        this.tagColor = tagColor;
    }

    /**
     * @return the color of the fish tag
     */
    public TagColor getTagColor()
    {
        return this.tagColor;
    }

    /**
     * @param tookSample whether or not a scale sample was taken
     */
    public void setTookSample(boolean tookSample)
    {
        this.tookSample = tookSample;
    }

    /**
     * @return whether or not a scale sample was taken
     */
    public boolean tookSample()
    {
        return this.tookSample;
    }

    /**
     * @param length the length of the fish in millimetres
     */
    public void setLength(int length)
    {
        this.length = length;
    }

    /**
     * @return the length of the fish in millimetres
     */
    public double getLength()
    {
        return this.length;
    }

    /**
     * @param condition the condition of the fish
     */
    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }

    /**
     * @return the condition of the fish
     */
    public Condition getCondition()
    {
        return this.condition;
    }

    /**
     * Make sure all potentially-invalid fields validate.
     * 
     * @param onlyFatal only throw an exception upon fatal validation errors
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validate(boolean onlyFatal) throws FishException
    {
        if (onlyFatal) return this.validateFatal();
        return this.validateAll();
    }

    private boolean validateAll() throws FishException
    {
        this.validateFishLength();
        this.validateTagged();
        this.validateTagId();
        this.validateTagColor();

        return true;
    }

    private boolean validateFatal() throws FishException
    {
        try
        {
            this.validateFishLength();
        }
        catch (FishException e)
        {
            if (e.isFatal()) throw e;
        }

        try
        {
            this.validateTagged();
        }
        catch (FishException e)
        {
            if (e.isFatal()) throw e;
        }

        try
        {
            this.validateTagId();
        }
        catch (FishException e)
        {
            if (e.isFatal()) throw e;
        }

        try
        {
            this.validateTagColor();
        }
        catch (FishException e)
        {
            if (e.isFatal()) throw e;
        }

        return true;
    }

    /**
     * Makes sure that if the fish is tagged that the tag color and ID are
     * filled out.
     * 
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateTagged() throws FishException
    {
        /* if tagged is true, but tagId or tagColor is not set... */
        if (this.tagged && (this.tagId == null || this.tagColor == null))
            throw new FishException(R.string.validation_tagged_error, this, true);
        return true;
    }

    /**
     * Validates the tag ID to ensure its format is correct.
     * 
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateTagId() throws FishException
    {
        if (!this.tagged) return true;

        if (false) /* if the tagId does not follow our expected format... */
            throw new FishException(R.string.validation_tagid_format_unrecognised, this, false);
        return true;
    }

    /**
     * Validates the tag color against the fish species.
     * 
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateTagColor() throws FishException
    {
        if (!this.tagged) return true;

        if (
        /* Pink is expected to be found on skate. */
        (this.tagColor == TagColor.PINK && this.species != Species.SKATE)
                /* Blue is expected to be found on striped bass. */
                || (this.tagColor == TagColor.BLUE && this.species != Species.STRIPED_BASS)
                /* Yellow is expected to be found on skates or bass. */
                || (this.tagColor == TagColor.YELLOW && this.species != Species.STRIPED_BASS && this.species != Species.SKATE)
        /* Orange and green can be found on any */
        )
            throw new FishException(R.string.validation_tagcolor_mismatch, this, false);

        return true;
    }

    /**
     * Validates the fish length to ensure that its length is not outside the
     * expected range.
     * 
     * @return true
     * @throws FishException in the event of improper data
     */
    public boolean validateFishLength() throws FishException
    {
        if (false) /* if the given length is too large... */
            throw new FishException(R.string.validation_length_too_large, this, false);
        else if (false) /* if the given length is too small... */
            throw new FishException(R.string.validation_length_too_small, this, false);
        return true;
    }
}