// https://leetcode.com/problems/valid-triangle-number/

/*
    The condition for the triplets (a, b, c) representing the lengths of the sides of a triangle, to form a valid triangle, 
    is that the sum of any two sides should always be greater than the third side alone. i.e. (a + b > c) , (b + c > a) and (a + c > b)

    If we sort the given nums array once, we can solve the given problem in a better way. 
    This is because, if we consider a triplet (a, b, c) such that a ≤ b ≤ c, 
    we need not check all the three inequalities for checking the validity of the triangle formed by them. 

    But, only one condition a + b > c would suffice. This happens because c ≥ b and c ≥ a. 
    Thus, adding any number to c will always produce a sum which is greater than either a or b considered alone.

    Thus, the inequalities c + a > b and c + b > a are satisfied implicitly by virtue of the property a < b < c.

    TC : O(N ^ 2)
    
*/

class Solution {
    
    public static int n;
    
    public int triangleNumber(int[] nums) {
        n = nums.length;
        Arrays.sort(nums);
        
        int count = 0;
        for(int i = n - 1; i >= 2; i--) {  // Fixing the ith element and calling 2 pointer from [0 to i-1]
            count += twoSumGreater(0, i - 1, nums[i], nums);
        }
        return count;
    }
    
    public static int twoSumGreater(int start, int end, int target, int[] nums) {
        // Two pointer
        int count = 0;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum > target) {
                count += end - start;
                end--;
            }
            else start++;
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force 
// TLE
// TC : O(N^3)

class Solution {
    
    public static int n;
    
    public int triangleNumber(int[] nums) {
        n = nums.length;
        int count = 0;
        for(int i = 0; i <= n - 3; i++) {
            for(int j = i + 1; j <= n - 2; j++) {
                for(int k = j + 1; k <= n - 1; k++) {
                    if(nums[i] + nums[j] > nums[k] &&
                       nums[i] + nums[k] > nums[j] &&
                       nums[k] + nums[j] > nums[i]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
