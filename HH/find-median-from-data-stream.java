// https://leetcode.com/problems/find-median-from-data-stream/

// Keeping maxHeap size as +1 
// maxHeap .... minHeap +1 (asc Order)
// smaller .... larger (asc Order)

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
        if(max.size() - min.size() >= 2) min.add(max.remove()); // if max +1 => OK, but +2 => Not OK, so balance out
        if(min.size() - max.size() >= 1) max.add(min.remove()); // Because max ko hamesha +1 rakhna h
    }
    
    public double findMedian() {
        if(max.size() == min.size()) return (max.peek() + min.peek()) / 2.0; // even
        else return max.peek(); // Odd
    }
    
}

-------------------------------------------------------------------------------------------------------------------------

// Keeping minHeap size as +1 
// maxHeap .... minHeap +1 (asc Order)
// smaller .... larger (asc Order)

class MedianFinder {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(minHeap.isEmpty() || num >= minHeap.peek()) {
            minHeap.offer(num);
            if(minHeap.size() > maxHeap.size() + 1) { // balance
                maxHeap.offer(minHeap.poll());
            }
        }
        else {
            maxHeap.offer(num);
            if(maxHeap.size() > minHeap.size()) { // balance
                minHeap.offer(maxHeap.poll());
            }
        }
    }
    
    public double findMedian() {
        if(minHeap.size() > 0 && maxHeap.size() > 0 && minHeap.size() == maxHeap.size()){
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        else if(minHeap.size() > 0) {
            return minHeap.peek();
        }
        else {
            return 0.0;
        }
    }
}
