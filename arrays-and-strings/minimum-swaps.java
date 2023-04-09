// https://practice.geeksforgeeks.org/problems/minimum-swaps/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

class Solution {
    
    static class Pair {
        int val;
        int idx;
        
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
    
    public int minSwaps(int nums[]) {
        int n = nums.length;
        Pair[] sorted = new Pair[n];
        for(int i = 0; i < n; i++) {
            sorted[i] = new Pair(nums[i], i); // Keeping original idx of the unsorted array
        }
        
        Arrays.sort(sorted, (a,b) -> a.val - b.val); // Sort on val
        
        //  Now, Sorted array ko unsorted me convert karo, usme jitne swaps lgenge that is your ans
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(sorted[i].val != nums[i]) { // If sorted array val is not equal to unsorted array val
                // Tum unsorted array me jaha the waha jaao
                int oriUnsortedIdx = sorted[i].idx;
                Pair temp = sorted[i];
                sorted[i] = sorted[oriUnsortedIdx];
                sorted[oriUnsortedIdx] = temp;
                count++;
                // After swap ab jo tumhare idx pe aaya usko v apne unsorted array me apne sahi jagah pahuchana hai
                // so process that same idx again, so do i--, kuki loop k wazah se i++ ho jaaega 
                i--;
            }
            // for(int j = 0; j < n; j++) {
            //     System.out.println(j +"  Bye " + nums[j]); //[0] + ", " + sorted[i][1]);
            // }    
            // System.out.println("...");
        }
        return count;
    }
}
