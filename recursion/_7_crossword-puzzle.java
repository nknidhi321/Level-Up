// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/crossword-puzzle-official/ojquestion

/*
    Words would be at level and cells would be the options (for loop)
    
    Intution:
    --------
    Try placing each word at every cell, if the word fits at a cell, place it
    Now start placing the next Word from cell (0, 0) 
    If the next word does not fit in anywhere backtrack the previous placed word and
    Try placing it from the very next index where it was placed earlier // Simple backtracking.
*/

import java.io.*;
import java.util.*;

public class Main {

	
  //-----------------------------------------------------------------------------------------------------------
  public static int n;
  public static int m;
  
  
	public static void solveCrossWord(char[][] crossWord, String[] words) {
      n = crossWord.length;
      m = crossWord[0].length;
      
      solveCrossWord_Util(crossWord, words, 0);
  }
	

  public static void solveCrossWord_Util(char[][] crossWord, String[] words, int idx) {
    if (idx == words.length) {
      print(crossWord);
      return;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (crossWord[i][j] == '-' || crossWord[i][j] == words[idx].charAt(0)) {

          // horizontal 
          if (canPlaceHorizontally(i, j, crossWord, words[idx])) {
            String oldWord = placeHorizontally(i, j, crossWord, words[idx]);
            solveCrossWord_Util(crossWord, words, idx + 1);
            placeHorizontally(i, j, crossWord, oldWord);
          }

          // NOTE : All patterns would be tried, even if the word fits horizantally, and we successfully solve the crossWord
          // We will backtrack and agian try placing it vertically, to look for another possibility of solving the crossWord
          
          // vertical
          if (canPlaceVertically(i, j, crossWord, words[idx])) {
            String oldWord = placeVertically(i, j, crossWord, words[idx]);
            solveCrossWord_Util(crossWord, words, idx + 1);
            placeVertically(i, j, crossWord, oldWord);
          }
        }
      }
    }
  }
	

	
  public static boolean canPlaceHorizontally(int row, int col, char[][] crossWord, String word) {

    // If there exist some char at left from the wordToBePlaced, that should be '+'
    if (col - 1 >= 0 && crossWord[row][col - 1] != '+') return false;

    // Checking if the word can be placed
    for (int j = 0; j < word.length(); j++) {

      // Goes out of length while calulation of word placement => we cannot place our word
      if (col + j >= m) return false;

      // We can place only if there is '-' or the char "we" want to place
      if (crossWord[row][col + j] == '-' || crossWord[row][col + j] == word.charAt(j)) continue;

      // There exist a '+' or something from 'A-Z' => we cannot place our word =>Fine as far
      else return false;
    }

    // If there exist some char at right after the wordToBePlaced, that should be '+'
    if (col + word.length() < m && crossWord[row][col + word.length()] != '+') return false;

    return true;
  }

	
	
  public static boolean canPlaceVertically(int row, int col, char[][] crossWord, String word) {

    // If there exist some char at top from the wordToBePlaced, that should be '+'
    if (row - 1 >= 0 && crossWord[row - 1][col] != '+') return false;

    // Checking if the word can be placed
    for (int i = 0; i < word.length(); i++) {

      // Goes out of length while calulation of word placement => we cannot place our word
      if (row + i >= n) return false;

      // We can place only if there is '-' or the char "we" want to place => Fine as far
      if (crossWord[row + i][col] == '-' || crossWord[row + i][col] == word.charAt(i)) continue;

      // There exist a '+' or something from 'A-Z' => we cannot place our word
      else return false;
    }

    // If there exist some char at bottom after the wordToBePlaced, that should be '+'
    if (row + word.length() < n && crossWord[row + word.length()][col] != '+') return false;

    return true;
  }

	
	
  public static String placeHorizontally(int row, int col, char[][] crossWord, String word) {
    
    // Keep the originalWord to backtrack
    String oldWord = ""; 
    
    for (int j = 0; j < word.length(); j++) {
      oldWord += crossWord[row][col + j]; // Forming the original word charcter by charcter
      crossWord[row][col + j] = word.charAt(j); // Placing the new word charcter by charcter
    }
    
    return oldWord; // return original Word
  }

	
	
  public static String placeVertically(int row, int col, char[][] crossWord, String word) {
      
    // Keep the originalWord to backtrack
    String oldWord = "";
    
    for (int i = 0; i < word.length(); i++) {
      oldWord += crossWord[row + i][col]; // Forming the original word charcter by charcter
      crossWord[row + i][col] = word.charAt(i); // Placing the new word charcter by charcter
    }
    
    return oldWord; // return original Word
  }
  //-----------------------------------------------------------------------------------------------------------------------
  

	
  public static void print(char[][] arr) {
    for (int i = 0 ; i < arr.length; i++) {
      for (int j = 0 ; j < arr.length; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    char[][] arr = new char[10][10];
    for (int i = 0 ; i < arr.length; i++) {
      String str = scn.next();
      arr[i] = str.toCharArray();
    }
    int NumberOfWords = scn.nextInt();
    String[] words = new String[NumberOfWords];
    for (int i = 0 ; i  < words.length; i++) {
      words[i] = scn.next();
    }
    solveCrossWord(arr, words);
  }

}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
