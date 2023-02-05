// https://leetcode.com/problems/find-median-from-data-stream/

class MedianFinder {

    // Only DS is PQ jisme bina O(n) traversal k bina ye solve hoga
    
    PriorityQueue<Integer> max; // O(1) me bich ka median find krne k liye
    PriorityQueue<Integer> min; // O(1) me bich ka median find krne k liye
    
    public MedianFinder() {
        max = new PriorityQueue<>(Collections.reverseOrder()); // Size can be n/2 + 1
        min = new PriorityQueue<>(); // Size can be n/2
    }
    
    public void addNum(int num) {
        if((max.isEmpty()) || (!max.isEmpty() && num <= max.peek())) max.add(num);
        else min.add(num);
        
        // Shuffling to keep both heaps balanced, to find median in O(1)
        if(max.size() - min.size() >= 2) min.add(max.remove()); 
        if(min.size() - max.size() >= 1) max.add(min.remove());
    }
    
    public double findMedian() {
        if(max.size() == min.size()) return (max.peek() + min.peek()) / 2.0; // even
        else return max.peek(); // Odd
    }
    
}
