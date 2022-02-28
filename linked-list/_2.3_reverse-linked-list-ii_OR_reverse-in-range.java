// https://leetcode.com/problems/reverse-linked-list-ii/
// https://nados.io/question/reverse-in-range?zen=true

class Solution {
    
    // Make sure to make th & tt global else addFirstInTempList will have to return both th and tt, since it is call by value
    // So when addFirstInTempList function returns th and tt in main will still be pointing to where it was poiting.   
    public ListNode th = null;  // th = tempHead
    public ListNode tt = null;  // tt = tempTail
    
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null) return head;
        
        ListNode curr = head;
        ListNode prev = null;
        int idx = 1;
        
        while(idx <= right) { 
            if(idx == left - 1) prev = curr; // Stand just before left
            
            if(idx >= left && idx <= right) { // Do addFirst for [left, right] nodes to reverse in tempList 
                while(idx >= left && idx <= right) {
                    ListNode forward = curr.next; // Backup
                    curr.next = null; // Break link with next node
                    addFirstInTempList(curr); // By addFirst we are reversing node in tempList
                    curr = forward; // for next iteration
                    idx++;
                }
            }
            else {
                curr = curr.next;  // Move to next node until you reach to left
                idx++;
            }
        }
        
        if(prev == null) head = th; // If left=1 => prev=null, so th will be your new head [modified because of reversing]
        else prev.next = th; // else add remaining left nodes to the th 
        
        tt.next = curr;  // Add remaining right nodes to the tt
        
        return head;
    }
    
    
    // Creating a tempList and doing addFirst
    public void addFirstInTempList(ListNode node) {
        if(th == null) {
            th = node;
            tt = node;
        }
        else {
            node.next = th;
            th = node;
        }
    }
    
}
