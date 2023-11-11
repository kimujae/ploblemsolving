import java.util.*;
class Solution {
    ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    private char[] ch = {'A', 'E', 'I', 'O', 'U'};
    private ArrayList<String> dict;
    public int solution(String word) {
        dict = new ArrayList<>();
        dfs(0, "");
        
        return dict.indexOf(word);
    }
    
    public void dfs(int depth, String str) {
        dict.add(str);
        if(depth == 5) return;
        
        for(int i = 0; i < 5; i++) {
            dfs(depth + 1, str + ch[i]);
        }
    }
}