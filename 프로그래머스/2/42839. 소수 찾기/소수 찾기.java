import java.util.*;
class Solution {
    private Set<Integer> ans = new HashSet<>();
    private boolean[] v;
    public int solution(String numbers) {
        v = new boolean[numbers.length()];
        for(int i = 1; i < numbers.length() +1 ; i++) {
            int[] sel = new int[i];
            dfs(i, 0, sel, numbers);
        }
        
        
        return ans.size();
    }
    
    public void dfs(int target, int depth, int[] sel, String numbers) {
        if(depth == target) {
            int idx = target - 1;
            int sum = 0;
            for(int n : sel) {
                sum = sum + (n * (int)Math.pow(10, idx--));
            }
            //stem.out.println(sum);

            // 소수 판단 알고리즘
            if(sum == 0 || sum == 1) return;
            
            for(int i = 2; i <=Math.sqrt(sum); i++) {
                if(sum % i == 0) return; // 소수가 아님 
            }
            
            // 소수 맞음
            ans.add(sum);
            return;
        }
        
        
        for(int i = 0; i < numbers.length(); i++) {
            if(v[i]) continue;
            
            v[i] = true;
            sel[depth] = Character.getNumericValue(numbers.charAt(i));
            dfs(target, depth+1, sel, numbers);
            v[i] = false;
        }
        
        
    }
    
   
}