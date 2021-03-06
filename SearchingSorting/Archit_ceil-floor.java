// https://practice.geeksforgeeks.org/problems/ceil-the-floor2802/1#

// If your target exists, both ceil && floor will be equal to that target,
// If your target do not exist, find ceil && floor of that target
// If your target is right most or left most element, Return -1 if floor or ceil is not present. 

class Solve {
    
    public Pair getFloorAndCeil(int[] arr, int n, int target) {
        
        Arrays.sort(arr);
        
        int potentialFloor = -1;
        int potentialCeil = -1;
        
        int low = 0;
        int high = arr.length - 1;
        
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            // When you are discarding left search space
            // the mid element can be your potentialFloor
            if(arr[mid] < target) { 
                potentialFloor = arr[mid];
                low = mid + 1;
            }
            
            // When you are discarding right search space
            // the mid element can be your potentialCeil
            else if(target < arr[mid]) {
                potentialCeil = arr[mid];
                high = mid - 1;
            }
            
            // If you found your target
            // That element is both your ceil and floor
            else {
                potentialFloor = arr[mid];
                potentialCeil = arr[mid];
                break;
            }
        }
        return new Pair(potentialFloor, potentialCeil);
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/ceil-floor-official/ojquestion
// Same code on pep

import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
    }
    int target = sc.nextInt();
    
    int potentialFloor = -1;
    int potentialCeil = -1;
    
    int low = 0;
    int high = arr.length - 1;
    
    while(low <= high) {
        
        int mid = low + (high - low) / 2;
        
        // When you are discarding left search space
        // the mid element can be your potentialFloor
        if(arr[mid] < target) { 
            potentialFloor = arr[mid];
            low = mid + 1;
        }
        
        // When you are discarding right search space
        // the mid element can be your potentialCeil
        else if(target < arr[mid]) {
            potentialCeil = arr[mid];
            high = mid - 1;
        }
        
        // If you found your target
        // That element is both your ceil and floor
        else {
            potentialFloor = arr[mid];
            potentialCeil = arr[mid];
            break;
        }
    }
    
    System.out.println(potentialCeil);
    System.out.println(potentialFloor);
 }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
