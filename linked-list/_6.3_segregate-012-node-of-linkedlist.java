// https://practice.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1# 

class Solution {
    
    static Node segregate(Node head) {
        
        Node dummy0 = new Node(-1);
        Node dummy1 = new Node(-1);
        Node dummy2 = new Node(-1);
        
        Node p0 = dummy0;
        Node p1 = dummy1;
        Node p2 = dummy2;
        
        Node curr = head;
        while(curr != null) {
            if(curr.data == 0) {
                p0.next = curr;
                p0 = p0.next;
            }
            else if (curr.data == 1) {
                p1.next = curr;
                p1 = p1.next;
            }
            else {
                p2.next = curr;
                p2 = p2.next;
            }
            curr = curr.next;
        }
        
        p2.next = null; // Last LL node, make sure to make it null, otherwise it will form a cycle
        
        // Jab ek v 1 nahi mila, then p0 k last node ko p2 k head se jor do
        if(dummy1.next == null) p0.next = dummy2.next;
        else {
            p0.next = dummy1.next;  // Saare 0 k baad saare 1 should come as per question
            p1.next = dummy2.next;  // Saare 1 k baad saare 2 should come as per question
        }
        return dummy0.next;
    }
}
