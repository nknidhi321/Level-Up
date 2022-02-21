// https://nados.io/question/unfold-of-linkedlist?zen=true/ 

import java.util.*;

class Main {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    //=========================================================================================================
    public static void unfold(ListNode head) {
        if(head == null || head.next == null) return;
        
        ListNode head2 = head.next; // Keep hold of headsNext because it will manipulate in the loop

        ListNode c1 = head;       // c1 = curr1
        ListNode c2 = head.next;  // c2 = curr2
        while(c2 != null && c2.next != null) { // [c2 != null : odd] , [c2.next != null : even]
            ListNode f = c2.next; // backup forward node, where f = foward
            c1.next = f;          // link by skipping one node from curr 
            c2.next = f.next;     // link by skipping one node from curr

            c1 = f;               // move by skipping one node from curr
            c2 = f.next;          // move by skipping one node from curr
        }

        c1.next = null; // For even length node c1 will still be pointing to the last node, so break that link
        
        // Ab 2 separate LL bn chuki hai  
      
        // Note original head ka next avi head.next.next hai, and not head.next
        // So if we pass head.next in reverseList(head2), it is wrong
        c1.next = reverseList(head2); // Reverse head2 and link it with the last node of 1st LL
    }


    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;  // Aadat
        
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr != null) {
            ListNode forward = curr.next; // Break krne se pehle aage k nodes ko point karwa lo kisi se [backup]
            curr.next = prev;             // curr k next me prev node ka address daal do, Reverse ho gayi [link]
            
            prev = curr;                  // next iteration k liye prev curr pe chala jaaega [move]
            curr = forward;               // aur curr forward pe chala jaaega [move]
        }
        
        // NOTE : forward bus ek temp backup node tha loop me
        
        return prev;
    }
    //=============================================================================================================


    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        ListNode head = dummy.next;
        unfold(head);
        printList(head);
    }
}
