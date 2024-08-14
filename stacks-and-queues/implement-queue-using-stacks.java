// https://leetcode.com/problems/implement-queue-using-stacks/

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
