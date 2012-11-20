package com.rohit.sudoku;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SudokuSolverActivity extends Activity implements OnClickListener, OnTouchListener {
	ArrayList<ArrayList<EditText>> allNumberEditText;
	Button solveButton;
	Button resetButton;
	int sudokuNumbers[][] = { 	
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0,0,0}
    };
	public static int gameNo = 0;
	public static int level = SudokuDbUtil.LEVEL_EASY;
	
	Integer lastTouchIndex[] = {-1,		// Sudoku row number
							 	-1};	// Sudoku column number
	String lastTouchValue = "";			// Text Box value
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SudokuDbUtil myDb = SudokuDbUtil.getInstance(this);
		myDb.initializeDataBase();
		myDb.openGameNo(gameNo, level, sudokuNumbers);
		
		setContentView(R.layout.edit_text_sudoku);
		
		initializeButtonsAndListeners();
		Util.setSudokuInEditTextArrayList(sudokuNumbers, allNumberEditText);
		Util.freezeTextAndClicksForEditText(allNumberEditText);
		
		((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(allNumberEditText.get(0).get(0).getWindowToken(), 0);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.solveButton:
			SudokuSolver solver = new SudokuSolver();
			
			if(Util.freezeSudoku == false) {
				Util.freezeTextAndClicksForEditText(allNumberEditText);
			}
			Util.freezedEditTextArrayList2Array(allNumberEditText, sudokuNumbers);
			
			if (solver.solve(sudokuNumbers) == 1) {
				Log.d("Status", "The program was unable to solve the Sudoku Completely...!!");
				Toast.makeText(this, "The program was unable to solve the Sudoku Completely...!!\n" +
						"The Partial solution is shown above. The sudoku is not solvable beyond this point.", Toast.LENGTH_LONG).show();
			}
			else {
				solveButton.setText("Solved");
				Toast.makeText(this, "Solved successfully.", Toast.LENGTH_LONG).show();
			}
			Util.setSudokuInEditTextArrayList(sudokuNumbers, allNumberEditText);
			break;
		case R.id.resetButton:
			Util.resetSudoku(allNumberEditText, solveButton);
			break;
		default:
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		EditText textBox = (EditText) v;
		int i,j;
		
		for(i=0; i<9; i++) {
			for(j=0;j<9;j++) {
				if( allNumberEditText.get(i).get(j).getId() == textBox.getId() ) {
					
					if(lastTouchIndex[0] != -1 && lastTouchIndex[1] != -1 && 
							allNumberEditText.get(lastTouchIndex[0]).get(lastTouchIndex[1]).getText().toString().trim().equals("") ) {
						allNumberEditText.get(lastTouchIndex[0]).get(lastTouchIndex[1]).setText(lastTouchValue);
					}
					else if(lastTouchIndex[0] != -1 && lastTouchIndex[1] != -1 && 
							allNumberEditText.get(lastTouchIndex[0]).get(lastTouchIndex[1]).getText().toString().equals("0") ) {
						allNumberEditText.get(lastTouchIndex[0]).get(lastTouchIndex[1]).setText(Util.BLANK_VALUE_STRING);
					}
					lastTouchIndex[0] = i;
					lastTouchIndex[1] = j;
					lastTouchValue = allNumberEditText.get(i).get(j).getText().toString();

					allNumberEditText.get(i).get(j).selectAll();
					
					resetColors();
					allNumberEditText.get(i).get(j).setTextColor(getResources().getColor(R.color.selection_text_color));
					int k,l;
					for(k=0; k<9; k++) {
						allNumberEditText.get(i).get(k).setTextColor(getResources().getColor(R.color.selection_text_color));
					}
					for(k=0; k<9; k++) {
						allNumberEditText.get(k).get(j).setTextColor(getResources().getColor(R.color.selection_text_color));
					}
					for(k=(i-(i%3)); k<(i-(i%3))+3; k++) {
						for(l=(j-(j%3)); l<(j-(j%3))+3; l++) {
							allNumberEditText.get(k).get(l).setTextColor(getResources().getColor(R.color.selection_text_color));
						}
					}
					return false;
				}
			}
		}
		return false;
	}

	private void resetColors() {
		int i,j;
		for(i=0; i<9; i++) {
			for(j=0;j<9;j++) {
				allNumberEditText.get(i).get(j).setTextColor(Color.BLACK);
			}
		}
	}
	
	private void initializeButtonsAndListeners() {
		allNumberEditText = new ArrayList<ArrayList<EditText>>();
		for (int i = 0; i < 9; i++) {
			allNumberEditText.add(new ArrayList<EditText>());
		}

		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText11));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText12));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText13));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText14));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText15));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText16));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText17));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText18));
		allNumberEditText.get(0).add((EditText) findViewById(R.id.editText19));

		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText21));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText22));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText23));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText24));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText25));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText26));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText27));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText28));
		allNumberEditText.get(1).add((EditText) findViewById(R.id.editText29));

		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText31));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText32));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText33));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText34));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText35));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText36));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText37));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText38));
		allNumberEditText.get(2).add((EditText) findViewById(R.id.editText39));

		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText41));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText42));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText43));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText44));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText45));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText46));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText47));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText48));
		allNumberEditText.get(3).add((EditText) findViewById(R.id.editText49));

		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText51));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText52));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText53));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText54));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText55));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText56));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText57));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText58));
		allNumberEditText.get(4).add((EditText) findViewById(R.id.editText59));

		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText61));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText62));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText63));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText64));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText65));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText66));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText67));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText68));
		allNumberEditText.get(5).add((EditText) findViewById(R.id.editText69));

		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText71));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText72));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText73));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText74));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText75));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText76));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText77));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText78));
		allNumberEditText.get(6).add((EditText) findViewById(R.id.editText79));

		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText81));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText82));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText83));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText84));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText85));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText86));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText87));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText88));
		allNumberEditText.get(7).add((EditText) findViewById(R.id.editText89));

		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText91));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText92));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText93));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText94));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText95));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText96));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText97));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText98));
		allNumberEditText.get(8).add((EditText) findViewById(R.id.editText99));

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (allNumberEditText.get(i).get(j) != null) {
					allNumberEditText.get(i).get(j).setOnClickListener(this);
					allNumberEditText.get(i).get(j).setOnTouchListener(this);
				} else {
					Log.e("Initialization Error",
							"Initialization Error of number editText at position: i = "
									+ i + ", j = " + j + " .");
				}
			}
		}
		
		solveButton = (Button) findViewById(R.id.solveButton);
		solveButton.setOnClickListener( this );
		
		resetButton = (Button) findViewById(R.id.resetButton);
		resetButton.setOnClickListener( this );
	}

	private void CreateMenu(Menu menu) {
		MenuItem menuLockNumbers = menu.add(0, 0, 0, "Lock Numbers");
		{
			menuLockNumbers.setAlphabeticShortcut('l');
			menuLockNumbers.setIcon(R.drawable.lock_icon);
		}
		MenuItem menuUnLockNumbers = menu.add(0, 1, 1, "Unlock Numbers");
		{
			menuUnLockNumbers.setAlphabeticShortcut('u');
			menuUnLockNumbers.setIcon(R.drawable.unlock_icon);
		}
		MenuItem menuExit = menu.add(0, 2, 2, "Home");
		{
			menuExit.setAlphabeticShortcut('e');
			menuExit.setIcon(R.drawable.home_icon);
		}
	}
	
	private boolean MenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			Util.freezeTextAndClicksForEditText(allNumberEditText);
			return true;
		case 1:
			Util.unFreezeTextAndClicksForEditText(allNumberEditText, this);
			return true;
		case 2:
			this.finish();
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuChoice(item);
	}
	
}