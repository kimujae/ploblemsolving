import java.util.*;
class Solution {
    //foodtimes의 총합과 k를 가지고 수행, 순위 == 나머지? 
    private PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
    
    public int solution(int[] food_times, long k) {
        int answer = 0;
        for(int i = 0; i < food_times.length; i++){
            pq.add(new int[]{food_times[i], i});
        }
        
        answer = solve(food_times, k);
        return answer;
    }

    
    
    public int solve(int[] food_times, long k){
        long round = 0;
        
        while(k - pq.size() > 0){
            k -= pq.size();
            round++;
            
            while(pq.peek()[0]- round == 0){
                pq.poll();
                if(pq.isEmpty()) return -1;
            }
        }
        

        while(k > -1){
            boolean isExist = false;
            for(int i = 0; i < food_times.length; i++){
                if(food_times[i] - round <= 0)
                    continue;

                k--;
                isExist = true;
                if(k == -1) return i+1;
            }
            round++;
            if(!isExist)return -1;
        }
        return -1;
    }
}