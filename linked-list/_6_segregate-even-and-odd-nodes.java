// https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1#
// Based on node value

class Solution{
    
    public Node divide(int N, Node head){
        
        Node evenDummy = new Node(-1);
        Node oddDummy = new Node(-1);
        
        Node ep = evenDummy;
        Node op = oddDummy;
        
        Node curr = head;
        while(curr != null) {
            if(curr.data % 2 == 0) {
                ep.next = curr;
                ep = ep.next;
            }
            else {
                op.next = curr;
                op = op.next;
            }
            curr = curr.next;
        }
        
        op.next = null; // Last LL node, make sure to make it null, otherwise it will form a cycle
        ep.next = oddDummy.next;  // Saare evenNodes k baad saare oddNodes should come as per question
        return evenDummy.next;
    }
}

-------------------------------------------------------------------------------------------------------------------------------------------
  
// https://leetcode.com/problems/odd-even-linked-list/
// Based on node idx

class Solution {
    
    // Always perform 0 1 size LL check,
    // Here, also perform even odd check. 
    public ListNode oddEvenList(ListNode head) {
        
        ListNode evenDummy = new ListNode(-1);
        ListNode oddDummy = new ListNode(-1);
        
        ListNode ep = evenDummy;
        ListNode op = oddDummy;
        
        ListNode curr = head;
        int i = 0;
        while(curr != null) {
            if(i % 2 == 0) {
                ep.next = curr;
                ep = ep.next;
            }
            else {
                op.next = curr;
                op = op.next;
            }
            curr = curr.next;
            i++;
        }
        
        op.next = null; // Last LL node, make sure to make it null, otherwise it will form a cycle
        ep.next = oddDummy.next;  // Saare evenIdx k baad saare oddIdx should come as per question
        return evenDummy.next;
    }
}

-------------------------------------------------------------------------------------------------------------------------------------------
