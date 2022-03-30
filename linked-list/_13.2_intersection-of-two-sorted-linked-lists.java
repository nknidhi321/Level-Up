// https://practice.geeksforgeeks.org/problems/intersection-of-two-sorted-linked-lists/1/#

class Sol {

   public static Node findIntersection(Node head1, Node head2) {
        Node c1 = head1;
        Node c2 = head2;
        Node dummy = new Node(-1);
        Node prev = dummy;
        while(c1 != null && c2 != null) {
            if(c1.data == c2.data) {
                prev.next = new Node(c1.data);
                prev = prev.next;
                c1 = c1.next;
                c2 = c2.next;
            }
            else if(c1.data < c2.data) c1 = c1.next;
            else c2 = c2.next;
        }
        return dummy.next;
    }
    
}
