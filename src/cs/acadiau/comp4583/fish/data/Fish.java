package cs.acadiau.comp4583.fish.data;

import java.sql.Date;

import android.location.Location;

/**
 * A caught fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class Fish
{
    private Location location;
    private Date date;
    private Species species;
    private boolean tagged;
    private String tagId;
    private TagColor tagColor;
    private boolean tookSample;
    private Length length;
    private Weight weight;
    private Condition condition;

    /**
     * Define a caught fish.
     * 
     * @param location the location in which the fish was caught
     * @param date the date and time at which the fish was caught
     * @param species the species of fish
     * @param tagId the ID of the fish tag
     * @param tagColor the color of the fish tag
     * @param length the length of the fish
     * @param weight the weight of the fish
     * @param condition the condition of the fish
     */
    public Fish(Location location, Date date, Species species, String tagId,
            TagColor tagColor, Length length, Weight weight,
            Condition condition)
    {
        this.location = location;
        this.date = date;
        this.species = species;
        this.tagged = true;
        this.tagId = tagId;
        this.tagColor = tagColor;
        this.tookSample = false;
        this.length = length;
        this.weight = weight;
        this.condition = condition;
    }

    /**
     * Define a caught fish.
     * 
     * @param location the location in which the fish was caught
     * @param date the date and time at which the fish was caught
     * @param species the species of fish
     * @param tookSample whether or not a sample was taken
     * @param length the length of the fish
     * @param weight the weight of the fish
     * @param condition the condition of the fish
     */
    public Fish(Location location, Date date, Species species,
            boolean tookSample, Length length, Weight weight,
            Condition condition)
    {
        this.location = location;
        this.date = date;
        this.species = species;
        this.tagged = false;
        this.tagId = null;
        this.tagColor = null;
        this.tookSample = tookSample;
        this.length = length;
        this.weight = weight;
        this.condition = condition;
    }

    /**
     * @return the location in which the fish was caught
     */
    public Location getLocation()
    {
        return this.location;
    }

    /**
     * @return the date and time at which the fish was caught
     */
    public Date getDate()
    {
        return this.date;
    }

    /**
     * @return the species of fish
     */
    public Species getSpecies()
    {
        return this.species;
    }

    /**
     * @return whether or not the fish is tagged
     */
    public boolean isTagged()
    {
        return this.tagged;
    }

    /**
     * @return the ID of the fish tag
     */
    public String getTagId()
    {
        return this.tagId;
    }

    /**
     * @return the color of the fish tag
     */
    public TagColor getTagColor()
    {
        return this.tagColor;
    }

    /**
     * @return whether or not a sample was taken
     */
    public boolean tookSample()
    {
        return this.tookSample;
    }

    /**
     * @return the length of the fish
     */
    public Length getLength()
    {
        return this.length;
    }

    /**
     * @return the weight of the fish
     */
    public Weight getWeight()
    {
        return this.weight;
    }

    /**
     * @return the condition of the fish
     */
    public Condition getCondition()
    {
        return this.condition;
    }
}