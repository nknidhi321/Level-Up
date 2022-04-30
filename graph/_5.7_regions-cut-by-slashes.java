// https://leetcode.com/problems/regions-cut-by-slashes/

class Solution {
    
    int[] par;
    int[] height;
    
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int dots = n + 1;
        
        par = new int[dots * dots];
        height = new int[dots * dots];
        
        for(int i = 0; i < dots * dots; i++) {
            par[i] = i;
            height[i] = 1;
        }
        
        // Merging all boundary dots with 0(could have been any dot)
        for(int i = 0; i < dots; i++) {
            for(int j = 0; j < dots; j++) {
                if(i == 0 || i == dots - 1 || j == 0 || j == dots - 1) {
                    int idx = i * dots + j;
                    if(idx != 0) union(0, idx);
                }
            }
        }

        // Calling union on the given slashes
        int count = 1;
        for(int i = 0; i < n; i++) {
            char[] ch = grid[i].toCharArray();
            for(int j = 0; j < n; j++) {
                if(ch[j] == '/') { // forward slash
                    int topIdx = i * dots + (j + 1); // Idx nikalo us corresponding slash ka
                    int bottomIdx = (i + 1) * dots + j; // Idx nikalo us corresponding slash ka
                    count += union(topIdx, bottomIdx); // Diff leader => 0 (Merging), Same leader => 1 (Cycle)
                }
                else if(ch[j] == '\\') { // backward slash 
                    int topIdx = i * dots + j; // Idx nikalo us corresponding slash ka
                    int bottomIdx = (i + 1) * dots + (j + 1); // Idx nikalo us corresponding slash ka
                    count += union(topIdx, bottomIdx); // Diff leader => 0 (Merging), Same leader => 1 (Cycle)
                }
                // Empty char aaya toh kuch nai krna hai
            }        
        }
        return count;
    }
    
    public int find(int x) {
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }
    
    public int union(int x, int y) {
        int lx = find(x);
        int ly = find(y);
        if(lx != ly) { // dono nbr hai and different leader aaya merge them
            if(height[lx] > height[ly]) {
                par[ly] = lx;
            }
            else if(height[ly] > height[lx]) {
                par[lx] = ly;
            }
            else {
                par[lx] = ly;
                height[ly]++;
            }
            return 0;
        }
        else { // If the leader is same, they will form cycle, so count++
            return 1;
        }
    }
    
}
