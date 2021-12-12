https://leetcode.com/problems/split-array-largest-sum/
// Same as allocate-minimum-number-of-pages

class Solution {
    
    public int splitArray(int[] pages, int students) {
        
        int n = pages.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, pages[i]);
            sum += pages[i];
        }
        
        int low = max;
        int high = sum;
        int probableAns = -1;
        
        // Pages pe binary search lagega.
        // Mid == fixedPage, Question is fixedPage = mid rakh k kya aap x students me apna kaam kar loge ? 
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            
            // Agar students kam ya equal hai toh => fixedPage ghata do taaki zyada students lage => move towards left
            if(isPossible(mid, students, pages)) {
                high = mid - 1;
                probableAns = mid;
            }
        
            // Agar students zyada lg raha hai toh => fixedPage badha do taaki students kam lage => move towards right
            else {
                low = mid + 1;
            }
        }
        return probableAns;
    }
    
    public static boolean isPossible(int fixedPage, int students, int[] pages) {
        int sum = 0;
        int actualStudents = 1; // set actualStudents at 1, kuki 1 element toh hoga he array me
        for(int i = 0; i < pages.length; i++) {
            if(sum + pages[i] <= fixedPage) {
                sum += pages[i];
            }
            else { // Agar sum exceed ho gaya fixedPage se toh actualStudents ko +1 kar do
                sum = pages[i];
                actualStudents++;
            }
            
            if(actualStudents > students) return false; //Jitne students tum soch kr aaye the, us se zyada students lg gaye us set kiye huye fixedPage pe
        }
        return true;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
