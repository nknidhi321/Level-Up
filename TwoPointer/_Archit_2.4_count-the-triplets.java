// https://practice.geeksforgeeks.org/problems/count-the-triplets4615/1#

/*
    Koi 2 number kisi tisre number k equal kab hoga ??
    
    Jab "2 chote" numbers ko add karenge tvi kisi "tisre bade" number ko achieve kar paaenge hum
    
    Isliye loop piche se lgao kuki array sorted rahega, so target hamesha ith element ko bnaenge
    and two pointer [0 to i-1] pe lga denge jiski range obviously ith element se choti hogi

*/

class Solution {
    
    public int countTriplet(int nums[], int n) {
        Arrays.sort(nums);
        int count = 0;
        for(int i = n - 1; i >= 2; i--) {  // Fixing the ith element and calling 2 pointer from [0 to i-1]
            count += twoSum(0, i - 1, nums[i], nums);
        }
        return count;
    }
    
    public int twoSum(int start, int end, int target, int[] nums) {
        // Two pointer
        int count = 0;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum == target) {
                count++;
                start++;
                end--;
            }
            else if(sum < target) start++;
            else end--;
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
