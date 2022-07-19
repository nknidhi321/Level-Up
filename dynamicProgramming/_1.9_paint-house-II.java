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

//=========================================================================================================================================
// Optimization TC : O(NC)
// Using 4 extra var for the curr and prev row

public class Solution {
    
    public int minCostII(int[][] arr) {
        if(arr == null || arr.length == 0) return 0;
        int houses = arr.length, colors = arr[0].length;
        
        // Cal min ans secMin for of 0th row
        int min = Integer.MAX_VALUE, secMin = Integer.MAX_VALUE;
        for(int color = 0; color < colors; color++) {
            if(arr[0][color] < min) {
                secMin = min;
                min = arr[0][color];  
            }
            else if(arr[0][color] >= min && arr[0][color] <= secMin) {
                secMin = arr[0][color];
            }
        }

        // house 0 ko kisi v color se paint kar saktey ho
        // So, arr k first row me jo hai wahi rehne do 
        for(int house = 1; house < houses; house++) {           
            int currMin = Integer.MAX_VALUE, currSecMin = Integer.MAX_VALUE;

            for(int color = 0; color < colors; color++) {
                // Forming ans for curr row
                if(arr[house - 1][color] == min) arr[house][color] = secMin + arr[house][color];
                else arr[house][color] = min + arr[house][color];

                // Calculating min secMin for next row
                // While forming the ans for the curr row
                if(arr[house][color] < currMin) {
                    currSecMin = currMin;
                    currMin = arr[house][color];  
                }
                else if(arr[house][color] >= currMin && arr[house][color] <= currSecMin) {
                    currSecMin = arr[house][color];
                }
            }
            min = currMin; secMin = currSecMin;
        }

        // Printing DP
        // for(int i = 0; i < arr.length; i++) {
        //     for(int j = 0; j < arr[0].length; j++) {
        //         System.out.print(arr[i][j] + "\t");
        //     }
        //     System.out.println();
        // }
        return min;
    }

}

//=========================================================================================================================================
