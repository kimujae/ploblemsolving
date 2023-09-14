import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int maxidx = -1;
        for(int i = 0 ; i < sizes.length; i ++) {
            if(max < sizes[i][0] || max < sizes[i][1]) {
                maxidx = i;
                max = Math.max(sizes[i][1], sizes[i][0]);

            }
        }
        
        int max2 = 0;
        for(int i = 0; i < sizes.length; i++) {            
            max2 = Math.max(max2, Math.min(sizes[i][0], sizes[i][1]));
        }
        
        return max * max2;
    }
}