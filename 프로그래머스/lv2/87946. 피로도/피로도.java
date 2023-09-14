import java.util.*;
class Solution {
    private List<int[]> list = new ArrayList<>();
    private boolean[] visit;
    private int max;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visit = new boolean[dungeons.length];
        
        permutation(k, dungeons, list, dungeons.length);
        return max;
    }
    
    public void permutation(int k , int[][] dungeons, List<int[]> list, int target) {
        if(list.size() == target) {
            int adventures = 0;
            // 던전 탐험 판정로직 
            for(int[] fatigues : list) {
                if(k < fatigues[0])break;
                  
                k -= fatigues[1];
                adventures += 1;
            }
            max = Math.max(max, adventures);
            return;
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            if(visit[i]) continue;
            
            visit[i] = true;
            list.add(dungeons[i]);
            permutation(k, dungeons, list, target);
            list.remove(list.size() - 1);
            visit[i] = false;
        }
    }
}