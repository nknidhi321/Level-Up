// https://leetcode.com/problems/container-with-most-water/

/*
    Intuition :-
    ---------
    Objective : Area maximize karna.
    Wo kab hoga ?
    Jab hum width and height ko as zyada se zyada rakh paaye.

    So, width ko zyada se zyada kaise rakha ja sakat hai ?
    Jab hum start aur end se shuru kare tab width maximum hoga.

    Now, height ko maximum kaise rakh saktey hai ?
    Koi area kiske wazah se kam aari ho sakti hai ?? 
    Width toh humne max rakh he liya hai. 
    So, us choti height k wali pillar k wazah se hamari overall area choti ho ja rahi hai.

    Consider agar hum us badi wali pillar ko le paate har bar, toh hamara area badhne ki zyada probability hoti.
    So, currArea jo v aaya usko probable answer mante huye choti wali pillar ko khiska denge aage, is umeed me ki kya pta koi bada pillar mil jaaye humse aage,
    taaki us naye bade wale pillar k saath aur apne purane fixed pillar k saath hum uski kahani kar paaye.
    
    NOTE :
    -----
    Jab chote wale pillar k pointer ko khiskate hai uska mtlb hai ki us chote wale pillar k pointer ko use kar k ab us se best area nahi bn sakta, islye usko khiska do.
    Still, agar mai chote wale ptr se baaki ko map karu, toh hum dekhenge ki Jo uska best area tha us se chota area zaroor bna dega
    par us area se best nahi bna paaega kvi, Islye it is sensless to keep that chota height wala ptr

    Approach :-
    -------
    The idea is to keep 2 pointers, one at first and the other one at last.
    Calculate minHeight of ith and jth height(Water will overflow from the minHeight side, hence taking minimum of the two).
    Calculate width, Find currArea, and keep updating the maxArea at each iteration if needed.
    Now, 
    Increment start if height[start] < height[end]
    Decrement end is height[end] < height[start]
    If both heights are equal increment start or decrement end (both are valid, because minHeight will be same for either of the start or end height).
    Terminate when start == end (Atleast 2 container chahiye to store water)
*/

// Two pointer
// TC : O(N)
```
class Solution {
    
    public int maxArea(int[] height) {
        int n = height.length, start = 0, end = n - 1;
        int maxArea = 0;
        
        while(start < end) {
            int width = end - start;
            int minHeight = Math.min(height[start], height[end]);
            maxArea = Math.max(maxArea, (minHeight * width));
            
            if(height[start] < height[end]) start++;
            else end--;
        }
        return maxArea;
    }
}
```
/*
    Observation :-
    --------
    Watch CodeBix YT video for diagram.
    **If you think of decrementing and incrementing the other way around then**
    Suppose, i = 20 and j = 9, and say width is 10, the next maxArea, with the jth height will always be less than or equal to the currArea 
    because here minHeight is 9 and width will always decrease at the next iteration.
    ->if you get less height at j-- , then that's the worst scenario, because now the minHeight will be the j-- height, hence less area.
    ->if greter than 9, then that is also of no use because minHeight would still be 9)and width would always be less than 10 (current Width) 
    hence less area than the currArea, hence the area with the jth height 9 is the maximum it can make).
    And we have already calulated currArea for that ith and jth index, and maxArea gets updated accordingly. 
*/

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Brute force
//TC : O(N^2)
```
class Solution {
    
    public int maxArea(int[] height) {
        
        int n = height.length, area = 0;
        for(int i = 0; i <= n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                int width = j - i;
                int minHeight = Math.min(height[i], height[j]);
                area = Math.max(area, (minHeight * width));
            }
        }
        return area;
    }
}
```
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
