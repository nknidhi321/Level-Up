// https://leetcode.com/problems/bus-routes/

// BFS me nbr node ka level +1 kar k daalte hai, yaha modified BFS lgegi
// Yaha xth stop se jin jin stop pe le jaa sakte hai(by boarding a bus), sabko +1 level kar dena
// Baaki same normal BFS ka code
// TC : O(V + E), simple BFS ki complexity

class Solution {
    
    public int numBusesToDestination(int[][] busNoArray, int src, int dest) {
       if(src == dest) return 0;
       int n = busNoArray.length; 
    
       // BusNo k corresponding Bus stops di huyi hai, busNoArray[][] waale array me
       // Ab iska inverse bnao taaki O(1) me kaam ho jaaye, 
       // NOTE : BusStop random integers hai, islye map bnana par raha otherwise array bna lete
        
       Map<Integer, ArrayList<Integer>> busStopMap = new HashMap<>();
       
       // Creating inverse map
       for(int busNo = 0; busNo < busNoArray.length; busNo++) {
            for(int stop : busNoArray[busNo]) {
                busStopMap.putIfAbsent(stop, new ArrayList<>());
                busStopMap.get(stop).add(busNo);             
            }       
       }
    
       // Now you can easily figure out, BusNo x se kaha kaha jaa saktey hai, from busNoArray 
       // And, BusStop y pe kon kon si bus aati hai, from busStopMap
        
        
       // BusStop ka visited rakh lo, kuki us BusStop pe jitne v buses aati hai, sbko same level pe process karna hai
       // Also, BusNo ka visited rakh lo, kuki kisi v busNo se uske saare stops/child ko queue me daal dete hai
       Set<Integer> busStopVisited = new HashSet<>();
       boolean[] busNoVisited = new boolean[n];
       
       Queue<Integer> queue = new LinkedList<>();
       queue.add(src);
       busStopVisited.add(src);
        
       int level = 0; 
       while(!queue.isEmpty()) {
           int size = queue.size();
           while(size-- > 0) {
               int busStand = queue.poll();
               
               // src BusStand pe kon kon si bus aati hai, sbko map se nikalenge 
               // And wo saari bus jin jin bus stop pe jaa sakti hai, sabko same level me he process karni hai
               // So sbko ek ek kar k queue me daal do same level wale ko 
               for(int busNo : busStopMap.get(busStand)) {
                    if(busNoVisited[busNo]) continue;
                    
                    busNoVisited[busNo] = true;
                    for(int stop : busNoArray[busNo]) {
                        if(stop == dest) return level + 1;
                        if(!busStopVisited.contains(stop)) { 
                            queue.offer(stop);
                            busStopVisited.add(stop);
                        }
                   }
               }
           }
           level++;
        }
        return -1;
    }
}
