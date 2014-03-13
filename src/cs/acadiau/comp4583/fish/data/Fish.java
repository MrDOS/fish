package cs.acadiau.comp4583.fish.data;

import java.io.Serializable;

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
}