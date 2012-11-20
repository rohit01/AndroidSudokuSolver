package com.rohit.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SelectLevelActivity extends Activity implements OnClickListener {

	Button levelSubmitButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_game);
		
		levelSubmitButton = (Button) findViewById(R.id.levelSubmitButton);
		levelSubmitButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		RadioGroup levelSelectRadioGroup = (RadioGroup) findViewById(R.id.levelSelectRadioGroup);
		Intent answer = new Intent();
		
		switch (levelSelectRadioGroup.getCheckedRadioButtonId()) {
		case R.id.levelEasy:
			answer.putExtra("level", SudokuDbUtil.LEVEL_EASY);
			break;
		case R.id.levelMedium:
			answer.putExtra("level", SudokuDbUtil.LEVEL_MEDIUM);
			break;
		case R.id.levelHard:
			answer.putExtra("level", SudokuDbUtil.LEVEL_HARD);
			break;
		case R.id.levelDiabolical:
			answer.putExtra("level", SudokuDbUtil.LEVEL_DIABOLICAL);
			break;
		default:
			Toast.makeText(this, "Please select a level or press back button.", Toast.LENGTH_LONG).show();
			return;
		}
		setResult(RESULT_OK, answer);
		finish();
	}

}