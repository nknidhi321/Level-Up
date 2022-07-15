// https://practice.geeksforgeeks.org/problems/subarray-with-0-sum-1587115621/1/#

class Solution {

    public static boolean findsum(int arr[],int n) {
        return subArraySum0(arr);
    }
     
    public static boolean subArraySum0(int[] arr) { 
        int sum = 0;
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        set.add(0); // Jab arr k saare log ka sum 0 hoga, so ek 0 sum ka hona imp hai set me
        
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            if(set.contains(sum)) return true;
            else set.add(sum);
        }
        return false;
    }
    
}
