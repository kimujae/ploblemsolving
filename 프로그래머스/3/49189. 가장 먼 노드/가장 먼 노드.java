import java.util.*;
class Solution {
    private boolean[] v;
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> g = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        
        for(int[] info : edge) {
            int s = info[0];
            int e = info[1];
            
            g.get(s).add(e);
            g.get(e).add(s);
            // 무방향그래프
        }
        
        v = new boolean[n+1];
        return bfs(1, g);
    }
    
    public int bfs(int start, ArrayList<ArrayList<Integer>> g) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        v[start] = true;
        int ret = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            int count = 0;
            
            ret = size;
            while(size > count) {
                int s = q.poll();
                count++;
                
                for(int i = 0; i < g.get(s).size(); i++) {
                    int next = g.get(s).get(i);

                    if(v[next]) continue;

                    v[next] = true;
                    q.add(next);
                }
            }
        }
        
        return ret;
       
    }
}