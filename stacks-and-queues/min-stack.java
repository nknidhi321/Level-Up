// https://leetcode.com/problems/min-stack/

Encode decode mathod is the best approch for this question
Not implemented here as of now, check YT

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

**Using 1 Stack, but Pair Class**
Pair class will have val and minSoFar, 
For minSoFar check stacks top minsf and curr val, and update your minsf accordingly.
  
```
class MinStack {
    
    public static class Pair {
        int val;
        int minsf;
        
        public Pair(int val, int minsf) {
            this.val = val;
            this.minsf = minsf;
        }
    }
    
    Stack<Pair> stack;
    
    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int val) {
        if(!stack.isEmpty() && stack.peek().minsf < val) stack.push(new Pair(val, stack.peek().minsf));
        else stack.push(new Pair(val, val));
    }
    
    public void pop() {
        if(!stack.isEmpty()) stack.pop();
    }
    
    public int top() {
        return stack.peek().val;
    }
    
    public int getMin() {
        return stack.peek().minsf;
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

**Using 2 stacks minStack and normal Stack :-**

minStack me tvi push hoga jab minStack k top se v choti val koi aa jaaye. 
  
NOTE: Do not use == to check value of stack and minStack because Stack<Integer> is of Integer type and not int type, 
hence == will check for if both minStack and stack top is pointing to the same address or not, 
but you have to check if both the val of minStack top and stack top are equal or not.

So instead of == we can use Integer.equals() method of Integer class or intValue() to check for equal value.
See the same in pop method in the below 2 programs.

Example How to use: 
Integer obj = new Integer(75);          
int value = obj.intValue();
boolean b = obj.equals(value);

Using Integer.equals() method
Checks if the content of the object is equal or not
Syntax : obj.equals(value);

```
class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> minStack;
    Stack<Integer> stack;
    
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int val) {
        if(minStack.isEmpty() || val <= minStack.peek())  {
            minStack.push(val);
        }
        stack.push(val);
    }
    
    public void pop() {
        if(!minStack.isEmpty() && stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
```

--------------------------------------------------------------------------------

//Using intValue() method, returns int type value
Syntax: obj.intValue()        //obj is Integer class object 
Example: 
Integer obj = new Integer(75);
int value = obj.intValue();

```
class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> minStack;
    Stack<Integer> stack;
    
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int val) {
        if(minStack.isEmpty() || val <= minStack.peek())  {
            minStack.push(val);
        }
        stack.push(val);
    }
    
    public void pop() {
        if(!minStack.isEmpty() && stack.peek().intValue() == minStack.peek().intValue()) {
            minStack.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
