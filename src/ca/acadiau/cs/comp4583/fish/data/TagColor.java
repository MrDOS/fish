package ca.acadiau.cs.comp4583.fish.data;

import ca.acadiau.cs.comp4583.fish.FishApplication;
import ca.acadiau.cs.comp4583.fish.R;

import com.google.gson.annotations.SerializedName;

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

    @Override
    public String toString()
    {
        return FishApplication.getContext().getString(this.name);
    }
}
