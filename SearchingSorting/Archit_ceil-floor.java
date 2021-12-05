//https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/ceil-floor-official/ojquestion

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
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
