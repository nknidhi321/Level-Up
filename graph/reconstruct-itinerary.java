//https://leetcode.com/problems/reconstruct-itinerary/

```
// The idea is to mark the edges visited and not the nodes
// To mark the edges visited, since you can use the edge only once so just remove the directed edge from map
// this will be used as visited check, or you can hash the edge example "JFK_MUC" and mark it as visited

class Solution {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        LinkedList<String> ans = new LinkedList<>();
        
        for(List<String> ticket : tickets) {
            PriorityQueue<String> pq = map.getOrDefault(ticket.get(0), new PriorityQueue<>());
            pq.add(ticket.get(1));
            map.put(ticket.get(0), pq);
        }
        
        dfs("JFK", ans, map);
        return ans;
    }    

    public void dfs(String src, LinkedList<String> ans, Map<String, PriorityQueue<String>> map) {
        PriorityQueue<String> nbrs = map.get(src);
        // Check if the src exists in map, and if it exists the pq against it is not empty
        // Don't use for loop, use while loop bcoz you are deleting from pq so you have to make empty check
        while(map.containsKey(src) && !map.get(src).isEmpty()) { 
            dfs(nbrs.poll(), ans, map); // .remove() gives t/f whereas poll() gives the removed ele
        }
        
        // Form your ans at last because your destination can come in middle, 
        // and even if it comes in middle, the destination will be added at last(since dfs) by using .addFirst()
        ans.addFirst(src);     
    }
    
}
```
