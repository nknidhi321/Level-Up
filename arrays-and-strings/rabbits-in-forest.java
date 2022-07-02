// https://leetcode.com/problems/rabbits-in-forest/

// With comments

```
class Solution {
    
    public int numRabbits(int[] arr) {
        int n = arr.length;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            
            // Jab "blue" color ka pehla rabbit bolra hai
            // Mere jaise 5 aur hai, toh intotal 6 rabbit honge "blue" color k 
            // So, pehle blue wale rabbit ne he sbke naam k count ko ans me add krwa diya arr[i]
            // + 1 khud k naam ka
            if(!map.containsKey(arr[i])) ans += arr[i] + 1; 
            
            // So, jab dobara "blue" milega tab wo jitna count bolra hai
            // usko add krwane ki koi zaroorat nai h, PROVIDED SOME CONDITION
            
            // Jab "blue" color k rabbit ka count 6 ho jaaye
            // Usko nikal do map se
            // Kuki yaha "blue" jaisa kuch mentioned nahi hai to identify
            // Ex : [3, 3, 3, 3, 3] Iska ans 8 hai na ki 4, 
            // Agar 3 ko fixed "blue" consider karoge tab 4 hoga,
            // But 3 ko fixed "blue" nahi keh sakte
            // Kuki sab keh rahe h mere jaise 3 aur hai, 
            // par yaha uske jaise 4 milenge agar 3 ko fixed blue kah denge toh
            // So, last wala 3 "blue" color ka nahi ho sakta, assign it some other color
            // Mtlb agar pehle he, jab 3 + 1 ka count 4 mil jaaye then usko map se nikal do
            // Taaki firse 4th wale idx ko uniquely 3 "blue" se identify kar paaye.
            // Kuki ans tvi bnate hai, jab 3 or "blue" color ka pehle bnda milta hai
            // 0th idx wala "blue" color ans bnaega and 4th idx wala "!blue" ans bnaega
            // So, agar aur "blue" jaise mil rahe h toh wo actually "blue" color ka nahi hoga  
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if(map.get(arr[i]) == arr[i] + 1) map.remove(arr[i]); // Here +1 is khud k naam ka
        }
        
        return ans;
    }
    
}
```
---------------------

// Without comments

```
class Solution {
    
    public int numRabbits(int[] arr) {
        int n = arr.length;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            if(!map.containsKey(arr[i])) ans += arr[i] + 1;
            
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if(map.get(arr[i]) == arr[i] + 1) map.remove(arr[i]);
        }
        
        return ans;
    }
    
}
```

------------------------------------------------------------------------------------------------------------
