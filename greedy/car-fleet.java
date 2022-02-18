// https://leetcode.com/problems/car-fleet/

class Solution {
    
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] carData = new double[n][2]; // {position, time}
        
        // Creating carData with position and time 
        for(int i = 0; i < n; i++) {
            carData[i][0] = position[i] * 1.0;
            carData[i][1] = ((target - position[i]) * 1.0) / speed[i];
        }
        
        Arrays.sort(carData, (a, b) -> Double.compare(a[0], b[0])); // sort on position basis
        
         // Agar humse kam ya equal time le raha hai mere se piche wala car toh same fleet me aa jaaega,
        // So cheack for those cars whose time is greater than the curr time for separate car fleets
        
        int fleets = 0;
        double prevTime = -1;
        for(int i = n - 1; i >= 0; i--) { // Iterate from end
            if(carData[i][1] > prevTime) { 
                fleets++;
                prevTime = carData[i][1];
            }
        }
        return fleets;
    }
}
