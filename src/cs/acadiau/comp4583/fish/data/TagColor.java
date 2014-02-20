package cs.acadiau.comp4583.fish.data;

import cs.acadiau.comp4583.fish.R;

/**
 * Tag colors of interest.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum TagColor
{
    PINK(R.string.color_pink, 0),
    BLUE(R.string.color_blue, 0),
    YELLOW(R.string.color_yellow, 0),
    ORANGE(R.string.color_orange, 0),
    GREEN(R.string.color_green, 0);

    private int name;
    private int databaseValue;

    private TagColor(int name, int databaseValue)
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
