package cs.acadiau.comp4583.fish.data;

import cs.acadiau.comp4583.fish.R;

/**
 * Categorical weights of caught fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum Weight
{
    WEIGHT_1KG(R.string.weight_1kg, 0),
    WEIGHT_6KG(R.string.weight_6kg, 0);

    private int name;
    private int databaseValue;

    private Weight(int name, int databaseValue)
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
