// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/

// Topo Sort, Kahn's Algo

class Solution {
    
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        
        Map<String, List<String>> graphMap = new HashMap<>();
        Map<String, Integer> indegreeMap = new HashMap<>();
        
        // Creating graph  // u(ingredient) -> v(recipe)
        for(int i = 0; i < ingredients.size(); i++) {
            String v = recipes[i];
            for(String u : ingredients.get(i)) {
                if(graphMap.containsKey(u)) {
                    List<String> nbrs = graphMap.get(u);
                    nbrs.add(v);
                    graphMap.put(u, nbrs);
                }
                else {
                    List<String> nbrs = new ArrayList<>();
                    nbrs.add(v);
                    graphMap.put(u, nbrs);
                }
                indegreeMap.put(v, indegreeMap.getOrDefault(v, 0) + 1);
            }
        }
        
        Queue<String> queue = new LinkedList<>();
        // Add all items in queue whose indegree is 0
        // supplies indegree will always be 0, supplies kisi pe dependent nahi hai
        for(String supply : supplies) {
            queue.add(supply);    
        }
        
        List<String> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            String item = queue.poll(); // Poll kiye huye items hamara answer nahi bnega *
            
            if(graphMap.containsKey(item)) {
                List<String> nbrs = graphMap.get(item);
                for(String v : nbrs) {
                    if(indegreeMap.containsKey(v)) {
                        int indegree = indegreeMap.get(v);
                        indegreeMap.put(v, --indegree);
                        if(indegree == 0) {
                            ans.add(v);  // * Jin nbrs k indegree 0 ho jaaenge wo answer hai => CanBePrepared
                            queue.add(v);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
