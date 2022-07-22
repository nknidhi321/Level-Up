// https://practice.geeksforgeeks.org/problems/pairwise-swap-elements-of-a-linked-list-by-swapping-data/1

class Solution {

    public Node pairwiseSwap(Node head) {
        if(head == null || head.next == null) return head;
        
        Node pprev = new Node(-1);
        Node prev = head;
        Node curr = prev.next;
        Node ans = curr;
        
        while(true) {
            Node forw = curr.next;
            prev.next = forw;
            curr.next = prev;
            
            // har dusre bnde ko khud se +2 wale bnde ko point krwana h
            pprev.next = curr;
            pprev = prev;
            
            // For next iterations
            if(forw != null && forw.next != null) {
                curr = forw.next;
                prev = forw;
            }
            else {
                break;
            }
        }
        
        // Node temp = ans;
        // while(ans != null) {
        //     System.out.print(ans.data + "\t");
        //     ans = ans.next;
        // }
        
        return ans;
    }
    
}
