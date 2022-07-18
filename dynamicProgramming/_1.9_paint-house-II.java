// https://www.lintcode.com/problem/516/
// Same question as paint house yaha bs 3 color k jagah c colors diye hai
// TC : O(NC^2) Har cell ko fill krne k liye khud k upar wale row pe c ka loop lga rahe
// min and secMin agar rakh le toh ye loop bacha sakte hai => TC : O(NC)

public class Solution {
    
    public int minCostII(int[][] arr) {
        if(arr == null || arr.length == 0) return 0;
        int houses = arr.length, colors = arr[0].length;
        
        // house 0 ko kisi v color se paint kar saktey ho
        // So, arr k first row me jo hai wahi rehne do 
        for(int house = 1; house < houses; house++) {
            for(int color = 0; color < colors; color++) {
                int min = (int)1e9;
                for(int k = 0; k < colors; k++) {
                    if(k == color) continue; // Adjacent house pe same color nai lga saktey
                    else min = Math.min(min, arr[house - 1][k]); // Khud se upar wale row me min find karo
                } 
                arr[house][color] = min + arr[house][color]; // yaha n ghr ko c color se paint karne ka cost h  
            }
        }

        // Last row se min nikalo that is your ans
        int min = Integer.MAX_VALUE;
        for(int color = 0; color < colors; color++) {
            min = Math.min(min, arr[houses - 1][color]);
        }
        return min;
    }

}
