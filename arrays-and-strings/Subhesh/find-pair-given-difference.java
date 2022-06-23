// https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1/#
// Wasn't able to use 2 ptr because dono taraf diff badh he raha tha
// So used HashMap or Set wala solution

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

