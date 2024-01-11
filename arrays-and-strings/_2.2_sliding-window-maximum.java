// https://leetcode.com/problems/sliding-window-maximum/

// Using Deque 
// TC : O(n)
class Solution {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int si = 0, ei = 0;
        int[] ans = new int[n - k + 1];
        
        // queue k left se cal ans, right se add elements
        Deque<Integer> dequeue = new ArrayDeque<Integer>(); 
        
        while(ei < n) {
            // Tumse chote elements, whoever is at your left, kvi v kisi window ka max nahi bn sakte
            // because now yoyu are the max, so remove every element before you whoever is smaller than you.
            while(dequeue.size() > 0 && nums[dequeue.getLast()] < nums[ei]) {
                dequeue.removeLast();
            }
            dequeue.addLast(ei); // Note adding index
            if(ei - si + 1 == k) { // calculate your answer of window
                ans[ei - k + 1] = nums[dequeue.getFirst()]; // leftmost(first) guy in queue is your answer
                if(dequeue.getFirst() <= si) { // resize your window
                    dequeue.removeFirst(); // remove si which should not be in your window
                }
                si++;
            } 
            ei++;
        }
        return ans;
    }
    
}

--------

// TLE 
// Using PQ(maxHeap)

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int si = 0, ei = 0;
        int[] ans = new int[n - k + 1];
        
        Queue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()); 
        
        while(ei < n) {
            pq.add(nums[ei]);
            if(ei - si + 1 == k) {
                int maxInWindow = pq.peek();
                ans[ei - k + 1] = maxInWindow;
                pq.remove(nums[si]);
                si++;
            } 
           // System.out.println(pq);
            ei++;
        }
        return ans;
    }
}

----------

// TLE
// Using NGTR

class Solution {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ngtr = NGTR(nums);

        int n = nums.length;        
        int[] ans = new int[n- k + 1];

        for(int si = 0; si <= n - k; si++) {
            int jump = si;
            while(ngtr[jump] < si + k) {
                jump = ngtr[jump];
            }
            ans[si] = nums[jump];
        } 
        return ans;
    }
    
    public static int[] NGTR(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        stack.push((int)1e9);
        int i = n - 1;
        while(i >= 0) {
            while(stack.peek() != (int)1e9 && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            if(stack.peek() == (int)1e9) {
                ans[i] = arr.length;
            }
            else {
                ans[i] = stack.peek();
            }
            
            stack.push(i);
            i--;
        }
        return ans;
    }
    
}

---------

// TLE
// BruteForce
// Loop(n) * Loop(k) and find max in k

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] arr = new int[nums.length-k+1];
        for(int i=0; i<=nums.length-k; i++){
            arr[i] = max(nums, i, i+k-1);
        }
        return arr;
    }
    public static int max(int[] nums, int i, int j){
        int max = Integer.MIN_VALUE;
        while(i<=j){
            if(nums[i] >= max)
                max = nums[i];
            i++;
        }
        return max;
    }
}
