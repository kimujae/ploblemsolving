import java.util.*;
class Solution {
    private char[] arr = {'A', 'E', 'I', 'O', 'U'};
    int idx = 0;
    int answer = 0;
    public int solution(String word) {
        dfs("", 0, word);
        return answer;
    }
    
    public void dfs(String word, int len, String target) {
        if(word.equals(target)) {
            answer = idx;
            return;
        }
        
        if(len == 5) return;
        for(int i = 0; i < 5; i++) {
            idx++;
            dfs(word+arr[i], len+1, target);
        }
    }
}