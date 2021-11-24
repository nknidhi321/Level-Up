//https://leetcode.com/problems/01-matrix/
//Multisource BFS

/*
     You need to use extra visited  matrix because you are modifying and returning the input matrix itself as an answer
     So to check for visited nodes you need an extra matrix, because you cannot keep both answer and visited in the same matrix    
     
     The idea is to add all 0's in the queue, now start BFS traversal and whenever you find a nbr 1 update nbr cell with level + 1, also mark it visited.. and so on..
 
*/

// 2D to 1D
class Solution {
    
    public int[][] updateMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 0) {
                    queue.offer(i * m + j);
                    visited[i][j] = true;
                }
            }
        }
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {       
                int idx = queue.poll();
                int x = idx / m;
                int y = idx % m;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];
                    
                    if(r >= 0 && r < n && c >= 0 && c < m && matrix[r][c] == 1) {
                         if(!visited[r][c]) {
                            queue.offer(r * m + c);
                            matrix[r][c] = level + 1;
                            visited[r][c] = true;
                         }
                    }
                }
            }
            level++;
        }
        return matrix;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Mine //BFS //Using Pair Class

class Solution {
    
    static class Pair {
        int x;
        int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[][] updateMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 0) {
                    queue.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {       
                Pair currPair = queue.poll();
                int x = currPair.x;
                int y = currPair.y;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];
                    
                    if(r >= 0 && r < n && c >= 0 && c < m && matrix[r][c] == 1) {
                         if(!visited[r][c]) {
                            queue.offer(new Pair(r, c));
                            matrix[r][c] = level + 1;
                            visited[r][c] = true;
                         }
                    }
                }
            }
            level++;
        }
        return matrix;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
