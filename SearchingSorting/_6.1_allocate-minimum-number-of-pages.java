//https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1#
//Binary Search on Answer

class Solution {
    //Function to find minimum number of pages.
    public static int findPages(int[]pages ,int n, int students) {
        if(students > n) return -1;
        
        // Min fixedPage max of pages hoga he kuki kisi din us max pages ko toh padhna he padega kisi ko,
        // So set your low at max of pages and max fixedPage can be sum of all pages so set high at sum of pages
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
        int currentPagesSum = 0;
        int actualStudents = 1; // set actualStudents at 1, kuki 1 element toh hoga he array me
        for(int i = 0; i < pages.length; i++) {
            if(currentPagesSum + pages[i] <= fixedPage) {
                currentPagesSum += pages[i];
            }
            else { // Agar currentPagesSum exceed ho gaya fixedPage se toh actualStudents ko +1 kar do
                currentPagesSum = pages[i];
                actualStudents++;
            }
            
            if(actualStudents > students) return false; //Jitne students tum soch kr aaye the, us se zyada students lg gaye us set kiye huye fixedPage pe
        }
        return true;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
