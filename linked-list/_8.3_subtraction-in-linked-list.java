// https://practice.geeksforgeeks.org/problems/subtraction-in-linked-list/1/#

class Solution {
    
    static Node subLinkedList(Node l1, Node l2) {
        
        // Remove leading zeros
        l1 = removeFrontZeros(l1);
        l2 = removeFrontZeros(l2);
        
        // Check which list is large and which is small
        Node small = null, large = null;
        if(isBigger(l1,l2)) { // l1 is bigger
            large = reverse(l1);
            small = reverse(l2);
        }
        else { // l2 is bigger
            large = reverse(l2);
            small = reverse(l1);
        }

        // Now, subtract
        Node dummy = new Node(-1);
        Node prev = dummy;
        int borrow = 0;
        
        while(large != null) {
            int sub = borrow + large.data - (small != null ? small.data : 0);
            if(sub < 0) { // If your curr answer is < 0, then you need a borrow
                sub += 10; // Borrowed for your current answer, base is 10, so add 10 
                borrow = -1; // If you borrow for curr answer then set borrow to -1, for next iteration 
            }
            else borrow = 0; // If you do not borrow for curr answer then set borrow to 0, for next iteration 
            
            prev.next = new Node(sub);
            
            // For next iteration
            prev = prev.next;
            if(large != null) large = large.next;
            if(small != null) small = small.next;
        }
        
        
        // Subtracted answer will be in reverse order so reverse again
        Node nhead = reverse(dummy.next);
        
        
        //Now, remove front 0's if there's any 
        Node ans = removeFrontZeros(nhead);
        return ans != null ? ans : new Node(0); // If all nodes becomes 0, then return single 0  
    }
    
    
    // If l1 is bigger return true, if l2 is bigger return false
    // If both are of same length then check the head which is bigger value
    public static boolean isBigger(Node l1, Node l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);
        
        if(len1 > len2) return true;
        else if(len2 > len1) return false;

        // If both are of same length then check the head which is bigger value 
        Node c1 = l1;
        Node c2 = l2;
        while (c1 != null) {
            if (c1.data > c2.data) return true;
            else if (c2.data > c1.data) return false;
            
            // If both data is equal
            c1 = c1.next;
            c2 = c2.next;
        }
        return true; // Ex : l1 = 999  , l2 = 999  
    }
    
    
    // Length of LL
    public static int getLength(Node node) {
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }
    
    // Reverse all nodes
    public static Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        while(curr != null) {
            Node forw = curr.next;
            curr.next = prev;
            
            prev = curr;
            curr = forw;
        }
        return prev;
    }
    
    // Remove front zeros
    public static Node removeFrontZeros(Node head) {
        Node curr = head;
        while (curr != null && curr.data == 0) {
            Node forw = curr.next;
            curr.next = null;
            curr = forw;
        }
        return curr;
    }
    
}
