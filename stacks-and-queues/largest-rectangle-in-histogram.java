// https://leetcode.com/problems/largest-rectangle-in-histogram/

// Rajneesh Bhaiya
// Finding, NSOL and NSOR together, Using single stack
// TC : O(2N), at max stack me har ek ele 2 baar visit hoga, once while pushing next time while popping.

class Solution {
    
    int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1); // Stack me by deafult -1 daal lo kuki jiska NSOL nahi milega uska NSOL -1 hoga
        // NOTE : Stack me ab isEmpty() k check k bajay -1 ka check lgana.
        
        for(int i = 0; i < n; i++) {
            // For NSOR, apne se bado ya equal ko pop karwana hai, and mai jisko v pop karwaungi mai uski NSOR banungi 
            while (st.peek() != -1 && heights[st.peek()] >= heights[i]) { // heights[st.peek()] > heights[i] // both will work
                int eleIdx = st.peek(); // Jisko v pop karwao us idx ka maxArea bnwa lo
                st.pop(); // eleIdx ka NSOL just uske niche hoga stack me *

                int h = heights[eleIdx];
                int NSOR = i; int NSOL = st.peek(); // * just uske niche
                int w = NSOR - NSOL - 1; 
                maxArea = Math.max(maxArea, h * w);
            }
            st.push(i); // Push your idx, tumhara maxArea bn na baaki hai avi
        }

        // Ab jo v stack me bach gaye uska NSOR nahi mil paaya hai avi tak jo ki n hoga
        int NSOR = n;
        while (st.peek() != -1) {
            int eleIdx = st.peek(); // Jisko v pop karwao us idx ka maxArea bnwa lo
            st.pop(); // eleIdx ka NSOL just uske niche hoga stack me *

            int h = heights[eleIdx];
            int NSOL = st.peek(); // * just uske niche
            int w = NSOR - NSOL - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Rajneesh Bhaiya
// Finding, NSOL and NSOR individually, using 2 stacks

class Solution {
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] ls = nextSmallerToLeft(n, heights);
        int[] rs = nextSmallerToRight(n, heights);
        
        // Sbka nextSmallerToLeft and nextSmallerToRight, ls and rs array se nikalo and max calulate karte chalo   
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            int width =  rs[i] - ls[i] - 1; // Na nextSmallerToLeft ko lena hai, na nextSmallerToRight ko lena hai islye -1 v hoga
            max = Math.max(max, heights[i] * width); // Calulate area and then max
        }
        return max;
    }
    
    public static int[] nextSmallerToLeft(int n, int[] heights) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < n; i++) { // Left calculate karna hai toh left se iterate karo
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();  //Khudse bade ya equal ko pop karwao
            if(!stack.isEmpty()) ans[i] = stack.peek(); // Ab jo v hai wo, val se chota hoga
            else ans[i] = -1; // Agar kisi ka nextSmallerToLeft nahi mil raha toh wo -1 hoga
            stack.push(i); // Tum v kisi ka answer bn saktey ho
        }
        return ans;
    }
    
    public static int[] nextSmallerToRight(int n, int[] heights) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = n - 1; i >= 0; i--) { // Right calculate karna hai toh right se iterate karo
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop(); //Khudse bade ya equal ko pop karwao
            if(!stack.isEmpty()) ans[i] = stack.peek();  // Ab jo v hai wo, val se chota hoga
            else ans[i] = n; // Agar kisi ka nextSmallerToRight nahi mil raha toh wo n hoga
            stack.push(i); // Tum v kisi ka answer bn saktey ho
        }
        return ans;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
