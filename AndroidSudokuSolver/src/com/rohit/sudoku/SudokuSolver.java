package com.rohit.sudoku;

public class SudokuSolver {
	
	public int solve(int sudokuNumbers[][] ) {
		return solve(sudokuNumbers, 0);
	}
	
	public int solve(int sudokuNumbers[][], int status /*to avoid recursion, default=0*/)      // Full Correct -- Do not modify...
	{   int i, j, x, y;
	    int tempSudokuNumbers[][] = { 	
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
	    
	    for (i = 0; i<9; i++)
	    {
	        for (j=0; j<9; j++)
	        {
	            if (!(sudokuNumbers[i][j]>=0 && sudokuNumbers[i][j]<=9))
	            {
	            	sudokuNumbers[i][j] = 0;
	            }
	            tempSudokuNumbers[i][j] = sudokuNumbers[i][j];
	        }
	    }
	    i = j = x = y = 0;
	    while ( check(sudokuNumbers) == 1)
	    {
	        for ( ; i<9; i++)
	        {
	            if (i != x)
	            {
	                j = 0;
	            }
	            for ( ; j<9; j++)
	            {
	                if (sudokuNumbers[i][j] == 0)
	                {
	                    break;
	                }
	            }
	            if (j != 9 && sudokuNumbers[i][j] == 0)
	            {
	                break;
	            }
	        }
	        if (i >= 9)
	        {
	            if (compare(sudokuNumbers, tempSudokuNumbers) == 0)
	            {
	                break;
	            }
	            for (i = 0; i<9; i++)
	            {
	                for (j=0; j<9; j++)
	                {
	                    tempSudokuNumbers[i][j] = sudokuNumbers[i][j];
	                }
	            }
	            i = 0;
	            j = 0;
	            continue;
	        }
	        x = i;	//Position of Zero = i,j = x,y
	        y = j;
	        sudokuNumbers[x][y] = find_at_xy(sudokuNumbers, x, y);
	        j++;
	    }
	    if (status == 0 && check(sudokuNumbers) == 1)
	    {   if(guess_n_solve(sudokuNumbers) != 0)
	        {   return 1;
	        }
	    }
	    else if(check(sudokuNumbers) == 1)
	    {   return 1;
	    }
	    return 0;
	}
	private void alloc_values(int sudokuNumbers[][], int horizontalLineArray[], int verticalLineArray[], int boxValuesArray[], int x, int y)   // Full Correct -- Do not modify...
	{
	    int i,j;

	    for(i=0; i<9; i++) {
	    	horizontalLineArray[i] = sudokuNumbers[x][i];
	    }
	    
//	    *H_arr = S[x]; **Done**
	    
	    for(i=0; i<9; i++) {
	    	verticalLineArray[i] = sudokuNumbers[i][y];
	    }
	    
//	    for (k=0; k<9; k++)
//	    {
//	        V_arr[k] = &S[k][y];
//	    }  										**Done**
	    
	    int columnStart = ((y)/3)*3; 
	    int rowStart = ((x)/3)*3; 

	    for(i=0; i<9; ) {
	    	for(j=rowStart; j<(rowStart+3); j++) {
	    		boxValuesArray[ i++ ] = sudokuNumbers[j][columnStart];
	    		boxValuesArray[ i++ ] = sudokuNumbers[j][columnStart+1];
	    		boxValuesArray[ i++ ] = sudokuNumbers[j][columnStart+2];
	    	}
	    }
	    
//	    k = ((x)/3)*3;
//	    l = ((y)/3)*3;
//	    
//	    B_arr[0] = &S[k][l];
//	    B_arr[1] = &S[k+1][l];
//	    B_arr[2] = &S[k+2][l];
//	    return 0;
	}
	private int find_at_xy(int S[][], final int x, final int y)      // Full Correct -- Do not modify...
	{
	    int k_put, key;
	    int horizontalLineArray[] = {0,0,0,0,0,0,0,0,0};
	    int verticalLineArray[] = {0,0,0,0,0,0,0,0,0};
	    int boxValuesArray[] = {0,0,0,0,0,0,0,0,0};
	    int i, j, m, n, k2_put=1;
	    for (key=1; key <= 9; key++)
	    {
	        k2_put=1;
	        alloc_values(S, horizontalLineArray, verticalLineArray, boxValuesArray, x, y);
	        k_put = findx(key, horizontalLineArray, verticalLineArray, boxValuesArray);
	        if (k_put == 1)
	        {
	            continue;
	        }
	        //Now we will test the surity of key in for expression  ***S[i][j] = key***
	        // For the BOX::
	        i = ((x)/3)*3;
	        j = ((y)/3)*3;      // i,j = position of begining of BOX...
	        for (m = i; m< i+3 && k2_put==1; m++)
	        {
	            for (n = j; n < j+3 && k2_put==1; n++)
	            {
	                if ((m != x || n != y) && (S[m][n] == 0))
	                {
	                    alloc_values(S, horizontalLineArray, verticalLineArray, boxValuesArray, m, n);
	                    k2_put = findx(key, horizontalLineArray, verticalLineArray, boxValuesArray);
	                }
	            }
	        }
	        if (k_put==0 && k2_put==1)
	        {
	            return key;
	        }
	        k2_put = 1;
	        // For the Vertical line::
	        for (m = 0; m< 9 && k2_put==1; m++)
	        {
	            if ((m != x) && (S[m][y] == 0))
	            {
	                alloc_values(S, horizontalLineArray, verticalLineArray, boxValuesArray, m, y);
	                k2_put = findx(key, horizontalLineArray, verticalLineArray, boxValuesArray);
	            }
	        }
	        if (k_put==0 && k2_put==1)
	        {
	            return key;
	        }
	        k2_put = 1;
	        // For the Horizontal line::
	        for (m = 0; m< 9 && k2_put==1; m++)
	        {
	            if ((m != y) && (S[x][m] == 0))
	            {
	                alloc_values(S, horizontalLineArray, verticalLineArray, boxValuesArray, x, m);
	                k2_put = findx(key, horizontalLineArray, verticalLineArray, boxValuesArray);
	            }
	        }
	        if (k_put==0 && k2_put==1)
	        {
	            return key;
	        }
	    }
	    return 0;
	}
	private int findx(int key, int horizontalLineArray[], int verticalLineArray[], int boxValuesArray[])      // Full Correct -- Do not modify...
	{
	    int i;
	    for (i = 0; i<9; i++)
	    {
	        if (horizontalLineArray[i] == key)
	        {
	            return 1;
	        }
	    }
	    
	    for (i = 0; i<9; i++)
	    {
	        if (verticalLineArray[i] == key)
	        {
	            return 1;
	        }
	    }
	    
	    for (i = 0; i<9; i++)
	    {
	        if (boxValuesArray[i] == key)
	        {
	            return 1;
	        }
	    }
	  
	    return 0;
	}
	
	private int check(int S[][])      // Full Correct -- Do not modify...
	{
	    int i, j, find_0 = 0;
	    for (i=0; i<9 && find_0 != 1; i++)
	    {
	        for (j=0; j<9; j++)
	        {
	            if ( S[i][j] == 0)
	            {
	                find_0 = 1;
	            }
	        }
	    }
	    return find_0;
	}
	
	private int compare(int S1[][], int S2[][])      // Full Correct -- Do not modify...
	{
	    int i, j;
	    for (i = 0; i<9; i++)
	    {
	        for (j = 0; j < 9; j++)
	        {
	            if (S1[i][j] != S2[i][j])
	            {
	                return 1;
	            }
	        }
	    }
	    return 0;
	}
	
	private int guess_n_solve(int S[][])      // Full Correct -- Do not modify...
	{
		int i, j, x, key, k_put;
		int horizontalLineArray[] = {0,0,0,0,0,0,0,0,0};
		int verticalLineArray[] = {0,0,0,0,0,0,0,0,0};
		int boxValuesArray[] = {0,0,0,0,0,0,0,0,0};
	
	    int tempSudokuNumbers[][] = { 	
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
	    
	    for (i = 0; i<9; i++)
	    {   for (j=0; j<9; j++)
	        {   tempSudokuNumbers[i][j] = S[i][j];
	        }
	    }
	    i = j = x = key = 0;
	    while ( check(tempSudokuNumbers) == 1)
	    {   for ( ; i<9; i++)
	        {   if (i != x)
	            {   j = 0;
	            }
	            for ( ; j<9; j++)
	            {   if (tempSudokuNumbers[i][j] == 0)
	                {   break;
	                }
	            }
	            if (j != 9 && tempSudokuNumbers[i][j] == 0)
	            {   break;
	            }
	        }
	        if (i >= 9)
	        {   return 1;
	        }
	        for(key=1; key <=9; key++)
	        {   alloc_values(tempSudokuNumbers, horizontalLineArray, verticalLineArray, boxValuesArray, i, j);
	            k_put = findx(key, horizontalLineArray, verticalLineArray, boxValuesArray);
	            if (k_put == 1)
	            {
	                continue;
	            }
	            tempSudokuNumbers[i][j] = key;
	            if(solve(tempSudokuNumbers, 1) == 0)
	            {   for(i = 0; i<9; i++)
	                {   for(j = 0; j < 9; j++)
	                    {   S[i][j] = tempSudokuNumbers[i][j];
	                    }
	                }
	                return 0;
	            }
	            else
	            {   int i2, j2;
	                for(i2 = 0; i2<9; i2++)
	                {   for(j2 = 0; j2 < 9; j2++)
	                    {   tempSudokuNumbers[i2][j2] = S[i2][j2];
	                    }
	                }
	            }
	        }
	        x = i;	//Position of Zero = i,j == x, j
	        j++;
	    }
	    return 0;
	}
	
//	private int is_correct(int S[][])      // Yet to be tested...
//	{   
//		int horizontalLineArray[] = {0,0,0,0,0,0,0,0,0};
//		int verticalLineArray[] = {0,0,0,0,0,0,0,0,0};
//		int boxValuesArray[] = {0,0,0,0,0,0,0,0,0};
//		int i, j, key ;
//		
//	    for (i = 0; i < 9; i++)
//	    {   for (j = 0; j < 9; j++)
//	        {   key = S[i][j];
//	            S[i][j] = 0;
//	            alloc_values(S, horizontalLineArray, verticalLineArray, boxValuesArray, i, j);
//	            if (findx(key, horizontalLineArray, verticalLineArray, boxValuesArray) == 1)
//	            {   S[i][j] = key;
//	                return 1;
//	            }
//	            S[i][j] = key;
//	        }
//	    }
//	    return 0;
//	}
}
