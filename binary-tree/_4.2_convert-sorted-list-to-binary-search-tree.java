// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

/*
    NOTE : Yaha Tree alag bn raga hai, ListNode pe bs divide and conquer lga rahe
           ListNode ko he tree me convert nahi kr rahe
*/

// From getPrevAndMid, It is expecting 2nd mid node as root

class Solution {
 
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
            
        ListNode[] PrevAndMid = getPrevAndMid(head); // {Prev, 2ndMid}
        ListNode prev = PrevAndMid[0]; // Prev
        ListNode mid = PrevAndMid[1];  // 2nd mid
        ListNode forw = mid.next;
        
        // Breaking the links of the midNode for Divide and Conquer, to create 2 seperate list
        if(prev != null) prev.next = null;
        mid.next = null;
        
        TreeNode root = new TreeNode(mid.val); // Creating rootNode
        
        // 1st half list
        // Jab sirf ek he node hoga then
        // agar head ko he pass kr diya then infinite call lg jaaegi
        // So agar prev null hai => Sirf ek he node hai, so pass the 1st half list as null
        root.left = sortedListToBST(prev != null ? head : null); 
        
        // 2nd half List
        // Incase sirf 1 he node hua then forw toh null he rahega, so pass the 2nd half as forw/null 
        root.right = sortedListToBST(forw);
        
        return root;
    }
    
    
    // Returns prev and 2nd mid Node
    public static ListNode[] getPrevAndMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next; 
        }
    
        return new ListNode[] {prev, slow};
    }
    
}

//---------------------------------------------------------

// Same as above
// From getPrevAndMid, It is expecting 1st mid node as root

class Solution {
    
    class Pair {
        ListNode prev;
        ListNode mid;
        public Pair(ListNode prev, ListNode mid) {
            this.prev = prev;
            this.mid = mid;
        }
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        
        Pair prevAndMid = getPrevAndMidNode(head);
        
        ListNode prev = prevAndMid.prev;
        ListNode mid = prevAndMid.mid;
        ListNode forw = mid.next;
        
        if(prev != null) prev.next = null;
        if(mid != null) mid.next = null;
        
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(prev == null ? null : head);
        root.right = sortedListToBST(forw);
        return root;
    }
    
    public Pair getPrevAndMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return new Pair(prev, slow);
    }

}
