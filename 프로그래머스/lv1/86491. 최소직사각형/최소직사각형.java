import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int val1 = 0 ; int val2 = 0;
        for(int[] size : sizes) {
            val1 = Math.max(val1, Math.max(size[1], size[0]));
            val2 = Math.max(val2, Math.min(size[0], size[1]));
        }
        
        return val1 * val2;
    }
}