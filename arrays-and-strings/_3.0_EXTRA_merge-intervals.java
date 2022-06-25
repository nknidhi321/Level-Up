// https://leetcode.com/problems/merge-intervals/

 // Follow this
 // Intuition : 

// Sort karo, why sorting ?? 
// Assume (7, 7) mila then (6, 6) 
// So (6, 6) kisi pehle wale interval me merge ho jana chahiye tha.

// Overlapping intervals find karo, apna end and coming interval k start ko compare krwa k
// Agar merge hoenge, then list me daalte jaao jo v merge hone k baad intervals bn raha hai
// NOTE : i pe khare hoke, khud se pehle walo ka ans bna rahe ho, so handle (i == n) also
// last me ans hoga list me, just copy them to an array 

```
class Solution {
    
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort at start 
        
        List<int[]> list = new ArrayList<>(); // Since you don't know ki kitne intervals merge honge, so keep a list
        
        int startSoFar = intervals[0][0];
        int endSoFar = intervals[0][1];
        
        for(int i = 1; i <= n; i++) {
            if(i == n) { // (i == n) Last interval ka ans
                list.add(new int[] {startSoFar, endSoFar});
                continue;
            }
            
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            
            if(endSoFar < currStart) { // Non overlapping
                
                // NOTE : i pe khare hoke, khud se pehle walo ka ans bna rahe ho,
                // So last wala bnda reh jaaega, so uska jugard if(i == n) me kiya hai upar
                list.add(new int[] {startSoFar, endSoFar}); // form your ans
                
                startSoFar = currStart; // For next iteration
                endSoFar = currEnd; // For next iteration
            }
            else {  // endSoFar >= currStart   // overlapping, so merge in same
                endSoFar = Math.max(endSoFar, currEnd); // Merge hoke jo v bada endPoint rahega wo jeetega
            }
        }
        
        // Copying list into array as expected by the method
        int listN = list.size();
        int[][] ans = new int[listN][2];
        for(int i = 0; i < listN; i++) {
            ans[i] = list.get(i);
        }
        
        return ans;
    }
    
}
 ```

--------------------------------------------------------------------------------------------------------------------------------

// Same as above just using stack 
// Using int[] to store in Stack

// Intuition :
// Starting pt. k against sort karo.
// Ek Stack bnao
// Now, one by one find overlapping interval against stack's top, if overlapping then merge and push onto the stack else push as it is.
// Last me jo v stack me hoga wo ans hai.

```
class Solution {
    
    // Intuition : Sort karo, overlapping intervals find karo, stack me daalte jaao and last me ans hoga stack me. 
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        int m = intervals[0].length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        Stack<int[]> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            int[] interval = intervals[i];
            if(!stack.isEmpty() && stack.peek()[1] >= interval[0]) {
                int[] top = stack.pop();
                top[1] = Math.max(top[1], interval[1]);
                stack.push(top);
            }
            else {
                stack.push(interval);
            }
        }
        
        int i = 0;
        int[][] ans = new int[stack.size()][m];
        for(int[] pair: stack) {
            ans[i++] = pair;
        }
        return ans;
    }
}
```

----------------------------------------------------------------------------------------------------------------------------------------

// Same code as above but Using Pair class to store in Stack

```
class Solution {
    
    public static class Pair {
        int start = 0;
        int end = 0;
        
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        int m = intervals[0].length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        Stack<Pair> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            int[] interval = intervals[i];
            if(!stack.isEmpty() && stack.peek().end >= interval[0]) {  // Agar pichla end Pt. bada ya equal hai curr start pt. se toh overlap ho raha hai
                Pair pair = stack.pop();
                stack.push(new Pair(pair.start, Math.max(pair.end, interval[1])));  // Pichla and curr end pt. me se jo v bada hoga wo new end pt. bnega 
            }
            else { // No overlapping
                stack.push(new Pair(interval[0], interval[1]));
            }
        }
				
        int i = 0;
        int[][] ans = new int[stack.size()][m];
        for(Pair pair : stack) {
            ans[i][0] = pair.start;
            ans[i++][1] = pair.end;
        }
        return ans;
    }
}
```
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
