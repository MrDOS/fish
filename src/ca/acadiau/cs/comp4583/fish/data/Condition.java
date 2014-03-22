package ca.acadiau.cs.comp4583.fish.data;

import ca.acadiau.cs.comp4583.fish.R;

import com.google.gson.annotations.SerializedName;

/**
 * Possible conditions of caught fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum Condition
{
    @SerializedName("healthy")
    HEALTHY(R.string.condition_healthy),

    @SerializedName("dead")
    DEAD(R.string.condition_dead),

    @SerializedName("weak")
    WEAK(R.string.condition_weak);

    private int name;

    private Condition(int name)
    {
        this.name = name;
    }

    public int getName()
    {
        return this.name;
    }
}
