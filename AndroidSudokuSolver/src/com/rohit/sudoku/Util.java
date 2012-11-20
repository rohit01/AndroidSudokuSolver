package com.rohit.sudoku;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Util {
	
	private static final String ERROR_TAG = "Util_Error";
	private static final String DEBUG_TAG = "Util_Debug";
	public static boolean freezeSudoku = false;
	public static final String BLANK_VALUE_STRING = " ";
	
	public static int[][] ArrayList2Array( ArrayList<ArrayList<Integer>> sudokuArrayListNumbers) {
		int sudokuArray[][] = {
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
		
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				sudokuArray[i][j] = sudokuArrayListNumbers.get(i).get(j);
			}
		}
		
		return sudokuArray;
	}
	
	public static int[][] editTextArrayList2Array( ArrayList<ArrayList<EditText>> editTextArrayListNumbers) {
		int sudokuArray[][] = {
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
		
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( !((editTextArrayListNumbers.get(i).get(j).getText().toString()).equals(BLANK_VALUE_STRING) ||
					  (editTextArrayListNumbers.get(i).get(j).getText().toString()).equals("")) ) {
					
					sudokuArray[i][j] = Integer.parseInt(editTextArrayListNumbers.get(i).get(j).getText().toString() );
				}
			}
		}
		
		return sudokuArray;
	}
	
	public static void editTextArrayList2Array( ArrayList<ArrayList<EditText>> editTextArrayListNumbers, int sudokuArray[][]) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( !((editTextArrayListNumbers.get(i).get(j).getText().toString()).equals(BLANK_VALUE_STRING) ||
					  (editTextArrayListNumbers.get(i).get(j).getText().toString()).equals("")) ) {
					
					sudokuArray[i][j] = Integer.parseInt( editTextArrayListNumbers.get(i).get(j).getText().toString() );
				}
				else {
					sudokuArray[i][j] = 0;
				}
			}
		}
	}
	
	public static void freezedEditTextArrayList2Array( ArrayList<ArrayList<EditText>> editTextArrayListNumbers, int sudokuArray[][]) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( (!((editTextArrayListNumbers.get(i).get(j).getText().toString()).equals(BLANK_VALUE_STRING) ||
					  (editTextArrayListNumbers.get(i).get(j).getText().toString()).equals("")) ) && 
					  (editTextArrayListNumbers.get(i).get(j).isEnabled() == false) ){
					
					sudokuArray[i][j] = Integer.parseInt( editTextArrayListNumbers.get(i).get(j).getText().toString() );
				}
				else {
					sudokuArray[i][j] = 0;
				}
			}
		}
	}
	
		
	public static ArrayList<ArrayList<Integer>> sudokuArray2ArrayList( int sudokuArray[][] ) {
		ArrayList<ArrayList<Integer>> sudokuArrayListNumbers = new ArrayList<ArrayList<Integer>>();
		
		int i, j;
		for(i=0; i<9; i++) {
			sudokuArrayListNumbers.add(new ArrayList<Integer>());
			for(j=0; j<9; j++) {
				sudokuArrayListNumbers.get(i).add( (Integer) sudokuArray[i][j] );
			}
		}
		
		return sudokuArrayListNumbers;
	}
	
	public static void setSudokuInButtonArrayList(int sudokuArray[][], ArrayList<ArrayList<Button>> buttonArrayListNumbers) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( sudokuArray[i][j] != 0 ) {
					buttonArrayListNumbers.get(i).get(j).setText( ((Integer) sudokuArray[i][j]).toString() );
				}
				else {
					buttonArrayListNumbers.get(i).get(j).setText(BLANK_VALUE_STRING);
				}
			}
		}
	}
	
	public static void setSudokuInButtonArrayList(ArrayList<ArrayList<Integer>> sudokuArrayListNumbers,
			ArrayList<ArrayList<Button>> buttonArrayListNumbers) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( sudokuArrayListNumbers.get(i).get(j) != 0 ) {
					buttonArrayListNumbers.get(i).get(j).setText( sudokuArrayListNumbers.get(i).get(j).toString() );
				}
				else {
					buttonArrayListNumbers.get(i).get(j).setText(BLANK_VALUE_STRING);
				}
			}
		}
	}
	
	public static void setSudokuInEditTextArrayList(int sudokuArray[][], ArrayList<ArrayList<EditText>> editTextArrayListNumbers) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( sudokuArray[i][j] != 0 ) {
					editTextArrayListNumbers.get(i).get(j).setText( ((Integer) sudokuArray[i][j]).toString() );
				}
				else {
					editTextArrayListNumbers.get(i).get(j).setText(BLANK_VALUE_STRING);
				}
			}
		}
	}
	
	public static void setSudokuInEditTextArrayList(ArrayList<ArrayList<Integer>> sudokuArrayListNumbers,
			ArrayList<ArrayList<EditText>> editTextArrayListNumbers) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( sudokuArrayListNumbers.get(i).get(j) != 0 ) {
					editTextArrayListNumbers.get(i).get(j).setText( sudokuArrayListNumbers.get(i).get(j).toString() );
				}
				else {
					editTextArrayListNumbers.get(i).get(j).setText(BLANK_VALUE_STRING);
				}
			}
		}
	}
	
	public static void freezeTextAndClicksForEditText(ArrayList<ArrayList<EditText>> editTextArrayListNumbers) {
		int i, j, number;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( editTextArrayListNumbers.get(i).get(j).getText().toString().equals(BLANK_VALUE_STRING) ||
						editTextArrayListNumbers.get(i).get(j).getText().toString().trim().equals("") ) {
					continue;
				}
				try{
					number = Integer.parseInt(editTextArrayListNumbers.get(i).get(j).getText().toString());
					if( number >= 1 && number <= 9 ) {
						editTextArrayListNumbers.get(i).get(j).setInputType(InputType.TYPE_NULL);
						editTextArrayListNumbers.get(i).get(j).setTypeface(null, Typeface.BOLD);
						editTextArrayListNumbers.get(i).get(j).setClickable(false);
						editTextArrayListNumbers.get(i).get(j).setOnClickListener(null);
						editTextArrayListNumbers.get(i).get(j).setEnabled(false);
						freezeSudoku = true;
					}
				}
				catch (Exception e) {
					Log.e(ERROR_TAG, "Error in parsing integer.");
				}
			}
		}
	}
	
	public static void unFreezeTextAndClicksForEditText(ArrayList<ArrayList<EditText>> editTextArrayListNumbers, OnClickListener listener) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( editTextArrayListNumbers.get(i).get(j).isEnabled() == false ) {
					editTextArrayListNumbers.get(i).get(j).setEnabled(true);
					editTextArrayListNumbers.get(i).get(j).setInputType(InputType.TYPE_CLASS_NUMBER);
					editTextArrayListNumbers.get(i).get(j).setTypeface(Typeface.MONOSPACE);
					editTextArrayListNumbers.get(i).get(j).setClickable(true);
					editTextArrayListNumbers.get(i).get(j).setOnClickListener(listener);
					freezeSudoku = false;
				}
			}
		}
	}
	
	public static void setZeroAsBlankInButtonArrayList(ArrayList<ArrayList<Button>> buttonArrayListNumbers) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( buttonArrayListNumbers.get(i).get(j).getText().equals("0") ) {
					buttonArrayListNumbers.get(i).get(j).setText(BLANK_VALUE_STRING);
				}
			}
		}
	}
	
	public static void resetSudoku(ArrayList<ArrayList<EditText>> editTextArrayListNumbers, Button solveButton) {
		int i, j;
		for(i=0; i<9; i++) {
			for(j=0; j<9; j++) {
				if( editTextArrayListNumbers.get(i).get(j).isEnabled() == true ) {
					editTextArrayListNumbers.get(i).get(j).setText(BLANK_VALUE_STRING);
				}
			}
		}
		if( solveButton != null ) {
			solveButton.setText("Solve");
		}
			
	}
	
}
