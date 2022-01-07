//https://leetcode.com/problems/course-schedule-ii/

//Rajneesh

/*
    Adding graph edge in the given order u -> v,
    so fill the answer from last index => Parent nodes gets filled from last to first
    Final ans array should be from Child -> Parent
*/

class Solution {
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
 
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        
        int[] indegree = new int[numCourses];
        for(int[] pre : prerequisites) {
            int u = pre[0];
            int v = pre[1];
            graph[u].add(v);
            indegree[v]++;
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        //In topBFS we add parents first and then child
        //And parent node should be as last as possible in the array as asked in question
        //So start filling ans from back so that parents can be as last as possible
        
        int[] ans = new int[numCourses];
        int i = numCourses - 1;
        while(!queue.isEmpty()) {
            int vtx = queue.poll();
            ans[i--] = vtx;
            
            for(int v : graph[vtx]){
                if(--indegree[v] == 0){
                    queue.add(v);
                }
            }
        }
        return i == -1 ? ans : new int[0];
    } 
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Rajneesh

/*
    Adding graph edge in reverse Order v -> u,
    so that answer can be filled from 0th index => Child nodes gets filled from first to last
    Final ans array should be from Child -> Parent
*/

class Solution {
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
 
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        
        int[] indegree = new int[numCourses];
        for(int[] pre : prerequisites) {
            int u = pre[0];
            int v = pre[1];
            graph[v].add(u);  //Creating graph from v -> u //Answer is asked in reverse Order
            indegree[u]++;    //Creating indegee at u 
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int[] ans = new int[numCourses];
        int i = 0;
        while(!queue.isEmpty()) {
            int vtx = queue.poll();
            ans[i++] = vtx;
            
            for(int v : graph[vtx]){
                if(--indegree[v] == 0){
                    queue.add(v);
                }
            }
        }
        return i ==  numCourses ? ans : new int[0];
    } 
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
