// https://leetcode.com/problems/two-sum/
// Unsorted Array

**Question Explanation**
----------------------
In this question you can have atmost 2 duplicate values for answer sum.
Example:
nums = [3, 3, 2, 2, 2, 2] target = 6 // This TC is valid
nums = [3, 3, 3, 2, 2, 2, 2] target = 6 // This TC is not valid
Because it's given exactly one solution would be present , and if we give three 3's, then there would be conflict like which index should be taken to form pair for answer.

But we can have n number of duplicate values, which will not be the part of our answer like we can have any number of 2's in the above example, 
because 2 + 2 != 6, So 2's index will never be part of our answer so there's no conflict, so this TC is valid.

===========================================================================================================================================================================
  
// HashMap
// TC : O(N)
// SC : O(N)

**Why HashMap is working ??**
-------------------------
In the above exmaple, Key : 2's value would be replaced in the HashMap each time we find a new 2, that would not be a matter of concern 
because 2 will never be a part of our answer in this TC.
And 3 would be added only once in the HashMap, because the next time if we find a 3 again, we will simply be returning from there,
so previous 3's index would still be the same(not replaced).
Hence, HashMap Solution is possible.

**Complexity analysis**
---------------------
map.containsKey() is O(1), assuming no collision
map.put() is O(1), assuming no collision
And for loop is running for N times in worst case,
Hence O(N) Time complexity

**Approach**
----------
When you find an element search for the other half, target - nums[i] in the Map,
If you find it, you got your answer, else add yourself and continue your search.

```
class Solution {

    public int[] twoSum(int[] nums, int target) {

	// {Element, Index}
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[] {i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }
}
```

-------------------------------------------------------------

// Two Pointer
// TC : O(nlogn)
// SC : O(N)
```
class Solution {
    
    public static class Pair {
        int element;
        int index;
        
        public Pair(int element, int index) {
            this.element = element;
            this.index = index;
        }
    }
     
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        
	// Creating PairNums to store element and index
	Pair[] pairNums = new Pair[n];
        for(int i = 0; i < n; i++) {
            pairNums[i] = new Pair(nums[i], i);
        }
				
	// Sorting PairNums based on element
        Arrays.sort(pairNums, (a, b) -> a.element - b.element);

	// Two Pointer
        int start = 0, end = pairNums.length - 1;
        while(start < end) {
            Pair pStart = pairNums[start];
            Pair pEnd = pairNums[end];
            
            int sum = pStart.element + pEnd.element;
            if(sum == target) return new int[] {pStart.index, pEnd.index};
            else if(sum < target) start++;
            else end--;
        }
        return new int[] {-1, -1};
    }
    
}
```

-------------------------------------------------------------

// Brute Force
// TC : O(N^2)
// SC : O(1)
```
class Solution {
    
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                } 
            }
        }    
        return new int[] {-1, -1};
    }
}
```
-------------------------------------------------------------

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Same question here boolean is asked to return 
// https://practice.geeksforgeeks.org/problems/key-pair5616/1#

// Unsorted Array

// HashSet
// TC: O(N)
// SC: O(N)

class Solution {

    boolean hasArrayTwoCandidates(int nums[], int n, int target) {
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            if(set.contains(target - nums[i])) return true;
            
            // Tumhara koi other half nai mil paaya => Tum kisi k other half ho saktey ho
            else set.add(nums[i]); 
        }
        return false;
    }
    
}

//-----------------------------------------------------------------------------------------

// Two Pointer
// TC : O(nlogn)
// SC : O(1)

class Solution {

    boolean hasArrayTwoCandidates(int nums[], int n, int target) {
        Arrays.sort(nums);

	// Two Pointer
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum == target) return true;
            else if(sum < target) start++;
            else end--;
        }
        return false;
    }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
