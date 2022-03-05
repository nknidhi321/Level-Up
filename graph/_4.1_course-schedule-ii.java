//https://leetcode.com/problems/course-schedule-ii/

// Rajneesh
// More intuitive

/*
    Ex : [0, 1]  {v, u}   u -> v  => u ki depenency 0 hai, v ki dependency 1 hai
    Course 1 ko lene k liye koi dependency nahi hai, so uski indegree 0 hai
    So us course ko queue me daal do and uske nbr pe indegree cadha do
*/


class Solution {
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        // Initialization
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        
        int[] indegree = new int[numCourses];
        
        // Creating graph and indegree 
        for(int[] pre : prerequisites) {
            int u = pre[1];
            int v = pre[0];
            graph[u].add(v);
            indegree[v]++;
        }
        
        // Add all whose indegree is 0
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


//Rajneesh

/*
    Adding graph edge in reverse Order
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
