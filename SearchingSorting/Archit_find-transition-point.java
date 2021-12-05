//https://practice.geeksforgeeks.org/problems/find-transition-point-1587115620/1#

//Intuition : If you find 1 keep on searching 1 in left search space, because you have to find 1st 1

class GfG {
    int transitionPoint(int arr[], int n) {
        int low = 0;
        int high = arr.length - 1;
        int potentialAnswer = -1;
	    
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid] == 1) {
                potentialAnswer = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return potentialAnswer;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
