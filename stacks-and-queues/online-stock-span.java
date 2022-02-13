// https://leetcode.com/problems/online-stock-span/

class StockSpanner {
    
    public  int day;
    public  Stack<int[]> stack; // {day, price}
    
    public StockSpanner() {
        day = 0;
        stack = new Stack<>();
    }
    
    public int next(int price) {
        while(!stack.isEmpty() && stack.peek()[1] <= price) stack.pop();
        int span = 0;
        if(!stack.isEmpty()) span = day - stack.peek()[0] + 1;
        else span = day - (-1);
        stack.push(new int[] {++day, price});
        return span;
    }
}
