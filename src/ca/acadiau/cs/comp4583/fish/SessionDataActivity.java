package ca.acadiau.cs.comp4583.fish;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;

public class SessionDataActivity extends Activity {
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.session_data);
		context = this;
		final Button save_session_button = (Button) findViewById(R.id.save_changes_buttons);
		final Button back_session_button = (Button) findViewById(R.id.submit_new_session_data_button);

		final Spinner location_spinner = (Spinner) findViewById(R.id.location_text_spinner);

		List<String> locations = Arrays.asList(getResources().getStringArray(R.array.locations));

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, locations);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		location_spinner.setAdapter(dataAdapter);

		Intent thisIntent = getIntent();
		final FishingSession session = (FishingSession) thisIntent
				.getSerializableExtra("Session");
		// location_spinner.set
		int position = dataAdapter.getPosition(session.getLocationName());
		location_spinner.setSelection(position);

		final EditText anglersText = (EditText) findViewById(R.id.num_anglers_text_edit);
		anglersText.setText(Integer.toString(session.getAnglers()));

		final CheckBox anglersEstimatedChbx = (CheckBox) findViewById(R.id.checkBox1);
		anglersEstimatedChbx.setChecked(!session.isExactAnglers());

		int number_of_fish = session.getFish().size();
		final EditText num_catches_Text = (EditText) findViewById(R.id.num_catches_text_edit);
		final CheckBox estimated_catches_chbx = (CheckBox) findViewById(R.id.CheckBox02);
		estimated_catches_chbx.setChecked(!session.isExactCatches());

		if (session.getCatches() == 0) {
			num_catches_Text.setText(Integer.toString(number_of_fish));
			estimated_catches_chbx.setChecked(false);
		} else
			num_catches_Text.setText(Integer.toString(session.getCatches()));

		// System.out.println("CATCHES: " +
		// Integer.toString(session.getCatches()));
		final EditText linesText = (EditText) findViewById(R.id.num_rods_text_edit);
		linesText.setText(Integer.toString(session.getLines()));

		save_session_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SubmitFishActivity.class);
                session.setLocationName(location_spinner.getSelectedItem().toString());
                try
                {
                	session.setAnglers(Integer.valueOf(anglersText.getText().toString()));
                }
                catch (NumberFormatException e) {

					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
							context);
					alertBuilder
							.setMessage("Number of Anglers must have a valid number.");
					alertBuilder.setCancelable(true);
					alertBuilder.setPositiveButton(android.R.string.ok,
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
                session.setExactAnglers(!anglersEstimatedChbx.isChecked());
                try 
                {
                	session.setLines(Integer.valueOf(linesText.getText().toString()));
                }
                catch (NumberFormatException e) {

					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
							context);
					alertBuilder
							.setMessage("Number of Rods must have a valid number.");
					alertBuilder.setCancelable(true);
					alertBuilder.setPositiveButton(android.R.string.ok,
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
                try
                {
                	session.setCatches(Integer.valueOf(num_catches_Text.getText().toString()));
                }
                catch (NumberFormatException e) {

					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
							context);
					alertBuilder
							.setMessage("Number of Catches must have a valid number.");
					alertBuilder.setCancelable(true);
					alertBuilder.setPositiveButton(android.R.string.ok,
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
                session.setExactCatches(!estimated_catches_chbx.isChecked());
				i.putExtra("Session", session);
				startActivity(i);
				finish();

			}
		});
		back_session_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SubmitFishActivity.class);
				i.putExtra("Session", session);
				startActivity(i);
                finish();

			}
		});

	}

}
