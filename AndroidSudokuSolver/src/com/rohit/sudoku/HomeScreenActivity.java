package com.rohit.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.Random;


public class HomeScreenActivity extends Activity implements OnClickListener {

	public static final int NEW_GAME = 1;
	public static final int CONTINUE_SOLVING = 2;
	public static final int SOLVE_A_SUDOKU = 3;
	public static final int SUDOKU_RULES = 4;
	public static final int HOW_2_PLAY = 5;
	public static final int ABOUT = 6;
	public static final int LEVEL_SELECT = 7;
	
	public int newGame;
	public int continueSolving;
	public int solveASudoku;
	public int sudokuRules;
	public int how2Play;
	public int about;
	public int exit;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_menu_screen);

		newGame = R.id.newGameText;
		continueSolving = R.id.continueSolvingText;
		solveASudoku = R.id.solveASudokuText;
		sudokuRules = R.id.sudokuRulesText;
		how2Play = R.id.how2PlayText;
		about = R.id.aboutText;
		exit = R.id.exitText;
		
		((TextView) findViewById(newGame)).setOnClickListener(this);
		((TextView) findViewById(continueSolving)).setOnClickListener(this);
		((TextView) findViewById(solveASudoku)).setOnClickListener(this);
		((TextView) findViewById(sudokuRules)).setOnClickListener(this);
		((TextView) findViewById(how2Play)).setOnClickListener(this);
		((TextView) findViewById(about)).setOnClickListener(this);
		((TextView) findViewById(exit)).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View itemClicked) {
		Intent question = null;
		
		switch(itemClicked.getId()) {
		case R.id.newGameText:
			question = new Intent(this, com.rohit.sudoku.SelectLevelActivity.class);
			startActivityForResult(question, LEVEL_SELECT);
			break;
		case R.id.continueSolvingText:
//			Intent 
			break;
		case R.id.solveASudokuText:
			question = new Intent(this, com.rohit.sudoku.SudokuSolverActivity.class);
			startActivityForResult(question, SOLVE_A_SUDOKU);
			break;
		case R.id.sudokuRulesText:
			question = new Intent(this, com.rohit.sudoku.SudokuRulesActivity.class);
			startActivityForResult(question, SUDOKU_RULES);
			break;
		case R.id.how2PlayText:
			question = new Intent(this, com.rohit.sudoku.How2PlayActivity.class);
			startActivityForResult(question, HOW_2_PLAY);
			break;
		case R.id.aboutText:
			
			break;
		case R.id.exitText:
			finish();
			break;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case NEW_GAME:
			
			break;
		case CONTINUE_SOLVING:
			
			break;
		case SOLVE_A_SUDOKU:
			
			break;
		case SUDOKU_RULES:
			
			break;
		case HOW_2_PLAY:
			
			break;
		case ABOUT:
			
			break;
		case LEVEL_SELECT:
			if(resultCode == RESULT_OK) {
				this.startNewGame(data.getExtras().getInt("level"));
			}
			break;
		}
	}
	
	private void startNewGame(int level) {
		Random randomNumberGenerator = new Random();
		
		SudokuDbUtil sudokuDatabase = SudokuDbUtil.getInstance(this);
		SudokuSolverActivity.level = level; 
		SudokuSolverActivity.gameNo = randomNumberGenerator.nextInt(sudokuDatabase.countNoOfGamesWithLevel(level));
		
		startActivityForResult(new Intent(this, com.rohit.sudoku.SudokuSolverActivity.class), NEW_GAME);
	}

}