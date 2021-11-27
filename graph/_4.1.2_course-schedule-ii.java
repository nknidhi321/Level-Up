//https://leetcode.com/problems/course-schedule-ii/

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
        //And parent node should be as last as possible in the ans as asked in question
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
