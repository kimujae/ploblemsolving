import java.util.*;
class Solution {
    private ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    private boolean[] v;
    private int count;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }// 초기화
        
        for(int[] info : wires) {
            int node1 = info[0];
            int node2 = info[1];
            
            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        } // 전력망 정보 완성
        
        
        int diff = 0;
        int sum1 = 0, sum2 = 0;
        for(int[] info : wires) {
            v = new boolean[n+1];
            int node1 = info[0];
            int node2 = info[1];
            
            v[node1] = true;
            v[node2] = true;
            
            count = 1;
            dfs(node1);
            sum1 = count;
            count = 1;
            dfs(node2);
            sum2 = count;
            
            diff = Math.abs(sum1 - sum2);
            answer = Math.min(answer, diff);
        }
        
        
        return answer;
    }
    
    public void dfs(int now) {
        for(int i = 0; i < tree.get(now).size(); i++) {
            if(v[tree.get(now).get(i)]) continue;
            
            
            int next = tree.get(now).get(i);
            v[next] = true;
            count++;
            dfs(next);
        }
    }
    
    
}