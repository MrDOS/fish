package ca.acadiau.cs.comp4583.fish;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


		// =======
		final Spinner speciesSpinner = (Spinner) findViewById(R.id.species_spinner);

		ArrayAdapter<Species> speciesDataAdapter = new ArrayAdapter<Species>(
				this, android.R.layout.simple_spinner_item, Species.values());
		speciesDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		speciesSpinner.setAdapter(speciesDataAdapter);
		// =======
		final Spinner tagColorSpinner = (Spinner) findViewById(R.id.tagged_colour_spinner);
		
		ArrayAdapter<TagColor> tagColorDataAdapter = new ArrayAdapter<TagColor>(
				this, android.R.layout.simple_spinner_item, TagColor.values());
		tagColorDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tagColorSpinner.setAdapter(tagColorDataAdapter);
		// =======
		final Spinner releaseConditionSpinner = (Spinner) findViewById(R.id.fish_release_condition_spinner);
		final Spinner catchConditionSpinner = (Spinner) findViewById(R.id.fish_condition_spinner);

		ArrayAdapter<Condition> conditionDataAdapter = new ArrayAdapter<Condition>(
				this, android.R.layout.simple_spinner_item, Condition.values());
		conditionDataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		releaseConditionSpinner.setAdapter(conditionDataAdapter);
		catchConditionSpinner.setAdapter(conditionDataAdapter);
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
				EditText lengthText = (EditText) findViewById(R.id.fish_length_text_edit);
				int length = Integer.parseInt(lengthText.getText().toString());
				CheckBox estimateText = (CheckBox) findViewById(R.id.fish_length_check);
				Boolean estimate = estimateText.isChecked();

				Species species = (Species) speciesSpinner.getSelectedItem();
				Condition catchCondition = (Condition) catchConditionSpinner.getSelectedItem();
				Condition releaseCondition = (Condition) releaseConditionSpinner.getSelectedItem();

				Fish fish = new Fish(species, length, !estimate, catchCondition,
						releaseCondition);

				if (tagged_spinner.getSelectedItem().toString().equals("no")) {
					CheckBox tookSampleChbx = (CheckBox) findViewById(R.id.tagged_scaled_check);
					boolean tookSample = tookSampleChbx.isChecked();
					fish.setTookSample(tookSample);

				} else {
					String TagId;
					TagColor tagColor = (TagColor) tagColorSpinner.getSelectedItem();

					EditText taggedIDText = (EditText) findViewById(R.id.tagged_id_text_edit);
					TagId = taggedIDText.getText().toString();

					fish.setTagColor(tagColor);
				}
				session.getFish().add(fish);
			}
		});

	}

}
