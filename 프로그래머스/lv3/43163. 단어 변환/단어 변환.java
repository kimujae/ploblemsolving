import java.util.*;
class Solution {
    private boolean[] visit;
    private int min = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visit = new boolean[words.length];
        dfs(0, target, words, begin);
        if(min == Integer.MAX_VALUE) return 0;
        else return min;
    }
    
   public void dfs(int depth, String target, String[] words, String word) {
       if(word.equals(target)) min = Math.min(min, depth);
       if(depth == words.length) return;
       
       int idx = -1;
       for(String w : words) {
           if(visit[++idx]) continue;
           
           int diff = 0;
           for(int i = 0; i < w.length(); i++) {
               if(word.charAt(i) != w.charAt(i)) diff++;
           }
           
           if(diff == 1) {
               visit[idx] = true; 
               dfs(depth + 1, target, words, w);
               visit[idx] = false;
           }
       }
   }
}