// https://leetcode.com/problems/course-schedule/

// Rajneesh
// By creating graph

// Ex : [0, 1]  {v, u}   u -> v  => u ki depenency 0 hai, v ki dependency 1 hai
// Course 1 ko lene k liye koi dependency nahi hai, so uski indegree 0 hai
// So us course ko queue me daal do and uske nbr pe indegree cadha do


class Solution {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
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
        
        
        int nodeCount = 0;
        while(!queue.isEmpty()) {
            int vtx = queue.poll(); // Pop a vtx and *
            nodeCount++;
            
            for(int v : graph[vtx]) { // * Relax it's nbr
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return nodeCount == numCourses;
    } 
}

----------------------------------------------------------------------------------
    
// Mine
// Same but Just u v difference
    
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
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
        
        int nodeCount = 0;
        while(!queue.isEmpty()) {
            int vtx = queue.poll();
            nodeCount++;
            
            for(int v : graph[vtx]) {
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return nodeCount == numCourses;
    } 
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Mine
//Same as above but without creating graph
//Not efficient since travelling over all matrix to find neigh for a vtx 

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Queue<Integer> queue = new LinkedList<Integer>(); 
        
        int[] indegree = new int[numCourses];
        for(int[] pre : prerequisites) {
            indegree[pre[1]]++;
        }
        
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int nodeCount = 0;
        while(!queue.isEmpty()) {
            int vtx = queue.poll();
            nodeCount++;
            
            for(int i = 0; i < prerequisites.length; i++) {
                if(vtx == prerequisites[i][0]) {
                    if(--indegree[prerequisites[i][1]] == 0) { 
                        queue.add(prerequisites[i][1]);
                    }
                }
            }
        }
        
        return nodeCount == numCourses ? true : false;
    } 
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
