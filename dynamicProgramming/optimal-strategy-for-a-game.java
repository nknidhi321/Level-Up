// https://practice.geeksforgeeks.org/problems/optimal-strategy-for-a-game-1587115620/1

class solve {

    public static long countMaximum(int arr[], int n) {
        int[][] dp = new int[n][n]; // dp me "tumhare optimal" moves ka ans hoga  
        return tab(n, arr, dp);
    }

    public static int tab(int N, int[] arr, int[][] dp) {
        for(int gap = 0; gap < N; gap++) {
            for(int i = 0, j = gap; j < N; i++, j++) {
                // NOTE : Sbse pehle tumhari chal hai
                
                if(gap == 0) { // Sbse pehle tumhari chal hai, and 1 size ki arr hai
                    dp[i][j] = arr[i]; // So, jo v hai utha lo
                }
                else if(gap == 1) { // Sbse pehle tumhari chal hai, and 2 size ki arr hai
                    dp[i][j] = Math.max(arr[i], arr[j]); // So, jo v max hai wo uthaoge
                }
                else { 
                    // [i, j] tak ki array me ya toh tum arr[i] utha loge, ya toh arr[j] utha loge
                    
                    // Agar tumne i pick kiya and :-
                    int min1 = dp[i + 2][j]; // Opponent ne i + 1 ko pick kiya, toh remaining dp[i + 2][j] se tumhara ans bnega
                    int min2 = dp[i + 1][j - 1]; // Opponent ne j ko pick kiya, toh remaining dp[i + 1][j - 1] se tumhara ans bnega
                    int max1 = arr[i] + Math.min(min1, min2); // Opponent v optimally khelega, islye min lena par raha
                    
                    // Agar tumne j pick kiya and :-
                    int min3 = dp[i][j - 2]; // Opponent ne j - 1 ko pick kiya, toh remaining dp[i][j - 2] se tumhara ans bnega
                    int min4 = dp[i + 1][j - 1]; // Opponent ne i ko pick kiya, toh remaining dp[i + 1][j - 1] se tumhara ans bnega
                    int max2 = arr[j] + Math.min(min3, min4); // Opponent v optimally khelega, islye min lena par raha
                    
                    // So, ab dono me se jo v max hoga wo he tum pick karoge
                    dp[i][j] = Math.max(max1, max2); 
                }
            }
        }
        return dp[0][N - 1];
    }
    
}
