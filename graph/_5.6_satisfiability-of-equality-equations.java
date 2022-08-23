// https://leetcode.com/problems/satisfiability-of-equality-equations/

/*
      Intuition:
      ---------
      We have 26 nodes in the graph.
      All "==" equations actually represent the connection in the graph.
      The connected nodes should be in the same color/union/set.

      Then we check all inequations.
      Two inequal nodes should be in the different color/union/set.

      Explanation:
      -----------
      We can solve this problem by DFS or Union Find.

      Firt pass all "==" equations.
      Union equal letters together
      Now we know which letters must equal to the others.

      Second pass all "!=" inequations,
      Check if there are any contradict happens.
*/

// Union Find
```
class Solution {
    
    int[] par;
    int[] height;
    
    public boolean equationsPossible(String[] equations) {
        par = new int[26];
        height = new int[26];
        
        char alpha = 0;
        for(int i = 0; i < 26; i++) {
            par[i] = alpha++;
            height[i] = 1; 
        }

        for(String s : equations) {
            char[] ch = s.toCharArray();
            if(ch[1] == '=' && ch[2] == '=') {
                union(ch[0] - 'a', ch[3] - 'a');
            }           
        }
                      
        for(String s : equations) {
            char[] ch = s.toCharArray();
            if(ch[1] == '!' && ch[2] == '=') {
                if(find(ch[0] - 'a') == find(ch[3] - 'a')) {
                    return false;
                }
            }           
        }
        return true;
    }
                      
    
    public int find(int x) {
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }
    
    public void union(int x, int y) {
        int lx = find(x);
        int ly = find(y);
        if(lx != ly) {
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
        }
    }
    
}
```

----------------------------------------------------------------------------------------

// DFS 
```
class Solution {
    
    public boolean equationsPossible(String[] equations) {
        List<Integer>[] graph = new ArrayList[26];
        for(int i = 0; i < 26; i++) graph[i] = new ArrayList();

        for(String eqn : equations) {
            if(eqn.charAt(1) == '=') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                graph[x].add(y);
                graph[y].add(x);
            }
        }

        int[] color = new int[26];  
        Arrays.fill(color, -1);  // unvisited

        // Har component ko diff color se mark kar do
        for(int i = 0; i < 26; i++) {
            if (color[i] == -1) {
                dfs(i, i, color, graph);
            }
        }

        // Ab bs not equal pe check lgao aur dekho
        // x and y same component me lie krte h ya nahi
        for(String eqn : equations) {
            if(eqn.charAt(1) == '!') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                if(color[x] == color[y]) return false;
            }
        }
        return true;
    }

    // mark the color of `node` as `c`
    private static void dfs(int node, int c, int[] color, List<Integer>[] graph) {
        color[node] = c;
        for(int nei : graph[node]) {
            if(color[nei] == -1) {
                dfs(nei, c, color, graph);
            }
        }
    }
    
}
```

------------------------------------------------------------------------------------------------
