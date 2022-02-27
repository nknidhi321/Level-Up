// https://leetcode.com/problems/reverse-nodes-in-k-group/

class Solution {
    
    // Make sure to make th & tt static else addFirstInTempList will have to return both th and tt, since it is call by value
    // So when addFirstInTempList function returns th and tt in main will still be pointing to where it was poiting.   
    public static ListNode th = null;  // th = tempHead
    public static ListNode tt = null;  // tt = tempTail
    
    public ListNode reverseKGroup(ListNode head, int K) {
        if(head == null || head.next == null) return head;
        
        // Calculate size of LL because if the remaining nodes will be less than K then no reversing is to be done 
        int size = sizeOfLL(head); 
        
        ListNode oh = null; // oh = originalHead
        ListNode ot = null; // ot = originalTail
        ListNode curr = head;
        
        while(size >= K) { // Reverse only till there is full K length nodes left
            int tempK = K;
            while(tempK-- > 0) { // Stop when tempK becomes 0
                ListNode forward = curr.next;
                curr.next = null; // Break link with next node
                addFirstInTempList(curr); // By addFirst we are reversing node in tempList
                curr = forward; // for next iteration
            }
            
            // After K iterations add tempList in new original List
            if(oh == null) { // For 1st time when oh is null, make tempList as new OriginalList
                oh = th;
                ot = tt;
            }    
            else { // After 1st time, add tempList at the end of new originalList 
                ot.next = th;
                ot = tt;
            }
            
            // For next K iterations 
            th = null; // Make tempHead to null 
            tt = null; // Make tempTail to null
            size -= K; // decrese size by K 
        }
        
        ot.next = curr; // Jo K se kam nodes bach gaye the unhe originalTail k end me add kar do
        return oh;
    }
    
    public static int sizeOfLL(ListNode head) {
        int size = 0;
        ListNode curr = head;
        while(curr != null) {
            curr = curr.next;
            size++;
        }
        return size;
    }
    
    public static void addFirstInTempList(ListNode node) {
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
