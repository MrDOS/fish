package cs.acadiau.comp4583.fish.data;

import java.sql.Date;

import android.location.Location;

/**
 * A caught fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 * @author Jeremy Wheaton <105823w@acadiau.ca>
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
     * @param length the length of the fish
     * @param weight the weight of the fish
     * @param condition the condition of the fish
     */
    public Fish(Location location, Date date, Species species, Length length,
            Weight weight, Condition condition)
    {
        this.location = location;
        this.date = date;
        this.species = species;
        this.tagged = false;
        this.tagId = null;
        this.tagColor = null;
        this.tookSample = false;
        this.length = length;
        this.weight = weight;
        this.condition = condition;
    }

    /**
     * @param The location set in the GUI
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * @return the location in which the fish was caught
     */
    public Location getLocation()
    {
        return this.location;
    }

    /**
     * @param the date determined in the GUI
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * @return the date and time at which the fish was caught
     */
    public Date getDate()
    {
        return this.date;
    }

    /**
     * @param the species given by the angler
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
     * @param the tagged value given by the angler
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
     * @param the tagId entered by the angler
     */
    public void setTagId(String tagId)
    {
        this.tagId = tagId;
    }

    /**
     * @return the ID of the fish tag
     */
    public String getTagId()
    {
        return this.tagId;
    }

    /**
     * @param the tag color entered by the angler
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
     * @param the value for took sample given by the angler
     */
    public void setTookSample(boolean tookSample)
    {
        this.tookSample = tookSample;
    }

    /**
     * @return whether or not a sample was taken
     */
    public boolean tookSample()
    {
        return this.tookSample;
    }

    /**
     * @param The length value given by the angler
     */
    public void setLength(Length length)
    {
        this.length = length;
    }

    /**
     * @return the length of the fish
     */
    public Length getLength()
    {
        return this.length;
    }

    /**
     * @param the weight given by the angler
     */
    public void setWeight(Weight weight)
    {
        this.weight = weight;
    }

    /**
     * @return the weight of the fish
     */
    public Weight getWeight()
    {
        return this.weight;
    }

    /**
     * @param the condition of the fish as determined by the angler
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
}