package cs.acadiau.comp4583.fish.data;

import cs.acadiau.comp4583.fish.R;

/**
 * Species of fish recognized by the data store.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum Species
{
    STRIPED_BASS(R.string.species_striped_bass, 0),
    SKATE(R.string.species_skate, 0),
    ATLANTIC_STURGEON(R.string.species_atlantic_sturgeon, 0),
    SALMON(R.string.species_salmon, 0);

    private int name;
    private int databaseValue;

    private Species(int name, int databaseValue)
    {
        this.name = name;
        this.databaseValue = databaseValue;
    }

    public int getName()
    {
        return this.name;
    }

    protected int getDatabaseValue()
    {
        return this.databaseValue;
    }
}