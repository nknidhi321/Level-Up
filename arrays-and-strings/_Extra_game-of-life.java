// https://leetcode.com/problems/game-of-life/

// NOTE : 
// We have to perform state change on the entire board on the original board and not on the modified state board 
// Doing stateChane in inPlace, so that's why assuming the below representation
// original dead cell = 0
// changed dead cell from 0 -> 1 = 2
// original live cell = 1
// changed live cell from 1 -> 0 = -2

class Solution {
    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1,1}, {1,0}, {1, -1}, {0,-1}};
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int nbr1Count = 0; 
                for(int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if(r >= 0 && r < n && c >= 0 && c < m) {
                        if(board[r][c] == 1 || board[r][c] == -2) { // counting nbr 1's
                            nbr1Count++;
                           // System.out.println("nbr Count " + nbr1Count);
                        }
                    }
                    //System.out.println();
                }
                
                // Just the given conditions
                if((board[i][j] == 1 || board[i][j] == -2) && nbr1Count < 2) {
                    board[i][j] = board[i][j] == 1 ? -2 : board[i][j];  // If 1 then change to 0
                }
                else if((board[i][j] == 1 || board[i][j] == -2) && (nbr1Count == 2 || nbr1Count == 3)) {
                    continue;
                }
                else if((board[i][j] == 1 || board[i][j] == -2) && nbr1Count > 3) {
                    board[i][j] = board[i][j] == 1 ? -2 : board[i][j]; // If 1 then change to 0
                }
                else if((board[i][j] == 0 || board[i][j] == 2) && nbr1Count == 3) {
                    board[i][j] = board[i][j] == 0 ? 2 : board[i][j]; // If 0 then change to 1
                }
                
                // Printing the board
                // for(int l = 0; l < n; l++) {
                //     for(int q = 0; q < m; q++) {
                //         System.out.print(board[l][q] + " ");
                //     }
                //     System.out.println();
                // }
                // System.out.println();
            }
        }
        
        // Change all -2 to 0  and 2 to 1, as the final state is expected.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == -2) {
                   board[i][j] = 0; 
                }
                else if(board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
