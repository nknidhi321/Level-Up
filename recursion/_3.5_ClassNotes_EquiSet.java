/*
 
Problem Statement: 
------------------
Form 2 sets with the following properties :-

-> Summation of Set1 = Summation of Set2
-> Set1 union Set2 = arr
-> Set1 intesection Set2 = phy or empty

NOTE: Utilize all elements

*/

package recursion;

public class EquiSet {

	public static int countEquiSet(int idx, int sum1, int sum2, int[] arr, String set1, String set2) {
		if(idx == arr.length) {
			if(sum1 == sum2) {
				System.out.println(set1 + " = " + set2);
				return 1;
			}
			return 0;
		}
		
		int count = 0;
		count += countEquiSet(idx + 1, sum1 + arr[idx], sum2, arr, set1 + arr[idx] + " ", set2);
		count += countEquiSet(idx + 1, sum1, sum2 + arr[idx], arr, set1, set2 + arr[idx] + " ");
		return count;
	}
	
	
	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 50, 60, 70, 80};
		
		System.out.println("All permutation set :- ");
		System.out.println(countEquiSet(0, 0, 0, arr, "", ""));
		
		
		//To obtain unique set fix one element of the array in any one of the particular set
		//i.e do not provide option for any one element to chose any of the set by itself
		//So, here fixing 10 in the 1st set
		System.out.println("\nUnique set :- ");
		System.out.println(countEquiSet(1, arr[0], 0, arr, arr[0] + " ", ""));

	}

}
