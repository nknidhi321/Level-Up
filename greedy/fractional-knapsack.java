// https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1

class Solution {

    double fractionalKnapsack(int W, Item arr[], int n) {
        double[][] items = new double[n][3]; 
        for(int i = 0; i < n; i++) {
            double ratio = arr[i].value / (arr[i].weight * 1.0);
            items[i][0] = arr[i].value;
            items[i][1] = arr[i].weight;
            items[i][2] = ratio; // value by weight ratio
        }
        
        Arrays.sort(items,(a, b) -> { // sorting in desc order
            if(a[2] > b[2]) return -1; // desc order me krna h, a bada h so -1 bhejo
            else if(a[2] < b[2]) return 1;
            return 0;
        });
        
        int i = 0;
        double profit = 0d;
        while(i < n && W > 0) {
            int weight = (int)items[i][1];
            double ratio = items[i][2];
            if(W - weight >= 0) {
                profit += ratio * weight; 
                W -= weight; 
            }
            else {
                profit += ratio * W;
                W -= W; 
            }
            i++;
        }
        return profit;
    }
    
}
