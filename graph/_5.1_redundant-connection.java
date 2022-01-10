// https://leetcode.com/problems/redundant-connection/

// Do not use DFS Solution because you will actually end up creating graph and calling dfs each time you add an edge, so that will increase your complexity a lot.
// DSU by Size

```
class Solution {
    
    static int[] par;
	static int[] size;
    
    public static int findPar(int v) {
		if (par[v] == v)
			return v;
		return par[v] = findPar(par[v]);
	}

	public static void mergeOrUnionBySize(int gpu, int gpv) {
		if (size[gpu] > size[gpv]) { 	
			par[gpv] = gpu;
			size[gpu] += size[gpv];
		} 
		else { 					
			par[gpu] = gpv; 
			size[gpv] += size[gpu];
		}
	}
    
    public int[] findRedundantConnection(int[][] edges) {
        
        int N = edges.length;
        par = new int[N + 1];
        size = new int[N + 1];
        
        for (int i = 0; i <= N; i++) {
			par[i] = i;				
			size[i] = 1;				
		}

		for (int[] arr : edges) {				
			int GlobalParentOfu = findPar(arr[0]);
			int GlobalParentOfv = findPar(arr[1]);

			if (GlobalParentOfu != GlobalParentOfv) 
				mergeOrUnionBySize(GlobalParentOfu, GlobalParentOfv);
            else
                return arr;
		}
        return new int[0];
    }
}
```

------------------------------------------------------------------------------------

// DSU by Height

```
class Solution {
    
    static int[] par;
	static int[] height;
    
    public static int findPar(int v) {
		if (par[v] == v)
			return v;
		return par[v] = findPar(par[v]);
	}

    public static void mergeOrUnionByHeight(int gpu, int gpv) {
        if (height[gpu] > height[gpv]) { 	  
            par[gpv] = gpu;						        
        }
        else if (height[gpv] > height[gpu]) {  	
            par[gpu] = gpv; 					
        }
        else {									
            par[gpu] = gpv; 					
            height[gpv]++;			     		
        }
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        
        int N = edges.length;
        par = new int[N + 1];
        height = new int[N + 1];
        
        for (int i = 0; i <= N; i++) {
			par[i] = i;				
			height[i] = 1;				
		}

		for (int[] arr : edges) {				
			int GlobalParentOfu = findPar(arr[0]);
			int GlobalParentOfv = findPar(arr[1]);

			if (GlobalParentOfu != GlobalParentOfv) 
				mergeOrUnionByHeight(GlobalParentOfu, GlobalParentOfv);
            else
                return arr;
		}
        return new int[0];
    }
}
```
----------------------------------------------------------------------------------
