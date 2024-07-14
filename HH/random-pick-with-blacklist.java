// https://leetcode.com/problems/random-pick-with-blacklist/
// Reference : https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap

class Solution {

    Map<Integer, Integer> mapBlacklistedIntegerWithBottomIntegers;
    Set<Integer> blacklistedSet;
    Random random;
    int top;
    
    public Solution(int n, int[] blacklist) {
        mapBlacklistedIntegerWithBottomIntegers = new HashMap<>();
        blacklistedSet = new HashSet<>();
        random = new Random();
        top = n - blacklist.length;
        int bottomInteger = n - 1; // Will use as indexes so -1
        
        // Add Blacklisted Integer in Set to find in O(1)
        for(int blacklistedInteger : blacklist) {
            blacklistedSet.add(blacklistedInteger);
        }
        
        // Map Blacklisted Integer With Bottom Integers
        for(int blacklistedInteger : blacklist) {
            if(blacklistedInteger < top) {
                while(blacklistedSet.contains(bottomInteger)) { // if BottomInteger if alreay a blacklistedInteger
                    bottomInteger--; // Find BottomInteger which is not blacklistedInteger
                }
                // Found BottomInteger which is not blacklistedInteger, so map the blacklistedInteger -> BottomInteger
                mapBlacklistedIntegerWithBottomIntegers.put(blacklistedInteger, bottomInteger--);
            }
        }
    }

    public int pick() {
        // If random comes which is a blacklistedInteger 
        // return it's corresponding mapped value which is not blacklistedInteger
        int randomInteger = random.nextInt(top);
        
        if(blacklistedSet.contains(randomInteger)){
            return mapBlacklistedIntegerWithBottomIntegers.get(randomInteger);
        }
        return randomInteger;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
