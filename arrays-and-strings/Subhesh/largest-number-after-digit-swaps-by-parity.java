// https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity/

TC : O(N)
```
//Get frequency of all the digits from 0-9
//Construct the newNumber by taking the maximum possible digit for that location => odd/even is determined by the parity of the value in the original number provided


class Solution {
    
 public int largestInteger(int num) {
        char[] nums = Integer.toString(num).toCharArray();
        
        //Calculate the frequency of each digit from 0 - 9
        int[] frequency = new int[10];
        for (int index = 0; index < nums.length; index++) {
            frequency[nums[index] - '0']++;
        }
        
        int newNumber = 0;
        int evenIndex = 8; // corresponds to the number 8 
        int oddIndex = 9; // corresponds to the number 9 

        // construct the number
        for(int index = 0; index < nums.length; index++) {
            // If the parity of number in current index is even
            if(nums[index] % 2 == 0) {
                // Get first even digit of non-zero frequency
                while(frequency[evenIndex]==0) {
                    evenIndex -= 2;
                }
                frequency[evenIndex]--;
                newNumber = newNumber*10 + evenIndex;
            } else {
                // If the parity of number in current index is odd
                // Get first odd digit of non-zero frequency
                while(frequency[oddIndex]==0) {
                    oddIndex -= 2;
                }
                frequency[oddIndex]--;
                newNumber = newNumber*10 + oddIndex;
            }
        }
       
        return newNumber;
    }
    
}
```
-----
TC : N(logN) 
```
class Solution {
    
    public int largestInteger(int num) {
        PriorityQueue<Integer> opq = new PriorityQueue<>();
        PriorityQueue<Integer> epq = new PriorityQueue<>();
        int bnum = num;
        while(num>0){
            int cur = num%10;
            if(cur%2==1){
                opq.add(cur);
            }else{
                epq.add(cur);
            }
            num /= 10;
        }
        
        System.out.println(opq);
        System.out.println(epq);
        
        
        StringBuilder sb = new StringBuilder();
        num = bnum;
        while(num>0){
            int cur = num%10;
            System.out.println(cur);
            if(cur%2==1)
                sb.insert(0, opq.poll());
            else
                sb.insert(0, epq.poll());
            num /= 10;
        }
        return Integer.parseInt(sb.toString());
    }
    
}
```
-----
TC : N^2
Look for a digit on the right that is bigger than the current digit and has the same parity, and swap them.
(a[j] - a[i]) % 2 == 0 parity check (true if both a[j] and a[i] are even or both are odd)

```
class Solution {
     public int largestInteger(int n){
        char[] a = String.valueOf(n).toCharArray();
        for(int i = 0; i < a.length; i++)
            for(int j = i + 1; j < a.length; j++)
                if(a[j] > a[i] && (a[j] - a[i]) % 2 == 0){
                    char t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
        return Integer.parseInt(new String(a));
    }
}
```
