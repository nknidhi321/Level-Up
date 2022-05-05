// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/max-non-overlapping-bridges-official/ojquestion

// Agar dono ko sort kar do and longest non overlapping length find kar lo toh baat bn jaaegi
// Sort on North, and south pe LIS lga do [LIS ka toh kaam he sorted order me LIS find karna]
// Make sure when you sort on north, keep it's corresponding south and then find LIS on south   

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] pairs = new int[n][2];
        for(int i = 0; i < n; i++) {
            pairs[i][0] = sc.nextInt();
            pairs[i][1] = sc.nextInt();
        }
        System.out.println(numberOfOverlappingBridges(pairs));
    }
    
    
    //=============================================================================================
    public static int numberOfOverlappingBridges(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1]; // If both are equal, then sort on smaller south
            else return a[0] - b[0]; // Sort in asc order on north
        });
        
        List<Integer> list = new ArrayList<>(); 
        for(int i = 0; i < n; i++) {
            int[][] pair = pairs;
            int ele = pair[i][1]; // LIS on south
            int insertPos = lastInsertPos(0, list.size() - 1, ele, list);
            if(insertPos == list.size()) list.add(ele);
            else list.set(insertPos, ele);
        }
        return list.size();
    }
    
    // List will contain duplicate ele. Why ?? Because it is valid as per question
    // Everytime a duplicate element comes as tar, it gets added to the valid very next Idx 
    public static int lastInsertPos(int si, int ei, int tar, List<Integer> list) {
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(list.get(mid) <= tar) si = mid + 1;
            else ei = mid - 1;
        }
        int insertPos = si;
        return insertPos;
    }
    //===============================================================================================
    
}
