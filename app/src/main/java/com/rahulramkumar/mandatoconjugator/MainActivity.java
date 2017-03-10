package com.rahulramkumar.mandatoconjugator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rahulramkumar.mandatoconjugator.libraries.ConjugatorHelper;
import com.rahulramkumar.mandatoconjugator.libraries.Nosotros;
import com.rahulramkumar.mandatoconjugator.libraries.Tu;
import com.rahulramkumar.mandatoconjugator.libraries.Usted;
import com.rahulramkumar.mandatoconjugator.libraries.Vosotros;

public class MainActivity extends AppCompatActivity {

	EditText infinitiveEdit;
	Spinner subjectSpinner;
	Switch verbTypeSwitch;
	Button conjugateButton;
	TextView conjugatedVerbText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		infinitiveEdit = (EditText) findViewById(R.id.edit_infinitive);

		conjugatedVerbText = (TextView) findViewById(R.id.text_conj_verb);

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


		conjugateButton = (Button) findViewById(R.id.btn_conjugate);
		conjugateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String infinitive = infinitiveEdit.getText().toString().toLowerCase().trim();

				// Only conjugate if it seems like a valid infinitive
				if (infinitive.length() >= 2 &&
						(ConjugatorHelper.getLastNumberOfLetters(infinitive, 2).equals("ar") ||
								ConjugatorHelper.getLastNumberOfLetters(infinitive, 2).equals("er") ||
								ConjugatorHelper.getLastNumberOfLetters(infinitive, 2).equals("ir"))) {
					final String subject = subjectSpinner.getSelectedItem().toString();
					String conjugatedVerb = "";

					try {
						if (subject.equals("Ud.") || subject.equals("Uds.")) {
							conjugatedVerb = Usted.conjugate(infinitive,
									(subject.equals("Ud.") ? Usted.Type.SINGULAR : Usted.Type.PLURAL));
						} else if (subject.equals("Tú")) {
							conjugatedVerb = Tu.conjugate(infinitive,
									(verbTypeSwitch.isChecked() ? ConjugatorHelper.VerbType.AFFIRMATIVE : ConjugatorHelper.VerbType.NEGATIVE));
						} else if (subject.equals("Vosotros")) {
							conjugatedVerb = Vosotros.conjugate(infinitive,
									(verbTypeSwitch.isChecked() ? ConjugatorHelper.VerbType.AFFIRMATIVE : ConjugatorHelper.VerbType.NEGATIVE));
						} else if (subject.equals("Nosotros")) {
							conjugatedVerb = Nosotros.conjugate(infinitive,
									(verbTypeSwitch.isChecked() ? ConjugatorHelper.VerbType.AFFIRMATIVE : ConjugatorHelper.VerbType.NEGATIVE));
						} else {
							displayError("Spinner is set to wrong position");
						}

						conjugatedVerbText.setText(String.format("%s%s", (verbTypeSwitch.isChecked() ? "" : "no "), conjugatedVerb));
					} catch (Exception e) {
						displayError(getString(R.string.input_error));
					}
				} else {
					displayError(getString(R.string.input_error));
				}
			}
		});
	}

	private void displayError(String message) {
		Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
	}
}
