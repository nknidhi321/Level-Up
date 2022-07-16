// https://leetcode.com/problems/reorganize-string/

/*
     Approach :
     ---------
     Sabki freq check karo, sbse max bnde ko sbse pehle rakho, uske just baad sec max freq waale bnde ko rakho
     freq update karo pq me dono char ki, and repeat. 
*/

// Same logic Using Array + PQ(Pair class)
// Follow this

```
class Solution {
    
    static class Pair {
        char ch;
        int freq;
        
        public Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq; 
        }
    }
    
    public String reorganizeString(String s) {
        int n = s.length();
        int[] alphaFreq = new int[26];
        for(int i = 0; i < n; i++) { // Cal freq.
            alphaFreq[s.charAt(i) - 'a']++;
        }
        
        // freq wise decreasing order me sort krenge
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for(int i = 0; i < 26; i++) { // Add in maxHeap by creating pair of {char, freq}
            if(alphaFreq[i] > 0) {
                Pair p = new Pair((char)(i + 'a'), alphaFreq[i]);
                maxHeap.add(p);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(maxHeap.size() >= 2) {
            // Why polling 2 chars ? Kuki adjacent same nai hona chahiye
            Pair firstMax = maxHeap.remove();
            Pair secondMax = maxHeap.remove();
            sb.append(firstMax.ch);
            sb.append(secondMax.ch);
            
            // NOTE : 1 char ko poll krwa k baat nai bnegi, ex : "aaab"  
            
            // Insertion order in heap is imp, why ??
            // Ex : abab 
            // a freq = 2, b freq = 2
            // Nikala and ans bna liya(ab), Now, aFreq = 1, bFreq = 1 
            // Now, both the freq is same, so a ko agar pehle daaloge,
            // tb he "a" maxHeap k head pe hoga
            // Agar b ko pehle daalte toh maxHeap k head pe "b" hota  
            // So, next iteration me jb poll hota,
            // then ans(abba) bnta which is wrong.
            
            // dec freq of the chars // This order is imp
            if(firstMax.freq - 1 > 0) maxHeap.add(new Pair(firstMax.ch, firstMax.freq - 1));
            if(secondMax.freq - 1 > 0) maxHeap.add(new Pair(secondMax.ch, secondMax.freq - 1));
        }
        
        // Only one type of char is left in the maxHeap now,
        // may be with greater than 1 freq
        while(maxHeap.size() == 1) {
            Pair p = maxHeap.remove();
            int freq = p.freq;
            if(freq == 1) sb.append(p.ch); // x 1 he baar hai  
            else return ""; // x 10 baar h, usko baar baar lga diya toh ans invalid ho jaaegi
        }
        
        return sb.toString();
    }
    
}
```

----------------------------------------------------------------------------------------------------------------------

// Same logic  Using HashMap + PQ(Character)

```
class Solution {
    
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        
        // Chars k freq ko decreasing order me sort krenge
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));
        maxHeap.addAll(map.keySet());
        
        while(maxHeap.size() >= 2) {
            // Why polling 2 chars ? Kuki adjacent same nai hona chahiye
            char firstMaxFreq = maxHeap.remove();
            char secondMaxFreq = maxHeap.remove();
            sb.append(firstMaxFreq);
            sb.append(secondMaxFreq);
            
            // dec freq of the chars 
            map.put(firstMaxFreq, map.get(firstMaxFreq) - 1);
            map.put(secondMaxFreq, map.get(secondMaxFreq) - 1);
            
            // Insertion order in heap is imp, why ??
            // Ex : abab 
            // a freq = 2, b freq = 2
            // Nikala and ans bna liya(ab), Now, aFreq = 1, bFreq = 1 
            // Now, both the freq is same, so a ko agar pehle daaloge,
            // tb he "a" maxHeap k head pe hoga
            // Agar b ko pehle daalte toh maxHeap k head pe "b" hota  
            // So, next iteration me jb poll hota,
            // then ans(abba) bnta which is wrong.
            
            if(map.get(firstMaxFreq) > 0) maxHeap.add(firstMaxFreq); // This order is imp
            if(map.get(secondMaxFreq) > 0) maxHeap.add(secondMaxFreq); // This order is imp
        }
        
        // Only one type of char is left in the maxHeap now,
        // may be with greater than 1 freq
        while(!maxHeap.isEmpty()) {
            char ch = maxHeap.remove();
            int freq = map.get(ch);
            if(freq == 1) sb.append(ch); // x type ka 1 he char hai,  
            else return ""; // x 10 baar aaya hua h, usko baar baar lga diya toh ans invalid ho jaaegi
        }
        
        return sb.toString();
    }
    
}
```

---------------------------------------------------------------------------------------------------------------------
