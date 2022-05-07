// https://leetcode.com/problems/sliding-puzzle/
// BFS

```
class Solution {
    
    public static class Pair {
        int idx;
        String state;
        
        public Pair(int idx, String state) {
            this.idx = idx;
            this.state = state;
        }
    }
    
    public int slidingPuzzle(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        String finalState = "123450";  // Given
    
        int startIdx = -1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 0) startIdx = i * m + j;
                sb.append(board[i][j]);
            }
        }
        
        String initialState = sb.toString();
        if(initialState.equals(finalState)) return 0;
        
        // why Set ??
        // Kuki kisi xth idx se wapas se hum xth idx pe aa saktey hai, and this is valid 
        // provided baaki cells thori bht idhar udhar ho 
        // So, this means poora board he ek state hai
        // So current board state ko visited mark karna hai, so Set
        Set<String> visSet = new HashSet<>(); 
        
        
        // idxCell(jaha 0 hai) pe khare hoke kon kon se cell/idx pe jaaya jaa sakta hai, 1D k term me
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}}; 
        
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startIdx, initialState)); // {0 ki initial idx, inital board state}
        visSet.add(initialState); // Mark visited

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Pair p = queue.remove();
                int idx = p.idx; // currently jaha pe 0 hai
                String currState = p.state; // currently jo state hai board ka

                int[] dir = dirs[idx]; // idx cell se kon kon se cell number pe jaa saktey hai
                for(int d = 0; d < dir.length; d++) {
                    int destIdx = dir[d];
                    String newState = swap(idx, destIdx, currState); // Swap kar do, jaha jaa saktey hai
                    if(newState.equals(finalState)) return level + 1;
                    if(!visSet.contains(newState)) {
                        queue.add(new Pair(destIdx, newState));
                        visSet.add(newState);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    
    // Swap '0' with the destIdx char
    public String swap(int idx, int destIdx, String state) {
        StringBuilder currState = new StringBuilder(state);
        char temp = currState.charAt(idx);
        currState.setCharAt(idx, currState.charAt(destIdx));
        currState.setCharAt(destIdx, temp);
        return currState.toString();
    }
    
}
```
-------------------------------------------------------------------------------------------------------------------
