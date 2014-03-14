package cs.acadiau.comp4583.fish.data;

import com.google.gson.annotations.SerializedName;

import cs.acadiau.comp4583.fish.R;

/**
 * Tag colors of interest.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum TagColor
{
    @SerializedName("blue")
    BLUE(R.string.color_blue),

    @SerializedName("pink")
    PINK(R.string.color_pink),

    @SerializedName("red")
    RED(R.string.color_red),

    @SerializedName("yellow")
    YELLOW(R.string.color_yellow);

    private int name;

    private TagColor(int name)
    {
        this.name = name;
    }

    public int getName()
    {
        return this.name;
    }
}
