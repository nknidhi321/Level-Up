// https://leetcode.com/problems/coin-change/

/*
    NOTE : 
    1) Use (int)1e9 inplace of Integer.MAX_VALUE, to prevent from overflow, because if we return Integer.MAX_VALUE from recursion and go on adding 1 to it as in this question then it will lead to overflow.
    2) 1e9 simply means (1) * (10^9)
    3) Typecast 1e9 to int since by default is is double => (int)1e9
*/


// Using for loop of permutation type, can be as combination also 

```
class Solution {

    public int coinChange(int[] coins, int tar) {
        int[] dp = new int[tar + 1];
        Arrays.fill(dp, -1);
        int ans = minCoins_Memo(tar, coins, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public int  minCoins_Memo(int tar, int[] coins, int[] dp) {
        if(tar == 0) return dp[tar] = 0; 
        
        if(dp[tar] != -1) return dp[tar];
        
        int minCoins = (int)1e9;
        for(int ele : coins) {
            if(tar - ele >= 0) {
                minCoins = Math.min(minCoins, minCoins_Memo(tar - ele, coins, dp) + 1);
            } 
        }
        return dp[tar] = minCoins;
    }
    
}
```

----------------------------------------------------------------------------------------------------------

// Tabulation , using permutation type, can be solved using combination also 
// Using for loop

```
class Solution {

    public int coinChange(int[] coins, int tar) {
        int[] dp = new int[tar + 1];
        int ans = minCoins_Tab(tar, coins, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public int  minCoins_Tab(int Tar, int[] coins, int[] dp) {
        for(int tar = 0; tar <= Tar; tar++) {
            if(tar == 0) {
                dp[tar] = 0;
                continue;
            }

            int minCoins = (int)1e9;
            for(int ele : coins) {
                if(tar - ele >= 0) {
                    minCoins = Math.min(minCoins, dp[tar - ele] + 1); // Math.min(minCoins, minCoins_Memo(tar - ele, coins, dp) + 1);
                } 
            }
            dp[tar] = minCoins;
        }
        return dp[Tar];
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Recursive solution without comments 
//TLE

```
class Solution {
    public int coinChange(int[] coins, int amount) {
        int ans = coinChange_Util(coins.length, coins, amount);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public int coinChange_Util(int n, int[] coins, int amount) {
        if(amount == 0 || n == 0) {
            return amount == 0 ? 0 : (int)1e9;
        }
            
        int min1 = (int)1e9; 
        int min2 = (int)1e9;
        
        if(amount - coins[n - 1] >= 0) {
            min1 = coinChange_Util(n, coins, amount - coins[n - 1]) + 1;
        }
        min2 = coinChange_Util(n - 1, coins, amount); 
     
        return Math.min(min1, min2);
    }
    
}
```

---------------------------------------------------------------------------------------------

// Same Recursive Solution with comments
// TLE

```
class Solution {
    public int coinChange(int[] coins, int amount) {
        //Agar amount ko amount -> 0 kar rahe ho, toh n leke chalo(otherwise idx leke chalo), taaki n, n -> 0 bn paaye
        //Aur base case acche se visualize ho paaye (0,0) dp array me
        //amount -> 0 toh n -> 0 leke chalo; n == 0(base case)
        //NOTE : 0 -> amount toh idx leke chalte; idx == coins.length(base case)
        int ans = coinChange_Util(coins.length, coins, amount);
        return ans == (int)1e9 ? -1 : ans;
    
    }
    
    public int coinChange_Util(int n, int[] coins, int amount) {
        if(amount == 0 || n == 0) {
            //0 amount banana h, aur bahut sare coins h, ya 0 coins h, nothing matters because
            //Question is minimum kitne coins use karoge 0 ko 0 bnane me => 0 coins
            //Note : Raste(Path) 1 hai, par 0 amount banana h toh 0 coins lagenge 
            //And why 1e9, because minimum chose karna h so minimum ki identity maximum value hai
            return amount == 0 ? 0 : (int)1e9;
        }
            
        
        //Note : If you initialize with 0 everytime minimum 0 will be returned
        int min1 = (int)1e9; 
        int min2 = (int)1e9;
        
        //Pick 
        //Suppose you receive 1e9 from the call, then min1 = 1e9 + 1
        if(amount - coins[n - 1] >= 0){
            min1 = coinChange_Util(n, coins, amount - coins[n - 1]) + 1; //Why +1, because we are picking the element
        }
        
        //Not Pick
        //Suppose you receive 1e9 from the call, then min2 = 1e9
        min2 = coinChange_Util(n - 1, coins, amount); //Why no +1, because we are not picking the element
        
        //min =  Math.min(1e9 + 1, 1e9) => 1e9
        return Math.min(min1, min2);
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Pick and Not Pick method
// Memoization

```
class Solution {
    public int coinChange(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length + 1][amount + 1];
    
        int ans = coinChange_Memo(coins, coins.length, amount, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public static int coinChange_Memo(int[] coins, int n, int amount, Integer[][] dp) {
        if(amount == 0 || n == 0) {
            if(amount == 0) {
                return dp[n][amount] = 0;
            }
            return dp[n][amount] = (int)1e9;
        }
    
        if(dp[n][amount] != null) return dp[n][amount];
        
        int min1 = (int)1e9;
        int min2 = (int)1e9;
        if(amount - coins[n - 1] >= 0){
           min1 = coinChange_Memo(coins, n, amount - coins[n - 1], dp) + 1;
        }
        min2 = coinChange_Memo(coins, n - 1, amount, dp);
        return dp[n][amount] = Math.min(min1, min2);
    }
}
```

-------------------------------------------------------------

// Pick and Not Pick method
// Tabulation

```
class Solution {
    public int coinChange(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length + 1][amount + 1];
    
        int ans = coinChange_Tab(coins, coins.length, amount, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public static int coinChange_Tab(int[] coins, int N, int AMOUNT, Integer[][] dp) {
        for(int n = 0; n <= coins.length; n++) {
            for(int amount = 0; amount <= AMOUNT; amount++) {
                if(amount == 0 || n == 0) {
                    if(amount == 0){
                        dp[n][amount] = 0;
                        continue;
                    }
                    dp[n][amount] = (int)1e9;
                    continue;
                }

                int min1 = (int)1e9;
                int min2 = (int)1e9;
                if(amount - coins[n - 1] >= 0){
                   min1 = dp[n][amount - coins[n - 1]] + 1; //coinChange_Memo(coins, n, amount - coins[n - 1], dp) + 1;
                }
                min2 = dp[n - 1][amount]; //coinChange_Memo(coins, n - 1, amount, dp);
                dp[n][amount] = Math.min(min1, min2);
            }
        }
        return dp[N][AMOUNT];
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

This solution would have been possible if sum of 2 previous elements would not exceeded the present element.
Example [83, 186, 408, 419] here 186+408 exceeds 419, therefore this solution won't work.
Example [1,2,5] This will work beacuse 1+2<5

```
class Solution {
    public int coinChange(int[] coins, int amount) {
        
        if(amount == 0)
            return 0;
        
        int count = 0;							
				//Sorting  because I have to take maximum element from right so as to make minimum number of coins.
				Arrays.sort(coins);
        
        for(int i = coins.length-1; i>=0; i--) {
            
						if(amount < coins[i]) 
                continue;
            else if(amount > coins[i]){
                amount = amount - coins[i];
                count++;
                i++;
            }
            else if(amount == coins[i])
                return count + 1;
        }
        return -1;
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
