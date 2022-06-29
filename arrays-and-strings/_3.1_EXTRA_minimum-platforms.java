// https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1/#

class Solution {

    public static int findPlatform(int arr[], int dep[], int n) {
        // Kisi v moment pe max. kitney number of platforms available hai, that is answer
        // So, trains ki arrival and departure time saath me leke chalne ka koi sense nai bnta hai

        // Koi v train depart huyi then new train lga do, else nayi platform leke aao 
        Arrays.sort(arr); // Sort both of them individually
        Arrays.sort(dep); // Sort both of them individually
        
        int platform = 1; // For the 0th idx train
        
        for(int i = 1, j = 0; i < n; i++) { // starting from 1st idx
            if(dep[j] >= arr[i]) platform++; // Agar departureTime zyada hai then you req. a new platform
            else j++; // Agar departureTime kam hai then next depatureTime pe point krwa do
        }
        return platform;
    }
    
}
