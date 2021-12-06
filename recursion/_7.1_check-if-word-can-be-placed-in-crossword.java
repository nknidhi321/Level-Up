//https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/submissions/

class Solution {
    
    public static int n;
    public static int m;
    
    public boolean placeWordInCrossword(char[][] crossWord, String word) {
        n = crossWord.length;
        m = crossWord[0].length;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(crossWord[i][j] == ' ' || crossWord[i][j] == word.charAt(0)) {
                    if(canPlaceHorizontally(i, j, crossWord, word)) return true;
                    if(canPlaceReverseHorizontally(i, j, crossWord, word)) return true;
                    if(canPlaceVertically(i, j, crossWord, word)) return true;
                    if(canPlaceReverseVertically(i, j, crossWord, word)) return true;
                }
            }
        }
        return false;
    }
    
    public static boolean canPlaceHorizontally(int row, int col, char[][] crossWord, String word) {
        if(col - 1 >= 0 && crossWord[row][col - 1] != '#') return false;
        
        for(int j = 0; j < word.length(); j++) {
            if(col + j >= m) return false;
            
            if(crossWord[row][col + j] == ' ' || crossWord[row][col + j] == word.charAt(j)) continue;
            else return false;
        }
        
        if(col + word.length() < m && crossWord[row][col + word.length()] != '#') return false;
        
        return true;
    }
    
    public static boolean canPlaceReverseHorizontally(int row, int col, char[][] crossWord, String word) {
        if(col + 1 < m && crossWord[row][col + 1] != '#') return false;
        
        for(int j = 0; j < word.length(); j++) {
            if(col - j < 0) return false;
            
            if(crossWord[row][col - j] == ' ' || crossWord[row][col - j] == word.charAt(j)) continue;
            else return false;
        }
        
        if(col - word.length() >= 0 && crossWord[row][col - word.length()] != '#') return false;
        
        return true;
    }
    
    public static boolean canPlaceVertically(int row, int col, char[][] crossWord, String word) {
        if(row - 1 >= 0 && crossWord[row - 1][col] != '#') return false;
        
        for(int i = 0; i < word.length(); i++) {
            if(row + i >= n) return false;
            
            if(crossWord[row + i][col] == ' ' || crossWord[row + i][col] == word.charAt(i)) continue;
            else return false;
        }
        
        if(row + word.length() < n && crossWord[row + word.length()][col] != '#') return false;
    
        return true;
    }
    
    public static boolean canPlaceReverseVertically(int row, int col, char[][] crossWord, String word) {
        if(row + 1 < n && crossWord[row + 1][col] != '#') return false;
        
        for(int i = 0; i < word.length(); i++) {
            if(row - i < 0) return false;
            
            if(crossWord[row - i][col] == ' ' || crossWord[row - i][col] == word.charAt(i)) continue;
            else return false;
        }
        
        if(row - word.length() >= 0 && crossWord[row - word.length()][col] != '#') return false;
        
        return true;
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
