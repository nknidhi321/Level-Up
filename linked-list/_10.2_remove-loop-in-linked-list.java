// https://practice.geeksforgeeks.org/problems/remove-loop-in-linked-list/1/#
// There is an edge case when cycle starts at the head of the LL

class Solution {
    
    public static void removeLoop(Node head) {
        detectCycle(head);
    }
    
    public static void detectCycle(Node head) {
        if(head == null || head.next == null) return;
        
        // Catch up the pt. where slow and fast meets for the very first time
        Node meet = null;
        Node slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                meet = slow;
                break;
            }
        }
        if(meet == null) return;  // No cycle found
        
        // Now keep a pointer at head and another ptr at, where slow and fast ptrs. met 
        // Now move both at speed of 1, they will meet again where the cycle starts. 
        Node temp1 = head, temp2 = meet;
        if(temp1 == temp2) { // If there is a cycle at the very 1st node(At head)
            while(temp2.next != temp1) { // temp2 ko badhate jaao jab tak temp2 ka next temp1 k equal na aa jaaye
                temp2 = temp2.next;
            }
            temp2.next = null;
        }
        else { // Cycle at other nodes
            Node prev = temp2;
            while(temp1 != temp2) {
                prev = temp2;
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            prev.next = null;
        }
    }
    
}
