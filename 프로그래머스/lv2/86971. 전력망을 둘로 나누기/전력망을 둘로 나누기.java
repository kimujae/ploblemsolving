import java.util.*;
class Solution {
    ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    boolean[] visited;
    int count;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0; i < n+1; i++){
            tree.add(new ArrayList<>());
        }
        
        for(int i = 0; i < wires.length; i++){
            int node1 = wires[i][0];
            int node2 = wires[i][1];
            
            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }
        visited = new boolean[n+1];
        
        int a1 =0, a2 =  0;
        for(int i = 0; i < wires.length;i++){
            int node1 = wires[i][0];
            int node2 = wires[i][1];
            
            init(node2, n);
            dfs(node1);
            a1 = count;
            
            init(node1, n);
            dfs(node2);
            a2 = count;
            
            answer = Math.min(answer, Math.abs(a1-a2));
        }
        
        
        return answer;
    }
    
    void init(int node, int n){
        count = 0;
        visited =new boolean[n+1];
        visited[node] = true;
    }
    
    
    
    void dfs(int now){
        visited[now] = true;
        count++;
        
        for(int i = 0; i < tree.get(now).size(); i++){
            int next = tree.get(now).get(i);
            
            if(visited[next])
                continue;
            
            dfs(next);
        }
    }
}