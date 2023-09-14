import java.util.*;
class Solution {
    private boolean[] visit;
    private int max;
    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        permutation(k, dungeons, 0);
        return max;
    }

    public void permutation(int k , int[][] dungeons, int depth) {
        for(int i = 0; i < dungeons.length; i++) {
            if(visit[i] || k < dungeons[i][0]) continue;

            visit[i] = true;
            k -= dungeons[i][1];
            permutation(k, dungeons, depth + 1);
            k += dungeons[i][1];
            visit[i] = false;
        }
        
        
        max = Math.max(max, depth);
    }
}