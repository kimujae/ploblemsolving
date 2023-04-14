import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        Map<String, Integer> wordMap = new HashMap<>(); 
        String curr ="";
        String prev ="";
        for(int i = 0; i < words.length; i++){
            curr = words[i];
            
            if(i > 0 && curr.charAt(0) != prev.charAt(prev.length()-1)){
                answer[0] = (i%n) + 1;
                answer[1] = (i/n) + 1;
                break;
            }
            if(wordMap.get(words[i]) == null) wordMap.put(words[i], 0);
            else{
                answer[0] = (i%n) + 1;
                answer[1] = (i/n) + 1;
                break;
            }
            
            
            
            prev = curr;
        }

        return answer;
    }
}