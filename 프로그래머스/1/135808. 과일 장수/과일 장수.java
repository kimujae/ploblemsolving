import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        Arrays.sort(score);
        int idx = score.length - 1;
        while(true) {
            if(idx-m+1 < 0) break;
            
            answer += (score[idx-m+1] * m);
            idx -= m;
        }
        
        return answer;
    }
}