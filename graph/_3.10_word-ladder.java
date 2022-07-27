// https://leetcode.com/problems/word-ladder/
// BFS + HM

/*
    **Using HashMap**
    The idea is to keep a HashMap to keep track of the visited words, since the vertexs are of String type so we are taking map 
    (If the nodes would have been int type we would have kept  an array to keep track of the visited vertexs).

    Here neighbour of a word would be a word, whose only one letter can be changed and that new changed word should be there in the dictionary list.

    Like if we have "hit", 
    we are keeping 2 for loops, The outer for loop will keep track of the indexes of the string and the inner for loop will iterate from 'a' to 'z'.

    So, for "hit" word: The outer loop will run thrice (length of hot is 3) and the inner loop will run 26 times.

    ait, bit, cit, dit, eit,....,zit                          //0th index changing
    hat, hbt, hct, hdt, hft,........,hzt                //1st index changing
    hia, hib, hic, hid, hie, hif,..................,hiz    //2th index changing(last)

    Note: if we find that changed word to be the endword we will return true, else if we find a word that is there in the dictionary and it is not visited
    then keep on adding that changed word to the queue (Whenever a word would be added to the queue this simply means that it is the neighbour to the current
    polled "currString" from the queue).

    If the endWord is not found or is not reachable from the beginWord simply return 0;
*/

```
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        if(!wordList.contains(endWord))
            return 0;
        
        HashMap<String, Boolean> visitedMap = new HashMap<String, Boolean>();
        for(String s : wordList){
            visitedMap.put(s, false);
        }
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        visitedMap.put(beginWord, true);
        int level = 1;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                String currString = queue.poll();
                
                for(int i = 0; i < currString.length(); i++){
                    char[] word = currString.toCharArray();
                    for(char ch = 'a'; ch <= 'z'; ch++){
                        word[i] = ch;
                        String neighString = String.valueOf(word);
                        if(neighString.equals(endWord)){
                            return level + 1;
                        }
                        if(visitedMap.containsKey(neighString) && !visitedMap.get(neighString)){
                            queue.offer(neighString);
                            visitedMap.put(neighString, true);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
```

--------------------------------------------------------------------------------------------------------------------

// **Using HashSet**
// Keep unvisited vertex in set, and when that vertex is visited simply remove that vertex from set. Rest is same.

```
class Solution {
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        
        Set<String> unvisitedSet = new HashSet<>(wordList);
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        unvisitedSet.remove(beginWord);
        int level = 1;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                String currString = queue.poll();
                
                for(int i = 0; i < currString.length(); i++) {
                    char[] word = currString.toCharArray();
                    for(char ch = 'a'; ch <= 'z'; ch++) {
                        word[i] = ch;
                        String neighString = String.valueOf(word);
                        if(neighString.equals(endWord)) {
                            return level + 1;
                        }
                        
                        if(unvisitedSet.contains(neighString) && wordList.contains(neighString)){
                            queue.offer(neighString);
                            unvisitedSet.remove(neighString);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
```
-----------------------------------------------------------------------------------------------------------------------------
