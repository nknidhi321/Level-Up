// https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1/#

// Two pointer 
// NOTE : For diff question, keep both pointer at 0 only, isme ei ko badhao toh diff badhega, and si ko badhao toh diff ghatega
// Wasn't able to use 2 ptr at the opp end, because dono taraf diff badh he raha tha

class Solution {
    
    public boolean findPair(int arr[], int n, int reqDiff) {
      Arrays.sort(arr); // Sorting is must taaki pta chal paaye si ko badhaye ya ei ko
      int si = 0, ei = 0;
      
      while(ei < n) {
          
          // n = 5  reqDiff = 0 
          // arr = 1 2 6 3 4
          // Agar ei , si equal ho jaaega toh diff 0 ho jaaega, which is equal to reqDiff
          // But arr me waisa koi 2 bnda nai hai jo ye reqDiff de paaye, so you ans should be false
          // So, make a check when si == ei, don't cal your ans at that state
          if(si == ei) {  
              ei++;
              continue;
          }
          
          int diff = arr[ei] - arr[si];
          if(diff == reqDiff) return true;
          else if(diff < reqDiff) ei++; // diff agar kam hai toh window ka length badhao
          else si++; // diff agar zyada hai toh window k lengrth ko shrink karo 
      }
      return false;
    }
    
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using HashMap/Set

// Diff can be found in these 2 ways :  [So, check both conditions]
// x - arr[i] = diff
// arr[i] - x = diff

class Solution {
    
    public boolean findPair(int arr[], int n, int reqDiff) {
       Set<Integer> set = new HashSet<>();
       for(int i = 0; i < n; i++) {
           if(set.contains(reqDiff + arr[i]) || set.contains(arr[i] - reqDiff)) return true;
           set.add(arr[i]);
       }
       return false;
    }
    
}

