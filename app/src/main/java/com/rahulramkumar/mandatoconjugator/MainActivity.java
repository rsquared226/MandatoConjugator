package com.rahulramkumar.mandatoconjugator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

	Spinner subjectSpinner;
	Switch verbTypeSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		subjectSpinner = (Spinner) findViewById(R.id.spinner_subject);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.subjects_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subjectSpinner.setAdapter(adapter);

		verbTypeSwitch = (Switch) findViewById(R.id.switch_type);
		verbTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					verbTypeSwitch.setText(R.string.affirmative);
				} else {
					verbTypeSwitch.setText(R.string.negative);
				}
			}
		});
	}
}
