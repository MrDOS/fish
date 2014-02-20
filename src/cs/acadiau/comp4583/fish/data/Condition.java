package cs.acadiau.comp4583.fish.data;

import cs.acadiau.comp4583.fish.R;

/**
 * Possible conditions of caught fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum Condition
{
    VIGOROUS(R.string.condition_vigorous, 0),
    HEALTHY(R.string.condition_healthy, 0),
    POOR(R.string.condition_poor, 0),
    MORIBUND(R.string.condition_moribund, 0);

    private int name;
    private int databaseValue;

    private Condition(int name, int databaseValue)
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
