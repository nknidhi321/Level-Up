//https://leetcode.com/problems/sudoku-solver/

/*
  Rajneesh
  Optimized Code
  Not making calls at cells which are already occupied by any number, i.e. making calls on only emptyCells,
  this will reduce the height of the tree.
  To achieve this, we traverse the entire board ones and add all the emptyIndex in a list,
  And make calls on these index's only, but board will also be passed to check isValid()

  Also, row and col checks of isValid() method can be written in a single loop.
*/

class Solution {
    
    // NOTE: It is guaranteed that the input board has only one solution
    
    public void solveSudoku(char[][] board) {
        
        //Creating list of emptyIndex Cells
        List<Integer> emptyIndex = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    emptyIndex.add(i * 9 + j); // Adding idx in encoded form => i * m + j
                }
            }
        }
        
        isSolved(0, emptyIndex, board);
    }
    
    public static boolean isSolved(int idx, List<Integer> emptyIndex, char[][] board) {
        if(idx == emptyIndex.size()) return true; // Sudoku solved
        
        // Decoding the encoded idx back to row, col
        int cellIdx = emptyIndex.get(idx);
        int row = cellIdx / 9;
        int col = cellIdx % 9;
        
        // Calculating nextRow, nextCol for the next call
        int nextRow = (col == 8) ? row + 1 : row; 
        int nextCol = (col == 8) ? 0 : col + 1;

        // If cell is empty, try filling the valid number
        if(board[row][col] == '.') {
            for(char num = '1'; num <= '9'; num++) { // Check from 0 to 9
                if(isValid(row, col, num, board)) {  // If any number is valid
                    board[row][col] = num; // Create your answer
                    if(isSolved(idx + 1, emptyIndex, board)) return true; // If Sudoku is solved make sure to return else ip == op *
                    board[row][col] = '.'; // Backtrack // * Because we will backtrack
                }
            }
        }
        
        return false;
    }
    
    
    public static boolean isValid(int row, int col, char num, char[][] board) {
        
        // Check row and col
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        
        // Check submatrix
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[row + i][col + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Archit
// Not so optimized 
// Making calls on both empty and non empty cells

class Solution {
    
    // NOTE: It is guaranteed that the input board has only one solution
    
    public void solveSudoku(char[][] board) {
        isSolved(0, 0, board);
    }
    
    public static boolean isSolved(int row, int col, char[][] board) {
        if(row == 9) return true; // Sudoku solved
        
        // Calculating nextRow, nextCol for the next call
        int nextRow = (col == 8) ? row + 1 : row; 
        int nextCol = (col == 8) ? 0 : col + 1;

        // If cell is empty, try filling the valid number
        if(board[row][col] == '.') {
            for(char num = '1'; num <= '9'; num++) { // Check from 0 to 9
                if(isValid(row, col, num, board)) {  // If any number is valid
                    board[row][col] = num; // Create your answer
                    if(isSolved(nextRow, nextCol, board)) return true; // If Sudoku is solved make sure to return else ip == op *
                    board[row][col] = '.'; // Backtrack // * Because we will backtrack
                }
            }
        }
        else { // If cell is already filled i.e. not empty make the next call
            if(isSolved(nextRow, nextCol, board)) return true;
        }
        
        return false;
    }
    
    
    public static boolean isValid(int row, int col, char num, char[][] board) {
        
        // Check row
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == num) {
                return false;
            }
        }
        
        //Check col
        for(int i = 0; i < 9; i++) {
            if(board[i][col] == num) {
                return false;
            }
        }
        
        // Check submatrix
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[row + i][col + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Sumit Sir //Old Code

class Solution {
    public void solveSudoku(char[][] board) {
        char[][] sudoku = new char[board.length][board[0].length];
        solveSudokuUtil(board, 0, 0, sudoku);
        for(int i=0; i<board.length; i++){
           for(int j=0; j<board[0].length; j++){
                board[i][j] = sudoku[i][j];
            }
        }   
            
    }
    
    public static void solveSudokuUtil(char[][] board, int i, int j, char[][] sudoku){
        if(i == board.length) {
            for(i=0; i<board.length; i++){
                for(j=0; j<board[0].length; j++){
                    sudoku[i][j] = board[i][j];
                }
             }   
            return;
        }
        
        int ni = 0;     //next i
        int nj = 0;     //next j
        
        if(j == board[0].length-1){
            ni = i + 1;
            nj = 0;
        }
        else{
            ni = i;
            nj = j + 1;
        }
        if(board[i][j] != '.')   
            solveSudokuUtil(board, ni, nj, sudoku);
        else{
            for(char val = '1'; val <= '9'; val++){
                if(isValid(board, i, j, val)){
                    board[i][j] = val;
                    solveSudokuUtil(board, ni, nj, sudoku);
                    board[i][j] = '.';
                }
            }
        }
    }
    
    public static boolean isValid(char[][] board, int x, int y, char val){
        //row checking
        for(int i=0; i<board[0].length; i++)
            if(board[x][i] == val)
                return false;
        
        //column checking
        for(int i=0; i<board.length; i++)
            if(board[i][y] == val)
                return false;
        
        //subSudoku checking
        int subx = (x / 3) * 3; //0th index(i) of respective subbox
        int suby = (y / 3) * 3; //0th index(j) of respective subbox
        
         for(int i = subx; i < subx + 3; i++)
            for(int j = suby; j < suby + 3; j++)
                if(board[i][j] == val)
                    return false;            
        return true;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
