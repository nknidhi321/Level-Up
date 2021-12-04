//https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#

/*
    SPECIAL CASE:
    =============

    ->Answer is asked in sorted order. 
    ->Hence, move the direction array in lexicographically order
    ->Else you will have to sort the ArrayList ones you get all the paths
    
*/


//void type

class Solution {
    
    //Answer is asked in sorted order. 
    //Hence, move the direction array in lexicographically order
    //Else you will have to sort the ArrayList ones you get all the paths
    
    public static int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    //Mapping dir with dirPath
    public static char[] dirPath = {'D', 'L', 'R', 'U'};
    
    public static ArrayList<String> findPath(int[][] arr, int n) {
        
        if(arr[0][0] == 0 || arr[n - 1][n - 1] == 0) return new ArrayList<>();    
        
        ArrayList<String> list = new ArrayList<>();
        findPath_Util(0, 0, n, "", arr, list);
        return list;
    }
   
    //void Type
    public static void findPath_Util(int sr, int sc, int n, String psf, int[][] arr, ArrayList<String> list) {
        if(sr == n - 1 && sc == n - 1) {
             list.add(psf);
             return;
        }
        
        arr[sr][sc] = 0; //Marking visited
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r >= 0 && r <= n - 1 && c >= 0 && c <= n - 1 && arr[r][c] == 1) {
                 findPath_Util(r, c, n, psf + dirPath[d], arr, list);
            }
        }
        arr[sr][sc] = 1; //Backtrack too generate all paths, mark unvisited
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//get Type

class Solution {
    
    //Answer is asked in sorted order. 
    //Hence, move the direction array in lexicographically order
    //Else you will have to sort the ArrayList ones you get all the paths
    
    public static int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    //Mapping dir with dirPath
    public static char[] dirPath = {'D', 'L', 'R', 'U'};
    
    public static ArrayList<String> findPath(int[][] arr, int n) {
        
        if(arr[0][0] == 0 || arr[n - 1][n - 1] == 0) return new ArrayList<>();    
        return findPath_Util(0, 0, n, arr);
    }
    
    //get Type
    public static ArrayList findPath_Util(int sr, int sc, int n, int[][] arr){
        if(sr == n - 1 && sc == n - 1){
             ArrayList<String> list = new ArrayList<>();
             list.add("");
             return list;
        }
        
        arr[sr][sc] = 0; //Marking visited
        ArrayList<String> ans = new ArrayList<>();
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r >= 0 && r <= n - 1 && c >= 0 && c <= n - 1 && arr[r][c] == 1){
                 ArrayList<String> smallAnsList = findPath_Util(r, c, n, arr);
                 for(String s : smallAnsList){
                    ans.add(dirPath[d] + s);            
                 }
            }
        }
        arr[sr][sc] = 1; //Backtrack too generate all paths, mark unvisited
        return ans;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
