//https://leetcode.com/problems/course-schedule/

//Rajneesh
//By creating graph

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
