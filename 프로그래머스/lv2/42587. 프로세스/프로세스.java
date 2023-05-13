import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        boolean[] visited = new boolean[priorities.length];
        Queue<int[]> q = new LinkedList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
        
        for(int i = 0; i < priorities.length; i++){
            pq.add(new int[]{priorities[i], i});
            q.add(new int[]{priorities[i], i});
        }
        
        
        while(!q.isEmpty()){
            while(pq.peek()[0] != q.peek()[0]){
                q.add(q.poll());
            }
            
            answer++;
            if(q.peek()[1] == location) return answer;
            q.poll();
            pq.poll();
        }
        return answer;
    }
}