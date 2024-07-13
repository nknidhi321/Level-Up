// https://leetcode.com/problems/brick-wall/

// Dry run
// wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
// 1 -> 3
// 2 -> 1
// 3 -> 3
// 4 -> 4
// 6 -> 6    
// 5 -> 2

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int n = wall.size();
        int max = 0; // max edges
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int prefixSumEdgePos = 0;
            List<Integer> li = wall.get(i);
            for(int j = 0; j < li.size() - 1; j++) { // last edge pe vertical line nai bna sakte given in question so li.size() - 1
                int width = li.get(j);
                prefixSumEdgePos += width;
                int freq = map.getOrDefault(prefixSumEdgePos, 0) + 1;
                map.put(prefixSumEdgePos, freq);
                max = Math.max(max, freq);
            }
        }
        return n - max; // total - edges = brickCuts
    }
}

// Same approach as above with comments

class Solution {
    public int leastBricks(List<List<Integer>> wall) 
    {
        HashMap<Integer, Integer> edge_frequency = new HashMap<>(); // HashMap to store the number of common edges among the rows
        int max_frequency = 0; // Variable to store the frequency of most occuring edge
        
        for(int row=0; row<wall.size(); row++) // Iterating through each row
        {
            int edge_postion = 0; // Variable to store different edge postion
            
            for(int brick_no=0; brick_no<wall.get(row).size()-1; brick_no++) // Iterating through each brick inside a row
            {
                int current_brick_length = wall.get(row).get(brick_no); // Length of the current brick
                edge_postion = edge_postion + current_brick_length; // Next Edge Position = Previous Edge Position + Current Brick's Length
                edge_frequency.put(edge_postion,edge_frequency.getOrDefault(edge_postion,0)+1); // Incrementing the Frequency of just calculated Edge Postion
                max_frequency = Math.max(edge_frequency.get(edge_postion), max_frequency); // Comparing the "Frequency of just calculated Edge Postion" with "Max Frequency seen till now" & storing whichever is greater.
            }
        }
        return wall.size() - max_frequency; // returning (Number of Bricks Crossed by Line) i.e. (Number of Rows in Wall - Frequency of Most Occuring Edge)
    }
}
