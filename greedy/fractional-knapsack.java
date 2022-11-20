// https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1

// Latest

class Solution {
    class Pair {
        int v;
        int w;
        double r;
        Pair() {}
    }
    
    double fractionalKnapsack(int W, Item arr[], int n) {
        Pair[] info = new Pair[n];
        for(int i = 0; i < n; i++) {
            Pair p = new Pair();
            p.v = arr[i].value;
            p.w = arr[i].weight;
            p.r = arr[i].value / (arr[i].weight * 1.0);
            info[i] = p;
        }
        
        // Arrays.sort(info, (a, b) -> {
        //     if(b.r > a.r) return 1;
        //     else if(b.r < a.r) return -1;
        //     else return 0;
        // }); // Sorted based on ratio in desc Order
        
        Arrays.sort(info, (a, b) -> { // You want reverse Order 
            if(b.r - a.r > 0) return 1; // b - a > 0 ho tab 1 bhejo
            else if(b.r - a.r < 0) return -1;
            else return 0;
        });  // Sorted based on ratio in desc Order
        
        int i = 0;
        double profit = 0.0;
        while(i < n && W > 0) {
            if(info[i].w <= W) {
                profit += info[i].v;
                W -= info[i].w;
            }
            else {
                profit += W * info[i].r;
                W -= W;
            }
            i++;
        }
        return profit;        
    }
}

//-----------
// Same Logic

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
