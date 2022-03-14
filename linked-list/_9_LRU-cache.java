// https://leetcode.com/problems/lru-cache/

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


/*
    Most recent app will be at head and LRU(Least Recently used) app will be at tail
    The idea is to have a Map {key(App name), Node(Address)}, kind of table to keep track of cached apps 
    Have a dummy head and tail ptr to escape null ptr checks when size of LL will be 0 or 1
    When size of Doubly LL == capacity then LRU app will be deleted from tail and the new app will be added at head
    When you get an app it becomes most recent
*/

class LRUCache {
    
    private class ListNode {
        /* Doubly LL Node will store it's 
            1) app id/key/name
            2) app value/state
            3) prev pointer
            4) next pointer
        */    
        Integer key, value;  
        ListNode next = null; 
        ListNode prev = null;

        ListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    
    // Table of HashMap<{app name}, {app address}> 
    private HashMap<Integer, ListNode> cacheTableMap;
    private int capacity;
    private ListNode head;
    private ListNode tail;

    
    // initialize your data members
    private void intialize(int capacity) {
        this.cacheTableMap = new HashMap<>();
        this.capacity = capacity;
        this.head = new ListNode(-1, null); //  dummy head
        this.tail = new ListNode(-1, null); //  dummy tail
        
        // Make dummy head and dummy tail point to each other
        this.head.next = tail;
        this.tail.prev = head;
    }

    
    // LRUCache constructor will initialize it's data members
    public LRUCache(int capacity) {
        intialize(capacity); 
    }

    
    // New app will be added at head of Doubly LL
    // Add the new node just after dummy head 
    private void addFirst(ListNode node) {
        ListNode nbr = this.head.next;
        
        node.next = nbr;
		node.prev = this.head;
        
        nbr.prev = node;
		this.head.next = node;
    }
    
    
    // LRU app will always be deleted from tail so, remove the node which is just before dummy tail
    // When you get an app, that app become mostRecent so remove(break links) of the node/app and bring it at head
    
    
    // removes any node from Doubly LL
    private void removeNode(ListNode node) {
        ListNode prevNbr = node.prev;
        ListNode nextNbr = node.next;
        
        prevNbr.next = nextNbr;
        nextNbr.prev = prevNbr;
        
        node.prev = node.next = null;
    }

    
    // The app which will be checked most recently will come at the head of the Doubly LL
    // Remove from where ever app exists and place it at the head of the Doubly LL
    private void makeRecent_OR_moveToFront(ListNode node) {
        removeNode(node);
        addFirst(node);
    }

    
    // Check if the app exists in the table? Return it's state/value else return -1 
    public int get(int key) {
        if (!cacheTableMap.containsKey(key))
            return -1;

        ListNode node = cacheTableMap.get(key);
        makeRecent_OR_moveToFront(node);
        return node.value;
    }

    
    // When you open an app, add it in your cache
    public void put(int key, int value) {
        
        if (cacheTableMap.containsKey(key)) { // If app already exists in the cache
            ListNode node = cacheTableMap.get(key); // Get the app
            node.value = value; // Change state && 
            makeRecent_OR_moveToFront(node); // Make it the most recent
        } 
        else { // If app does not exist in the cache
            ListNode newNode = new ListNode(key, value); // Create a node of the app 
            if (cacheTableMap.size() == this.capacity) { // Check if the cache is full
                ListNode LRU_ListNode = this.tail.prev; // Get the last node's address from the cache
                cacheTableMap.remove(LRU_ListNode.key); // Remove last node from the cache
				removeNode(LRU_ListNode); // Also remove from the cacheTableMap
            }

            addFirst(newNode); // Now add your new app in cache
            cacheTableMap.put(key, newNode); // Also register new app in cacheTableMap
        }
    }
}
