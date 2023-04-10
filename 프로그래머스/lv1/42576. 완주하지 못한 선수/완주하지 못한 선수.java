/*
단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주
participant 마라톤에 참여한 선수들의 이름배열
completion 완주한 선수들의 이름이 담긴 배열

참가자 중에는 동명이인이 있을 수 있습니다.
*/
/*
해시에 완주자 명단을 모두 넣는다.
참가자 명단을 get하며 완주여부를 체크한다. 
*/
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        answer = checkCompletion(participant, completion);
        return answer;
    }
    
    String checkCompletion(String[] participant, String[] completion){
        Map<String, Integer> completionMap = new HashMap<>();
        String ans = "";
        for(int i = 0; i < completion.length; i++){
            completionMap.put(completion[i], completionMap.getOrDefault(completion[i], 0)+1);
        }// 해시 입력 완료
        
        for(int i = 0; i < participant.length; i++){
            if(completionMap.getOrDefault(participant[i], 0) != 0){
                completionMap.put(participant[i], completionMap.get(participant[i])-1);   
                continue;
            }
            ans = participant[i];
            break;
        }
        
        return ans;
    } 
    
    
    
}