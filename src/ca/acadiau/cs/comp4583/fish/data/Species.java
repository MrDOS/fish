package ca.acadiau.cs.comp4583.fish.data;

import ca.acadiau.cs.comp4583.fish.FishApplication;
import ca.acadiau.cs.comp4583.fish.R;

import com.google.gson.annotations.SerializedName;

/**
 * Species of fish recognized by the data store.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public enum Species
{
    @SerializedName("atlantic sturgeon")
    ATLANTIC_STURGEON(R.string.species_atlantic_sturgeon),

    @SerializedName("atlantic salmon")
    ATLANTIC_SALMON(R.string.species_atlantic_salmon),

    @SerializedName("dogfish")
    DOGFISH(R.string.species_dogfish),

    @SerializedName("skate")
    SKATE(R.string.species_skate),

    @SerializedName("striped bass")
    STRIPED_BASS(R.string.species_striped_bass);

    private int name;

    private Species(int name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return FishApplication.getContext().getString(this.name);
    }
}