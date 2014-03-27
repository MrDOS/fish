package ca.acadiau.cs.comp4583.fish;

import java.io.Console;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import ca.acadiau.cs.comp4583.fish.data.Condition;
import ca.acadiau.cs.comp4583.fish.data.Fish;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;
import ca.acadiau.cs.comp4583.fish.data.Species;
import ca.acadiau.cs.comp4583.fish.data.TagColor;
import android.view.View.OnClickListener;

public class SubmitFishActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_fish);
		Intent thisIntent = getIntent();
		final FishingSession session = (FishingSession) thisIntent
				.getSerializableExtra("Session");
		final Button end_session_button = (Button) findViewById(R.id.end_session_button);

		final Button edit_session_button = (Button) findViewById(R.id.edit_session_button);

		final Button submit_fish_button = (Button) findViewById(R.id.submit_fish_button);

		final Spinner tagged_spinner = (Spinner) findViewById(R.id.tagged_spinner);
		// ====
		ArrayList<String> tagged_options = new ArrayList<String>();
		tagged_options.add("no");
		tagged_options.add("yes");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tagged_options);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tagged_spinner.setAdapter(dataAdapter);

		System.out.println("Test");
		// =======
		final Spinner speciesSpinner = (Spinner) findViewById(R.id.species_spinner);
		ArrayList<String> species_options = new ArrayList<String>();

		species_options.add("atlantic sturgeon");
		species_options.add("atlantic salmon");
		species_options.add("dogfish");
		species_options.add("skate");
		species_options.add("striped bass");

		ArrayAdapter<String> speciesDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, species_options);
		speciesDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		speciesSpinner.setAdapter(speciesDataAdapter);
		// =======
		final Spinner tagColorSpinner = (Spinner) findViewById(R.id.tagged_colour_spinner);
		
		ArrayList<String> tag_color_options = new ArrayList<String>();

		tag_color_options.add("blue");
		tag_color_options.add("pink");
		tag_color_options.add("red");
		tag_color_options.add("yellow");


		ArrayAdapter<String> tagColorDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, tag_color_options);
		tagColorDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tagColorSpinner.setAdapter(tagColorDataAdapter);
		// =======
		final Spinner releaseConditionText = (Spinner) findViewById(R.id.fish_release_condition_spinner);
		final Spinner catchConditionText = (Spinner) findViewById(R.id.fish_condition_spinner);

		ArrayList<String> condition_options = new ArrayList<String>();

		condition_options.add("healthy");
		condition_options.add("dead");
		condition_options.add("weak");

		ArrayAdapter<String> conditionDataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, condition_options);
		conditionDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		releaseConditionText.setAdapter(conditionDataAdapter);
		catchConditionText.setAdapter(conditionDataAdapter);
		// =======
		tagged_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				RelativeLayout tagged_yes_layout;
				RelativeLayout tagged_no_layout;
				if (tagged_spinner.getSelectedItem().toString().equals("no")) {
					tagged_no_layout = (RelativeLayout) findViewById(R.id.tagged_no);
					tagged_no_layout.setVisibility(View.VISIBLE);

					tagged_yes_layout = (RelativeLayout) findViewById(R.id.tagged_yes);
					tagged_yes_layout.setVisibility(View.GONE);
				} else {

					tagged_yes_layout = (RelativeLayout) findViewById(R.id.tagged_yes);
					tagged_yes_layout.setVisibility(View.VISIBLE);

					tagged_no_layout = (RelativeLayout) findViewById(R.id.tagged_no);
					tagged_no_layout.setVisibility(View.GONE);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		end_session_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						EndSessionActivity.class);
				i.putExtra("Session", session);
				startActivity(i);

			}
		});
		edit_session_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SessionDataActivity.class);
				i.putExtra("Session", session);
				startActivity(i);

			}
		});
		submit_fish_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Do Fish Submit Stuff Here
				// Fish(Species species, int length, boolean exactLength,
				// Condition catchHealth, Condition releaseHealth)
				String speciesName = speciesSpinner.getSelectedItem()
						.toString();
				EditText lengthText = (EditText) findViewById(R.id.species_spinner);
				int length = Integer.parseInt(lengthText.getText().toString());
				CheckBox estimateText = (CheckBox) findViewById(R.id.fish_length_check);
				Boolean estimate = estimateText.isChecked();
				String catchConditionString = catchConditionText
						.getSelectedItem().toString();
				String releaseConditionString = catchConditionText
						.getSelectedItem().toString();

				Species species = null;
				if (speciesName.equals("atlantic sturgeon"))
					species = Species.ATLANTIC_STURGEON;
				else if (speciesName.equals("atlantic salmon"))
					species = Species.ATLANTIC_SALMON;
				else if (speciesName.equals("dogfish"))
					species = Species.DOGFISH;
				else if (speciesName.equals("skate"))
					species = Species.SKATE;
				else if (speciesName.equals("striped bass"))
					species = Species.STRIPED_BASS;

				Condition catchCondition = null;
				if (catchConditionString.equals("healthy"))
					catchCondition = Condition.HEALTHY;
				else if (catchConditionString.equals("DEAD"))
					catchCondition = Condition.DEAD;
				else if (catchConditionString.equals("weak"))
					catchCondition = Condition.WEAK;

				Condition releaseCondition = null;
				if (releaseConditionString.equals("healthy"))
					releaseCondition = Condition.HEALTHY;
				else if (releaseConditionString.equals("DEAD"))
					releaseCondition = Condition.DEAD;
				else if (releaseConditionString.equals("weak"))
					releaseCondition = Condition.WEAK;

				Fish fish = new Fish(species, length, !estimate, catchCondition,
						releaseCondition);

				if (tagged_spinner.getSelectedItem().toString().equals("no")) {
					CheckBox tookSampleChbx = (CheckBox) findViewById(R.id.tagged_scaled_check);
					boolean tookSample = tookSampleChbx.isChecked();
					fish.setTookSample(tookSample);

				} else {
					String TagId;
					TagColor tagColor = null;

					EditText taggedIDText = (EditText) findViewById(R.id.tagged_id_text_edit);
					TagId = taggedIDText.getText().toString();

					
					String tagColorString = tagColorSpinner.getSelectedItem()
							.toString();

					if (tagColorString.equals("blue")) {
						tagColor = TagColor.BLUE;
					} else if (tagColorString.equals("pink")) {
						tagColor = TagColor.PINK;
					} else if (tagColorString.equals("red")) {
						tagColor = TagColor.RED;
					} else if (tagColorString.equals("yellow")) {
						tagColor = TagColor.YELLOW;
					}
					fish.setTagColor(tagColor);
				}
				session.getFish().add(fish);
			}
		});

	}

}
