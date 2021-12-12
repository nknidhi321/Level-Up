//https://leetcode.com/problems/single-element-in-a-sorted-array/

// Archit
// Binary Search 
// O(logN)

class Solution {
    
    public int singleNonDuplicate(int[] arr) {
        int n = arr.length;
    
        // Checking the edge cases at the starting so that we can play safe in BS
        if(n == 1) return arr[0]; // If "the" only element is single
        if(arr[0] != arr[1]) return arr[0]; // If 1st element is single
        if(arr[n - 1] != arr[n - 2]) return arr[n - 1]; // If last element is single
        
        int low = 0;
        int high = arr.length - 1;
      
        while(low <= high) {
            int mid = low + (high - low) / 2;
            
            // Khud pe khare hoke prev aur next check karo, agar dono diff hai matlab arr[mid] he single hai
            if(arr[mid - 1]  != arr[mid] && arr[mid] != arr[mid + 1]) return arr[mid];
            
            
            // Agar dono prev and next different nahi hai,
            // Iska matlab ya toh mid aur prev equal hoga 
            // Ya fir mid aur next equal hoga
            // So, create a division to form 2 groups and get the left and right group count
            // So, hamara answer odd group count k taraf milne ki zyada chances hai,
            // kuki usi single element k wazah se group ka count odd aa raha hai
            // So discard your even group space.
            
           
            // prev aur mid equal hai, toh division mid k baad and next se pehle bnao  
            else if(arr[mid - 1] == arr[mid]) {
                int leftGroupSize = mid - low + 1;  // Left group count [low, mid]
                if(leftGroupSize % 2 == 0) low = mid + 1; // Left group even hai => Right group me answer milega
                else high = mid - 2; // left group k 2 elements ko already check kar chuke hai, so mid - 2
            }
            
            else { // arr[mid] == arr[mid + 1] // mid aur next equal hai, toh division mid se pehle aur prev k baad bnao 
                int rightGroupSize = high - mid + 1; // Right group count [mid, high]
                if(rightGroupSize % 2 == 0) high = mid - 1; // Right group even hai => Left group me answer milega
                else low = mid + 2; // right group k 2 elements ko already check kar chuke hai, so mid + 2
            }
        }    
        
        return -1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO(N) Solution 

XOR operator property :-
a ^ 0 = a
a ^ a = 0

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int XOR = 0;
        for(int i = 0; i < n; i++) {
            XOR ^= nums[i];
        }
        return XOR;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
