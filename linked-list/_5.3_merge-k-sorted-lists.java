// https://leetcode.com/problems/merge-k-sorted-lists/

Rajneesh Bhaiya 03) b
Best Approach, TC : N(logK) 
Using Sorted property of LL
Sab individual list already sorted hai, so no need to make it 1 mainList and then sort,
just see when si == ei that time just return list of that idx [Simple mergeSort]

```
class Solution {
    
    public ListNode mergeKLists(ListNode[] lists) {   
        if(lists.length == 0) return null;
        return mergeKSortedList_03(lists, 0, lists.length - 1);
    }
    
    public ListNode mergeKSortedList_03(ListNode[] lists, int si, int ei) {
        if (si == ei) return lists[si];

        int mid = (si + ei) / 2;
        return mergeTwoSortedLists(mergeKSortedList_03(lists, si, mid), mergeKSortedList_03(lists, mid + 1, ei));
    }
    
    // Merging 2 sorted lists
    public ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        
        while(curr1 != null && curr2 != null) {
            if(curr1.val <= curr2.val) {
                prev.next = curr1;
                curr1 = curr1.next;
            }
            else {
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }
        
        prev.next = curr1 != null ? curr1 : curr2;
        return dummy.next; 
    }
    
}
```

--------------------------------------------------------------------------------------

Rajneesh Bhaiya 03) a
TC : NK * (log K)

Not using the sorted property of individual LL
The idea is, saare individual list ko jor k 1 main list bna do and mergeSort kar do. 
    
```
class Solution {
   
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        for(ListNode list : lists) {
            ListNode tail = getTail_OR_lastNode(list); // Curr list ka tail find karo
            
            // Agar wo null hai toh usko apne main list me add nahi karenge
            // otherwise null->null->null.. aisa mainlist bn jaaega kisi din
            if(tail != null) { 
                prev.next = list; // Pichle list k end me khud ko jor do 
                prev = tail; // And next iteration k liye, prev ko curr list k end pe pahucha do
            }
        }
        prev.next = null; // main list k last node k next me null daal do, already null hoga writing just for visualization
        
        return mergeSort(dummy.next); // mergeSort kar k return kar do.
    }
    
    public static ListNode getTail_OR_lastNode(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode curr = head;
        
        while(curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }
    
    
    public ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode mid = mid(head);
        ListNode nhead = mid.next; // head of 2nd LL
        mid.next = null; // Setting 1st half LL end to null
        
        // Divide and conquer, recursively divide and then merge
        return mergeTwoSortedLists(mergeSort(head), mergeSort(nhead));
    }
    
    
    // 1st mid
    public ListNode mid(ListNode head) {
      if(head == null || head.next == null) return head;  

      ListNode slow = head; 
      ListNode fast = head;

      while(fast.next != null && fast.next.next != null) {   // fast will never be null here
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }
    
    
    // Merging 2 sorted lists
    public ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        
        while(curr1 != null && curr2 != null) {
            if(curr1.val <= curr2.val) {
                prev.next = curr1;
                curr1 = curr1.next;
            }
            else {
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }
        
        prev.next = curr1 != null ? curr1 : curr2;
        return dummy.next; 
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

Rajneesh Bhaiya 02
Using PQ
TC : NK * (log K)
SC : O(K) // Saare LL ka 1st node rahega PQ me, baaki sirf pointers heap me honge [Not sure]

Saare list ko PQ me daal do, saare ll k head pe jo element hoga un sbka comparison hoga, [NOTE : do not insert null] 
Since using minHeap, smallest node nikaalo answer bnate jaao
     
```
class Solution {
    
    public ListNode mergeKLists(ListNode[] lists) {
        
        // PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
        //     return a.val - b.val; // default behaviour, minHeap
        //  // return b.val - a.val; // reverse behaviour, maxHeap
        // });
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Adding all lists in PQ
        for(ListNode list : lists) {
            if(list != null) {
                pq.add(list);
            }
        }    
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy; // prev will be a kind of tail pointer to ansList
        
        while(!pq.isEmpty()) {
            ListNode node = pq.poll();  // minNode nikal gaya
            ListNode forw = node.next;
            
            node.next = null;
            prev.next = node; // ansList me usko jor diye
            
            prev = prev.next; // prev ko ansList k tail pe point kara do
            node = forw; // minNode use ho chuka hai, so node ko aage badha do
            
            // Jis list ko nikale the check karo kya ab usme sirf null toh nahi bacha hai
            // Agar sirf null hai toh, skip that list, since completed
            // par agar ab v elements bache hai then add the remaining list to pq
            if(node != null) pq.add(node); 
        }
        
        return dummy.next;
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

Rajneesh Bhaiya 01
TC : N * (K^2)
Iterating on each list one by one and mergingTwoSortedLists

```
class Solution {
    
    public ListNode mergeKLists(ListNode[] lists) {
        
        // Iterating on each list one by one and mergingTwoSortedLists
        ListNode res = null;
        for(ListNode list : lists) {
            res = mergeTwoLists(res, list);
        }
        return res;
    }
    
     public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        // Maintain dummy othewise you will have to write more if's statement while creating the very 1st node 
        ListNode dummy = new ListNode(-1); 
        
        // The smaller list(curr) will lastly point to null in the while loop,
        // after that adding the remaining node to the end of the samller list 
        // So we have to keep track of the prev node beforehand so that when curr points to null we point to the prev of curr
        ListNode prev = dummy;
        
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        
        while(curr1 != null && curr2 != null) {
            if(curr1.val <= curr2.val) {
                prev.next = curr1;
                curr1 = curr1.next;
            }
            else {
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }
        
        prev.next = curr1 != null ? curr1 : curr2; // Adding the remaining nodes to the end of smaller list
        return dummy.next; // return dummy.next because 1st node was useless
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
