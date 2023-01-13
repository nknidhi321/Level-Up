// https://leetcode.com/problems/grid-illumination/
// Check n queen, for formulas of row, col, dia, antiDia

class Solution {
    
    int m;
    // {Number, count}
    Map<Integer, Integer> row;
    Map<Integer, Integer> col;
    Map<Integer, Integer> dia;
    Map<Integer, Integer> antiDia;
    
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        m = n; // I know n == m
        row = new HashMap<>(); // is row me kitne lamps h
        col = new HashMap<>(); // is col me kitne lamps h
        dia = new HashMap<>(); // is dia me kitne lamps h
        antiDia = new HashMap<>(); // is antiDia me kitne lamps h
        Set<Integer> set = new HashSet<>(); // turned on lamps
        
        int l = lamps.length;
        for(int i = 0; i < l; i++) {
            int r = lamps[i][0];
            int c = lamps[i][1];
            if(!set.contains(r * m + c)) { // same bulb ko avoid krne k liye
                row.put(r, row.getOrDefault(r, 0) + 1);
                col.put(c, col.getOrDefault(c, 0) + 1);
                dia.put(r + c, dia.getOrDefault(r + c, 0) + 1);
                antiDia.put(r - c + m - 1, antiDia.getOrDefault(r - c + m - 1, 0) + 1);
                set.add(r * m + c); // i * m + j
            }
        }
        
        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        int a = 0;
        int q = queries.length;
        int[] ans = new int[q];
        for(int i = 0; i < q; i++) {
            int r = queries[i][0];
            int c = queries[i][1];
            // Kisi v dir me agar ek v bulb h => tum illuminate ho jaaoge
            if(row.getOrDefault(r, 0) > 0 || col.getOrDefault(c, 0) > 0 ||
               dia.getOrDefault(r + c, 0) > 0 || antiDia.getOrDefault(r - c + m - 1, 0) > 0) ans[a++] = 1; 
            else ans[a++] = 0; // Ek v bulb nai mila kisi v dir me toh tumko koi illuminate nai krega
            
            if(set.contains(r * m + c)) { // Tum khud ek bulb ho toh 
                set.remove(r * m + c);  // remove it (turn Off)
                removeIllumination(r, c); // Uske wazah se illuminate hone wale sbko minus karo aatho dir se
            }
            for(int d = 0; d < dir.length; d++) { // Apne aatho dir wale ko dekho
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if(x >= 0 && y >= 0 && x < n && y < m) {
                    if(set.contains(x * m + y)) { // Koi agar bulb hai toh 
                        set.remove(x * m + y); // remove it (turn Off)
                        removeIllumination(x, y); // Uske wazah se illuminate hone wale sbko minus karo aatho dir se
                    }
                }
            }
        }
        return ans;
    }
    
    public void removeIllumination(int r, int c) {
        row.put(r, row.getOrDefault(r, 1) - 1);
        col.put(c, col.getOrDefault(c, 1) - 1);
        dia.put(r + c, dia.getOrDefault(r + c, 1) - 1);
        antiDia.put(r - c + m - 1, antiDia.getOrDefault(r - c + m - 1, 1) - 1);
    }
    
}
