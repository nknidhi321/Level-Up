// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/hashmap-and-heaps/pairs-with-given-sum-in-two-sorted-matrices-official/ojquestion

/*
    If we use HM, we are not taking advantage of sorted matrix => A better solution exists since given mat are sorted
    Duplicate elements are given so you need to store freq of element
    Ex : mat1 = [1, 1, 1, 7]     matr2 = [19]    target = 20     // Here 19 will make pair with all 1's of mat1, so count = 3 (ans)  [Here 1 is the duplicate element]


    Approach :-
    --------
      Pehle matrix k saare elements ko HM me daal do, 
      Now iterate over 2nd matrix and find for it's comp, if comp is found and keep on adding the freq in your answer
      
      NOTE : Don't add youself in HM while iterating on mat2, because elements need to be from {mat1, mat2} 

    TC: O(N^2)
    SC :O(N^2)
*/

import java.util.*;

public class Main {
	

  //=====================================================================
  public static int solve(int[][] num1, int[][] num2, int target) {
		
		// {Element, Freq}
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < num1.length; i++) {
		    for(int j = 0; j < num1[0].length; j++) {
		        map.put(num1[i][j], map.getOrDefault(num1[i][j], 0) + 1);
		    }
		}
		
		int count = 0;
		for(int i = 0; i < num2.length; i++) {
		    for(int j = 0; j < num2[0].length; j++) {
    		    int comp = target - num2[i][j];
    		    if(map.containsKey(comp)) {
    		        count += map.get(comp);
    		    }
		    }
		}
		return count;
	}
  //============================================================================


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] mat1 = new int[N][N];
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat1[0].length; j++) {
				mat1[i][j] = sc.nextInt();
			}
		}

		int[][] mat2 = new int[N][N];
		for (int i = 0; i < mat2.length; i++) {
			for (int j = 0; j < mat2[0].length; j++) {
				mat2[i][j] = sc.nextInt();
			}
		}
		int K = sc.nextInt();
		System.out.println(solve(mat1, mat2, K));

	}

}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
