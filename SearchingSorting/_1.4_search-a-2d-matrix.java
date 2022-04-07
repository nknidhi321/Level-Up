// https://leetcode.com/problems/search-a-2d-matrix/

// Rajneesh
// Imagine in sorted 1D matrix, by (r * m + c) formula, 
// Now, see it is just a sorted 1D array, so  simply apply binary search
// TC of binary search : O(logn), where here n = n * m
// log(n * m) => log(n) + log(m)

```
class Solution {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int si = 0, ei = (n * m) - 1;
        
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            int r = mid / m, c = mid % m;
            
            if(matrix[r][c] == target) return true;
            else if(matrix[r][c] < target) si = mid + 1;
            else ei = mid - 1;
        }
        return false;
    }
    
}
```

// NOTE : It is not specified ki tumse niche wale tumse bade hai, but since it is sortes so tumse niche wale tumse bade he honge for sure
// So this can be also solved like below follow up
-------------------------------------------------------------------------------------------------------------------------------------------

// Follow up :- https://leetcode.com/problems/search-a-2d-matrix-ii/

// Rajneesh
// Staircase Search -> O(N + M) in Worst Case
// Note : Here you cannot apply original Binary Search like above because though it array is sorted but not how it is expected in BS, so apply modified BS

```
class Solution {
    
    public boolean searchMatrix(int[][] matrix, int target) {
           int n = matrix.length, m = matrix[0].length; 
        
        // Tumse niche wale sab tumse bade hai, tumse left wale sab tumse chote hai
        // So simply apply binary search
        
        // We will start searching from top right corner
        int row = 0; 
        int col = m - 1;
        
        while (row < n && col >= 0) {
            if (matrix[row][col] == target) return true;
            if (target < matrix[row][col]) col--;
            else row++;
        }
        return false;
    }
    
}
```

---------------------------------------------------------------------------------------------------------------------------------------------
