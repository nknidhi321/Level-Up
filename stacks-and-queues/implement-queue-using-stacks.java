// https://leetcode.com/problems/implement-queue-using-stacks/
// https://www.youtube.com/watch?v=3Et9MrMc02A

// Optimized Approach
// NOTE : Input stack will serve as stack and output stack will serve as queue

// push() TC : O(1) : we push in input stack, pile up 
// and later dump to output stack when output stack will be empty, this will be done when pop() will be called
// Also maintain bottom most element of input stack for frontOrPeek

// pop() TC : O(1)-amortized, O(N)-worst case : we check in output stack which serves as queue because 
// these elements are the elements which was dumped from input stack in a go to the output stack,
// so bascially its reversed order and we want reversed order itself for output stack to act as a queue.
// So if elements exists in output stack return the top else dump all elements
// from input stack to output stack and return the top of output stack

// peek() TC : O(1) we can peek from the output stack else return frontOrPeek
// Because peek will always remain same for an actual queue until and unless we pop elements from queue

// empty() TC : O(1): An actual queue will become empty only when both input and output stack will be empty

class MyQueue {
    
    private Stack<Integer> input; // Serving as Stack
    private Stack<Integer> output; // Serving as Queue
    private int frontOrPeek;
    
    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
    
    public void push(int x) {
        if(input.empty()) frontOrPeek = x; 
        input.push(x); 
    }
    
    public int pop() {
        if(!output.empty()) {
            return output.pop();
        }
        else {
            while(!input.empty()) {
                output.push(input.pop());
            }
            return output.pop();
        }
    }
    
    public int peek() {
        if(!output.empty()) {
            return output.peek();
        }
        else {
            return frontOrPeek;
        }
    }
    
    public boolean empty() {
        if(input.isEmpty() && output.isEmpty()) return true;
        return false;
    }
    
}

//-------------------------------------------------------------------

// Naive Approach
// For PUSH O(N)
// Empty the original stack to the support
// Add the new value
// Now add all values from support stack to the original stack

class MyQueue {
    
    Stack<Integer> original;
    Stack<Integer> support;
        
    public MyQueue() {
        original = new Stack<>();
        support = new Stack<>();
    }
    
    public void push(int x) {
        while(!original.empty()) {
            support.push(original.pop());
        }
        original.push(x);
        while(!support.empty()) {
            original.push(support.pop());
        }
    }
    
    public int pop() {
        if(!empty()) return original.pop();
        return -1;
    }
    
    public int peek() {
        if(!empty()) return original.peek();
        return -1;
    }
    
    public boolean empty() {
        if(original.isEmpty()) return true;
        return false;
    }
    
}

// -----------------------------------------------
