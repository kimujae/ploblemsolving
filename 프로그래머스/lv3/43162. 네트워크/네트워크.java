import java.util.Queue;
import java.util.LinkedList;

class Solution {
    Queue<Integer> queue = new LinkedList<>();
    boolean[][] visited; 
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            if(computers[i][i] == 1 && !visited[i][i]){
                bfs(i, n , computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    void bfs(int node, int n, int[][] computers){  
        queue.add(node);
        visited[node][node] = true;
        
        while(!queue.isEmpty()){
            int now = queue.poll();
            
            for(int i = 0; i < n; i++){
                if(visited[now][i])
                    continue;
                
                if(computers[now][i] == 0)
                    continue;
                
                queue.add(i);
                visited[now][i] = true;
            }   
        }
    }
}