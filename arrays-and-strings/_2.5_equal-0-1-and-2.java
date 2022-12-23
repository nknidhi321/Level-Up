// https://practice.geeksforgeeks.org/problems/equal-0-1-and-23208/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

class Solution  { 
    
    long getSubstringWithEqual012(String str) { 
        int n = str.length();
        int PS0 = 0, PS1 = 0, PS2 = 0;
        Map<String, Integer> map = new HashMap<>();
        int diff10 = PS1 - PS0;
        int diff21 = PS2 - PS1;
        String hash = (diff10) + "#" + (diff21); 
        map.put(hash, 1);
        
        long ans = 0;
        for(int i = 0; i < n; i++) {
            if(str.charAt(i) == '0') PS0 += 1;
            else if(str.charAt(i) == '1') PS1 += 1;
            else if(str.charAt(i) == '2') PS2 += 1;
            
            diff10 = PS1 - PS0;
            diff21 = PS2 - PS1;
            hash = (diff10) + "#" + (diff21);
            if(map.containsKey(hash)) {
                ans += map.get(hash);
            }
            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }
        return ans;
    }
    
} 
