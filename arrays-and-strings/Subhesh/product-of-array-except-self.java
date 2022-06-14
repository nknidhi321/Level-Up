// https://leetcode.com/problems/product-of-array-except-self/

// TC : O(2N), SC : O(1)

```
class Solution {
    
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int prod = 1, zeroCounts = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) zeroCounts++; // counting zeros
            else prod *= nums[i]; // Poore array ka product except 0's 
        }
        
        if(zeroCounts > 1) return new int[n]; // Agar 1 se zyada 0's hai then sbka ans 0 hoga 
    
        // Now, we are sure if Incase if we have any 0 that would be only a single 0
        
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            // Agar mai 0 hu toh baaki sbka product mera ans hoga
            if(nums[i] == 0) ans[i] = prod; 
            
            // If i come for this checking mtlb mai 0 nai hu, 
            // par ek element 0 hai, jiske wazah se mera ans 0 ho jaaega 
            else if(zeroCounts == 1) ans[i] = 0;  

            // Agar ek v 0 nahi tha then (sbka product)/(by me) will be my ans 
            else ans[i] = prod / nums[i];
        }
        return ans;
    }
    
}
```

---------------------------------------------------------------------------------------------------------

// TC : O(3N), SC : O(2N)

```
class Solution {
    
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        
        // Left se ab tak ka product // Prefix product
        int[] leftProduct = new int[n];
        for(int i = 0; i < n; i++) {
            leftProduct[i] = i == 0 ? nums[i] : leftProduct[i - 1] * nums[i]; 
        }
        
        // Right se ab tak ka product  // Suffix product
        int[] rightProduct = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            rightProduct[i] = i == n-1 ? nums[i] : nums[i] * rightProduct[i + 1]; 
        }
        
        // Khud ko chor k baaki ka product nikal do using leftProduct && rightProduct
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            if(i == 0) ans[i] = rightProduct[i + 1];
            else if(i == n - 1) ans[i] = leftProduct[i - 1];
            else ans[i] = leftProduct[i - 1] * rightProduct[i + 1];
        }
        return ans;
    }
    
}
```

--------------------------------------------------------------------------------------------------------

// TC : O(N^2) brute force, SC : O(1)

```
class Solution {
    
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for(int i = 0; i < n; i++) {
            int prod = 1;
            for(int j = 0; j < n; j++) {
                if(i != j) {
                    prod *= nums[j];
                }
            }
            ans[i] = prod;
        }
        return ans;
    }
    
}
```
----------------------------------------------------------------------------------------------------------------
