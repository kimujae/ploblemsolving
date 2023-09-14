import java.util.*;
class Solution {
    public List<Integer> solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
        
        
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three ={3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == one[i % one.length]) result1++;
            if(answers[i] == two[i % two.length]) result2++;
            if(answers[i] == three[i % three.length]) result3++;
        }
        
        pq.add(new int[]{result1, 1});
        pq.add(new int[]{result2, 2});
        pq.add(new int[]{result3, 3});
        
        
        int[] max = pq.poll();
        answer.add(max[1]);
        while(!pq.isEmpty() && pq.peek()[0] == max[0]) {
            answer.add(pq.poll()[1]);
        }
        
        return answer;
    }
}