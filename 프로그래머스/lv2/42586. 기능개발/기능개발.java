import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        ArrayList<Integer> list = solve(progresses, speeds);
        answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    ArrayList<Integer> solve(int[] progresses, int[] speeds){
        ArrayList<Integer> answer = new ArrayList<>(); 
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++){
            int remain = 100 - progresses[i];
            if(remain % speeds[i] == 0) q.add((remain / speeds[i]));
            else q.add((remain / speeds[i]) + 1);
        }
        
        
        
        a : while(!q.isEmpty()){
                int now = q.poll();
                int task = 1;
                if(q.isEmpty()){
                    answer.add(task);
                    break;
                }

                while(now >= q.peek()) {
                    q.poll();
                    task++;

                    if(q.isEmpty()){
                    answer.add(task);
                    break a;
                    }
                }
                    
                answer.add(task);
            }
            
            return answer;
        }
    
}