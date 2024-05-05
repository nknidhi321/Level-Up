// https://leetcode.com/problems/maximum-swap/
// https://www.youtube.com/watch?v=IiAd7twX0xU

/*
    NOTE :-
    ----
    Sbse max digit ko 0th idx pe daal denge, this is wrong logic 
    Ex TC : 98368  Here 3 and last 8 should be swapped
*/

// TC : O(10 * n) = O(n), SC : O(10) = O(1)
// Store lastIndexOf All digits [0, 9]
// Now, iterate from left over num array, 
// Agar tum 4 ho toh tumko tumhare se aage k bnde,
// starting from max like 9 8 7 6 5 digits he replace kar paaenge 
// provided these digits exits after you in the num array
// And starting from 9, 8, 7... So that the number formed is maximum.

class Solution {
    
    public int maximumSwap(int num) {
        int[] lastIndexOfDigits = new int[10];
        Arrays.fill(lastIndexOfDigits, -1);
        StringBuilder sb = new StringBuilder(num + "");
        int n = sb.length();
        
        // lastIndexOfDigits 
        for(int i = 0; i < n; i++) {
            int digit = Integer.parseInt(sb.charAt(i) + "");
            lastIndexOfDigits[digit] = i;
        }
        
        // for(int i = 0; i < 10; i++) System.out.println(i + " : " + lastIndexOfDigits[i]);
        
        boolean flag = false;
        for(int i = 0; i < n; i++) {
            int currDigit = Integer.parseInt(sb.charAt(i) + "");
            // Tumse aage ka sbse bada bnda jo exit karta hai
            // num array of digits me wo he tumko replace kar sake hai to form maximum number
            for(int digit = 9; digit > currDigit; digit--) { 
                if(lastIndexOfDigits[digit] > i && digit != currDigit) {
                    flag = true;
                    swap(i, lastIndexOfDigits[digit], sb);
                    break;
                }
            }
            if(flag) break;
        }
        return Integer.parseInt(sb.toString());
    }
    
    public void swap(int a, int b, StringBuilder s) {
        char temp = s.charAt(a);
        s.setCharAt(a, s.charAt(b));
        s.setCharAt(b, temp);
    }
    
}
---------------------------------------------------------------------------------------------------------------------
/*

    TC : O(n)
    SC : O(n)
    
    Approach:-
    --------
    Create a SuffixMax(mere se aage k bndo ka, not including me, Why ? Ex : 1993)
    Now, iterate from left and jis pehle bnde ne bola ki mera max mai khud nahi hu
    usko uske max k saath swap kar do
*/

```
class Solution {
    
    public int maximumSwap(int num) {
    
        StringBuilder sb = new StringBuilder(""+num); // Put it as a String
        int n = sb.length();

        // Calculating suffixMaxIdx, it will contain idx of max bnda so far from right
        int[] suffixMaxIdx = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) suffixMaxIdx[i] = n - 1;
            else {
                // Why not >= ? Ex : 1993, Here 2nd wale 9 ko 1 se swap hona chahiye to form maximum
                // So suffixMaxIdx = [2, 2, 2, 3]
                // If you would have taken >= then 1 and first 9 swap ho jata, which is wrong
                if(sb.charAt(i) > sb.charAt(suffixMaxIdx[i + 1])) suffixMaxIdx[i] = i; 
                else suffixMaxIdx[i] = suffixMaxIdx[i + 1];
            }
        }
        

        // In suffixMaxIdx, jo v aisa pehla bnda hai jo keh raha hai mera max mere right me hai 
        // Us nums[ith] bnde ko swap krna hai, nums[suffixMaxIdx[i]] pe jo bnda rakha hai us se 
        for(int i = 0; i < n; i++) {
            
            // Agar mai suffixMaxIdx[i] k equal nahi hu and
            // mujhe jis se swap hona hai wo mere jaisa nahi hai toh he swap karo // Ex : 98368
            // If mere jaise ka chcek nahi lgate toh 8 and 8 apas me swap ho jaatey
            if(i != suffixMaxIdx[i] && sb.charAt(i) != sb.charAt(suffixMaxIdx[i])) {
                int idx = suffixMaxIdx[i];
                char currChar = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(idx));
                sb.setCharAt(idx, currChar);
                break; // Atmost 1 swap so break
            }
        }
        return Integer.parseInt(sb.toString());
    }
    
}
```
---------------------------------------------------------------------------------------------------------------------

// Using sorting : O(nlogn)
// unsorted and sorted array me check karo, 
// Iterate from left jo sbse pehla diff element hai
// unhi dono elements ko ek dusre k original pos se swap karna hoga
// Now, Ex : 993885 here 8 should be swapped with 3 but which 8 ?? 
// So, 998835, 998385 we have to choose the max number so 998835 should be our answer
// So, you should pick up the rightmost idx of 8 to swap

class Solution {
    public int maximumSwap(int num) {
        List<Integer> sorted = new ArrayList<>();
        List<Integer> original = new ArrayList<>();
        int n = num;
        while(n > 0) {
            int rem = n % 10;
            n = n / 10;
            sorted.add(rem);
            original.add(rem);
        }
        Collections.reverse(original);
        Collections.sort(sorted, Collections.reverseOrder());
        
        // First diff idx
        int shouldBe = -1;
        int sortedPositionOfshouldBe = -1;
        for(int i = 0; i < original.size(); i++) {
            if(original.get(i) != sorted.get(i)) {
                shouldBe = sorted.get(i);
                sortedPositionOfshouldBe = i;
                break;
            }
        }
        
        if(sortedPositionOfshouldBe == -1) return num;
        
        // Pick the rightmost idx of shouldBe
        int lastOccuringPositionOfShouldBe = -1;
        for(int i = original.size() - 1; i >= 0; i--) {
            if(original.get(i) == shouldBe) {
                lastOccuringPositionOfShouldBe = i;
                break;
            }
        }
        
        // swap both
        original.set(lastOccuringPositionOfShouldBe, original.get(sortedPositionOfshouldBe));
        original.set(sortedPositionOfshouldBe, shouldBe);
        
        // Form number from ans list
        int pow = 0, ans = 0;
        for(int i = original.size() - 1; i >= 0; i--) {
            ans += original.get(i) * Math.pow(10, pow++);
        }
        
        return ans;
    }
    
}
