// https://leetcode.com/problems/partition-list/

class Solution {
    
    public ListNode partition(ListNode head, int x) {
        
        ListNode evenDummy = new ListNode(-1);  // Represents < x
        ListNode oddDummy = new ListNode(-1);   // Represents >= x
        
        ListNode ep = evenDummy;
        ListNode op = oddDummy;
        
        ListNode curr = head;
        while(curr != null) {
            if(curr.val < x) {  // Only change from even odd segregation
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
