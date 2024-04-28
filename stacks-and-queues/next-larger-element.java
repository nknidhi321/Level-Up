// https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article

class Solution {
    public static long[] nextLargerElement(long[] arr, int n) { 
        long[] ans = new long[n]; 
        Stack<Integer> st = new Stack<>(); // Storing indexes
        for(int i = n - 1; i >= 0; i--) {
            while(!st.isEmpty() && arr[st.peek()] <= arr[i]) st.pop();
            ans[i] = st.isEmpty() ? -1 : arr[st.peek()];
            st.add(i);
        }
        return ans;
    } 
}
