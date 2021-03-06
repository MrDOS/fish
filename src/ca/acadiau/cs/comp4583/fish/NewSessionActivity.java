package ca.acadiau.cs.comp4583.fish;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;

public class NewSessionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.new_session);
		final Button new_session_button = (Button) findViewById(R.id.submit_new_session_data_button);
		final Spinner location_spinner = (Spinner) findViewById(R.id.location_text_spinner);
		final DatePicker startDatePicker = (DatePicker) findViewById(R.id.start_date_picker);
		final TimePicker startTimePicker = (TimePicker) findViewById(R.id.start_time_picker);

		List<String> locations = Arrays.asList(getResources().getStringArray(
				R.array.locations));

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, locations);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		location_spinner.setAdapter(dataAdapter);

		new_session_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// EditText locationText =
				// (EditText)findViewById(R.id.location_text_edit);

				// String location = locationText.getText().toString();

				EditText anglersText = (EditText) findViewById(R.id.num_anglers_text_edit);
				int anglers = 0;
				try {
					anglers = Integer
							.parseInt(anglersText.getText().toString());
				} catch (NumberFormatException e) {

					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
							NewSessionActivity.this);
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
				EditText linesText = (EditText) findViewById(R.id.num_rods_text_edit);
				int lines = 0;
				try {
					lines = Integer.parseInt(linesText.getText().toString());
				} catch (NumberFormatException e) {

					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
							NewSessionActivity.this);
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
				Double longitude = null;
				Double latitude = null;
				long start_time = new GregorianCalendar(startDatePicker
						.getYear(), startDatePicker.getMonth(), startDatePicker
						.getDayOfMonth(), startTimePicker.getCurrentHour(),
						startTimePicker.getCurrentMinute(), 0)
						.getTimeInMillis() / 1000;
				/*
				 * Long does not have NaN or null. Using MIN_VALUE as default
				 * value, as it can easily be checked and won't be used.
				 */
				long end_time = Long.MIN_VALUE;
				FishingSession session = new FishingSession(latitude,
						longitude, start_time, end_time, anglers, lines);
				CheckBox anglerEstimateChbx = (CheckBox) findViewById(R.id.checkBox1);
				session.setExactAnglers(!anglerEstimateChbx.isChecked());
				session.setLocationName(location_spinner.getSelectedItem()
						.toString());
				Intent i = new Intent(getApplicationContext(),
						SubmitFishActivity.class);
				i.putExtra("Session", session);
				startActivity(i);
				finish();
			}
		});

	}

}
