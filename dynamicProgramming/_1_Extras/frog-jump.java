// https://leetcode.com/problems/frog-jump/

class Solution {
    
    public boolean canCross(int[] stones) {
        
        int n = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) map.put(stones[i], new HashSet<Integer>()); 
        map.get(0).add(1); // jump of 1 can be taken from 0th stone (given) 
        
        for(int i = 0; i < n; i++) {
        	int currStone = stones[i];
            Set<Integer> jumps = map.get(currStone);
        	for(int jump : jumps) {
        		int position = currStone + jump;

                // Jaha v pahuch sakta hai, us stone k jumps set ko update kar do
                if(map.containsKey(position)) {
                    if(position == stones[n - 1]) return true;

                    Set<Integer> set = map.get(position);
                    if(jump - 1 > 0) set.add(jump - 1); // Because <= 0 jumps will never make you reach your des
                    set.add(jump);
                    set.add(jump + 1);

                    map.put(position, set);
                }
        	}
        }
        return false;
    }
}
