// https://leetcode.com/problems/can-place-flowers/

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        for(int i = 0; i < len; i++) {
            if(flowerbed[i] == 0) {
                if((i == 0 || flowerbed[i - 1] == 0) && (i == len - 1 || flowerbed[i + 1] == 0)) {
                    flowerbed[i] = 1;
                    count++;
                    if(count == n) return true;
                }
            }
        }
        return count >= n;
    }
}
