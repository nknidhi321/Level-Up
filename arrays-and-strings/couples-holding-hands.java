// https://leetcode.com/problems/couples-holding-hands/

// Assuming for a couple pehle pos pe bnda h, and dusre pe bndi, [bnda, bndi]
// It has no relation with question just to understand easily
class Solution {
    
    public int minSwapsCouples(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(arr[i], i); // {bndaId, index}
        }
        System.out.println(map);
        
        int numberOfSwaps = 0;
        for(int i = 0; i < n; i+=2) { // 2 jan (couple) ka kaam saath me so ho raha so +=2
            int bnda = arr[i];
            int KiskiBandi = arr[i + 1]; // You don't know who's sitting beside
            
            // Format of couples (0 1), (3 2), (4 5), (7 6), (8 9) ....
            if(bnda % 2 == 0) { // val even number h to uska partner just val + 1 hoga
                int bndaKiBandi = bnda + 1;
                if(KiskiBandi == bndaKiBandi) { // bnde ki bndi 
                    continue;
                }
                else { // kisiAurKiBndi, so find iski bndi kis idx pe h and swap with kisiAurKiBndi idx
                    swap(i + 1, map.get(bndaKiBandi), arr, map);
                    numberOfSwaps++;
                }
            }
            else { // val odd number h to uska partner just val - 1 hoga
                int bndaKiBandi = bnda - 1;
                if(KiskiBandi == bndaKiBandi) { // bnde ki bndi 
                    continue;
                }
                else { // kisiAurKiBndi, so find iski bndi kis idx pe h and swap with kisiAurKiBndi idx
                    swap(i + 1, map.get(bndaKiBandi), arr, map);
                    numberOfSwaps++;
                }
            }
        }
        return numberOfSwaps;
        
    }
    
    public void swap(int a, int b, int[] arr, Map<Integer, Integer> map) {
        map.put(arr[a], b);
        map.put(arr[b], a);
        
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
}
