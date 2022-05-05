// https://leetcode.com/problems/russian-doll-envelopes/

/*
    Approach
    --------
    1) Sort the array, ascend on width and descend on height if width are same.
    2) Find the longest increasing subsequence based on height.

    NOTE : width/height dono strictly increasing/decreasing hona chahiye, Solving using strictly increasing 
    ----
    Since the width is increasing, we only need to consider height.
    [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise
    it will be counted as an increasing number if the order is [3, 3], [3, 4]

    Dry run on these TC :-
    -------------------
    [[5,4],[6,4],[6,7],[2,3]]  op : 3
    [[4,5],[4,6],[6,7],[2,3],[1,1]] op : 4
*/

```
class Solution {
    
    public int maxEnvelopes(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> { // Aise krne se width strictly increasing ka sure ho jaaega
            if(a[0] == b[0]) return b[1] - a[1]; // If both are equal, then sort on desc south/height
            else return a[0] - b[0]; // Sort in asc order on north/width
        });
        
        List<Integer> list = new ArrayList<>();
        int lastProcessedEle = -1;
        for(int i = 0; i < n; i++) {
            int[][] pair = pairs;
            int ele = pair[i][1]; // LIS on south/height
            int insertPos = lastInsertPos(0, list.size() - 1, ele, list);
            if(insertPos == list.size()) list.add(ele);
            else list.set(insertPos, ele);
        }
        return list.size();
    }
    
    // Aur yaha se height strictly increaing ho jaaega, kuki duplicates escape kar de rahe
    // List will not contain duplicate ele. Why ?? Because it is invalid as per question
    // Everytime a duplicate element comes as tar, it overrides on the last valid pos 
    public static int lastInsertPos(int si, int ei, int tar, List<Integer> list) {
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(list.get(mid) <= tar) si = mid + 1;
            else ei = mid - 1;
        }
        int insertPos = si;
        int dulicatePos = si - 1;
        return dulicatePos >= 0 && list.get(dulicatePos) == tar ? dulicatePos : insertPos;
    }

}
```
------------------------------------------------------------------------------------------------------------------------
