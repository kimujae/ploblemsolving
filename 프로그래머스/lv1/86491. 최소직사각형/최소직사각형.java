import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        PriorityQueue<int[]> wpq = new PriorityQueue(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o2[1]- o1[1];
                return o2[0] - o1[0];
            }
        });
        
        PriorityQueue<int[]> hpq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[1] == o2[1]) return o2[0]- o1[0];
                return o2[1] - o1[1];
            }
        });
        
        
        
        for(int i = 0; i < sizes.length; i++){
            int x = sizes[i][0];
            int y = sizes[i][1];
            
            wpq.add(new int[]{x, y});
            hpq.add(new int[]{x, y});
        }
        
        int wmax = wpq.peek()[0];
        int hmax = hpq.peek()[1];
        answer = wmax * hmax;
        
        if(wmax > hmax){
            while(hpq.peek()[0] < hpq.peek()[1]){
                int[] now = hpq.poll();
                int x = now[1];
                int y = now[0];
                
                hpq.add(new int[]{x, y}); 
            }
            
            answer = wmax * hpq.peek()[1];
        }else{
            while(wpq.peek()[0] > wpq.peek()[1]){
                int[] now = wpq.poll();
                int x = now[1];
                int y = now[0];
                
                
                wpq.add(new int[]{x, y}); 
            }
            answer = hmax*wpq.peek()[0];
        }
        
        
        return answer;
    }
}