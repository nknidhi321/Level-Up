// https://nados.io/question/segregate-01-node-of-linkedlist-over-swapping-nodes?zen=true

import java.util.*;

class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    
    //=================================================================================================
    public static ListNode segregate01(ListNode head) {
        ListNode evenDummy = new ListNode(-1);
        ListNode oddDummy = new ListNode(-1);
        
        ListNode ep = evenDummy;
        ListNode op = oddDummy;
        
        ListNode curr = head;
        while(curr != null) {
            if(curr.val == 0) {  // Only change from even odd segregation
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
    //==================================================================================================


    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static ListNode createList(int n) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        ListNode h1 = createList(n);
        h1 = segregate01(h1);
        printList(h1);
    }
}
