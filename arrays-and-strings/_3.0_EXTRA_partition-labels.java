// https://leetcode.com/problems/partition-labels/

// Using Array

class Solution {
    
    static class Pair {
        int start;
        int end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString() {
            return this.start + " " + this.end;
        }
    }
    
    public List<Integer> partitionLabels(String s) {
        if(s.equals("")) return new ArrayList<>();
        
        List<Integer> ans = new ArrayList<>();
        Pair[] intervals = new Pair[26];
        
        // Kept Integer.MAX_VALUE so that while sorting these useless Pairs comes at the end
        Arrays.fill(intervals, new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE)); 
        
        int n = s.length();
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if(intervals[ch - 'a'].start == Integer.MAX_VALUE) { // Found start idx of alphabet
                Pair p = new Pair(i, i); // (i, i) because an alphabet might come only once so start == end 
                intervals[ch - 'a'] = p;
            }
            else { // Found end idx of alphabet
                Pair p = intervals[ch - 'a'];
                p.end = i;
            }
        }
        
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        // for(int i = 0; i < 26; i++) {
        //     System.out.println(intervals[i]);
        // }
        
        int prevStart = intervals[0].start;
        int prevEnd = intervals[0].end;
        
        // Note it's sorted array and useless Pairs are at the end
        // Now the problem is ditto Merge Interval
        for(int i = 0; i < 26; i++) {
            Pair interval = intervals[i];   
            if(interval.start != Integer.MAX_VALUE) {
                if(interval.start <= prevEnd) {
                    prevEnd = Math.max(prevEnd, interval.end);
                }
                else {
                    //System.out.println(interval);
                    ans.add(prevEnd - prevStart + 1);
                    prevStart = interval.start;
                    prevEnd = interval.end;
                }
            }
            else {  // if(interval.start == Integer.MAX_VALUE) iske baad sab useless pairs he hai
                break; 
            }
        }
        
        ans.add(prevEnd - prevStart + 1);
        return ans;
    }
}

//-------

// Same as above but using Map + ArrayList
class Solution {
    
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        Map<Character, int[]> map = new HashMap<>(); // { Character, [startIdx, endIdx] }
        
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) { // Already existed
                int[] interval = map.get(c);
                interval[1] = i; // Upadte only endIdx
                map.put(c, interval);
            }
            else { // Appeared 1st time
                int[] interval = new int[2];
                interval[0] = interval[1] = i; // Upadte both [startIdx, endIdx]
                map.put(c, interval);
            }
        }
        
        // Printing map
        // for (Map.Entry<Character, int[]> entry : map.entrySet()) {
        //     System.out.println("Key = " + entry.getKey() +
        //                      ", Value = " + entry.getValue()[0] + "\t" + entry.getValue()[1]);
        // }
        
        
        // NOTE : StartIdx k acc. sorted milega intervals me
        // Kuki we are travelling over s from 0, so no need to sort intervals in the merge function
        int[][] intervals = new int[map.size()][2];
        int idx = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) { // Jab pehli baar mil raha hai tab he usko intervals me daal do 
                intervals[idx++] = map.get(c); // Us variable ka khata cadha diya
                map.remove(c); // And ab map se nikal do, kuki aage jitne baar v mile wo, we don't care  
            }
        }        
        
        // Call mergeIntervals
        return merge(intervals);
    }
        
    public List<Integer> merge(int[][] intervals) {
        int n = intervals.length;
        // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort at start 
        
        List<Integer> list = new ArrayList<>(); 
        
        int startSoFar = intervals[0][0];
        int endSoFar = intervals[0][1];
        
        for(int i = 1; i <= n; i++) {
            if(i == n) { // (i == n) Last interval ka ans
                list.add(endSoFar - startSoFar + 1);
                continue;
            }
            
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            
            if(endSoFar < currStart) { // Non overlapping
                
                // NOTE : i pe khare hoke, khud se pehle walo ka ans bna rahe ho,
                // So last wala bnda reh jaaega, so uska jugard if(i == n) me kiya hai upar
                list.add(endSoFar - startSoFar + 1); // form your ans
                
                startSoFar = currStart; // For next iteration
                endSoFar = currEnd; // For next iteration
            }
            else {  // endSoFar >= currStart   // overlapping, so merge in same
                endSoFar = Math.max(endSoFar, currEnd); // Merge hoke jo v bada endPoint rahega wo jeetega
            }
        }
        
        return list;
    }
    
}
