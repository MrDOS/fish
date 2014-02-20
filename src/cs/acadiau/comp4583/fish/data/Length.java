package cs.acadiau.comp4583.fish.data;

import cs.acadiau.comp4583.fish.R;

/**
 * Categorical lengths of caught fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum Length
{
    LENGTH_5CM(R.string.length_5cm, 0),
    LENGTH_11CM(R.string.length_11cm, 0);

    private int name;
    private int databaseValue;

    private Length(int name, int databaseValue)
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
