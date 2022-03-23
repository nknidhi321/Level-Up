// https://practice.geeksforgeeks.org/problems/steps-by-knight5927/1/#

class Solution {

    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int n) {
        
        int sr = KnightPos[0] - 1, sc = KnightPos[1] - 1;
        int dr = TargetPos[0] - 1, dc = TargetPos[1] - 1;
        
        if(sr == dr && sc == dc) return 0;
        
        int[][] dir = { {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1} };
        
        boolean[] visited = new boolean[n * n];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(sr * n + sc);
        visited[sr * n + sc] = true;
        
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int idx = queue.poll();
                int r = idx / n;
                int c = idx % n;
                
                for(int d = 0; d < dir.length; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
            
                    if(x >= 0 && x < n && y >= 0 && y < n && !visited[x * n + y]) {
                        if(x == dr && y == dc) return level + 1;
                        queue.add(x * n + y);
                        visited[x * n + y] = true;
                    }
                }
            } 
            level++;
        }
        return -1;
    }
}
