import java.util.*;
class Solution {
    private boolean[] visit;
    private int answer = 0;
    private Set<Integer> numSet = new HashSet<>();
    public int solution(String numbers) {
        visit = new boolean[numbers.length()];
        dfs(numbers, "");
        for(int num : numSet) {
            boolean isPrime = true;
            for(int i = 2; i * i <= num; i++) {
                if(num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if(num != 0 && num != 1 && isPrime) answer++;
        }
        
        return answer;
    }
    
    public void dfs(String numbers, String target) {
        if(numbers.length() < target.length()) return;
        
        if(target.length() != 0) {
            int num = Integer.valueOf(target);
            numSet.add(num);
        }
        
        for(int i = 0; i < numbers.length(); i++) {
            if(visit[i]) continue;
            
            visit[i] = true;
            dfs(numbers, target+numbers.charAt(i));
            visit[i] = false;
        }
    }
}