package ca.acadiau.cs.comp4583.fish;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ScrollView;
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
		tagged_options.add(getString(R.string.no));
		tagged_options.add(getString(R.string.yes));

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
        final CheckBox estimateText = (CheckBox) findViewById(R.id.fish_length_check);
        estimateText.setChecked(true);
		// =======
        final Spinner catchConditionSpinner = (Spinner) findViewById(R.id.fish_condition_spinner);
		final Spinner releaseConditionSpinner = (Spinner) findViewById(R.id.fish_release_condition_spinner);

		ArrayAdapter<Condition> conditionDataAdapter = new ArrayAdapter<Condition>(
				this, android.R.layout.simple_spinner_item, Condition.values());
		conditionDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catchConditionSpinner.setAdapter(conditionDataAdapter);
		releaseConditionSpinner.setAdapter(conditionDataAdapter);
		
        catchConditionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                /* If the catch condition is "dead", the release condition must
                 * also be "dead". */
                if (((Condition) parent.getSelectedItem()).equals(Condition.DEAD))
                {
                    releaseConditionSpinner.setEnabled(false);
                    releaseConditionSpinner.setSelection(parent.getSelectedItemPosition());
                }
                /* When re-enabling the release condition spinner, we'll set its
                 * value to match the catch condition. */
                else if (!releaseConditionSpinner.isEnabled())
                {
                    releaseConditionSpinner.setEnabled(true);
                    releaseConditionSpinner.setSelection(parent.getSelectedItemPosition());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
		// =======
		tagged_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				RelativeLayout tagged_yes_layout;
				RelativeLayout tagged_no_layout;
				if (tagged_spinner.getSelectedItem().toString().equals(getString(R.string.no))) {
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
                finish();

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
				/* Length is given in cm but must be passed to storage as mm. */
				int length = 0;
				try
				{
					length = (int) (Double.parseDouble(lengthText.getText().toString()) * 100);
				}
				catch (NumberFormatException e) {

					System.out.println(e.getMessage());
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
							SubmitFishActivity.this);
					alertBuilder
							.setMessage("Length must have a valid number.");
					alertBuilder.setCancelable(true);
					alertBuilder.setPositiveButton("Okay",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
					AlertDialog numberAlert = alertBuilder.create();
					numberAlert.show();
					return;
				}
				Boolean estimate = estimateText.isChecked();

				Species species = (Species) speciesSpinner.getSelectedItem();
				Condition catchCondition = (Condition) catchConditionSpinner.getSelectedItem();
				Condition releaseCondition = (Condition) releaseConditionSpinner.getSelectedItem();

				Fish fish = new Fish(species, length, !estimate, catchCondition,
						releaseCondition);

				if (tagged_spinner.getSelectedItem().toString().equals(getString(R.string.no))) {
					CheckBox tookSampleChbx = (CheckBox) findViewById(R.id.tagged_scaled_check);
					boolean tookSample = tookSampleChbx.isChecked();
					fish.setTookSample(tookSample);

				} else {
					EditText taggedIDText = (EditText) findViewById(R.id.tagged_id_text_edit);
                    String tagId = taggedIDText.getText().toString();

                    TagColor tagColor = (TagColor) tagColorSpinner.getSelectedItem();

                    fish.setTagId(tagId);
					fish.setTagColor(tagColor);
				}
				session.getFish().add(fish);

				SubmitFishActivity.this.reset();
			}
		});

	}

    private void reset()
    {
        ((Spinner) findViewById(R.id.tagged_spinner)).setSelection(0);
        ((CheckBox) findViewById(R.id.tagged_scaled_check)).setChecked(false);
        ((EditText) findViewById(R.id.fish_length_text_edit)).getEditableText().clear();
        ((CheckBox) findViewById(R.id.fish_length_check)).setChecked(false);
        ((Spinner) findViewById(R.id.fish_release_condition_spinner)).setSelection(0);
        ((Spinner) findViewById(R.id.fish_condition_spinner)).setSelection(0);

        ((ScrollView) findViewById(R.id.fish_scroll)).fullScroll(ScrollView.FOCUS_UP);
    }
}   