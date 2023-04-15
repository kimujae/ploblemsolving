/*
그 문자의 개수와 반복되는 값으로 표현 -> 더 짧은 문자열로 줄여서 표현
문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 방법을 찾아보려한다.


문자열 단위 : 1~s.length()/2 까지 반복 
문자열 순회탐색 -> 압축 반복

*/
class Solution {
    public int solution(String s) {
        int answer = 0;
        if(s.length() == 1) answer = 1;
        else answer = solve(s);
        return answer;
    }
    
    int solve(String s){
        int min = Integer.MAX_VALUE;
        
        for(int i = 1; i <= s.length()/2; i++){
            // 문자열 trim 단위 
            int iter = 1;// 반복 문자열 횟수
            int len = 0;
            String curr  = "";
            String prev = s.substring(0, i);
            int idx = i;
            
            while(true){
                // 문자열 탐색
                if(idx+i > s.length()) {
                    // 반복 문자열 횟수 길이 + trim문자열 길이  + 잔여 문자열 
                    if(iter > 1)len += String.valueOf(iter).length() + i + s.length()-idx;
                    else len += i + s.length()-idx;
                    break;
                }
                
                curr = s.substring(idx, idx+i); // 현재 문자열 할당
                if(curr.equals(prev)) iter++; //비교문자열이 맞다면 반복횟수++
                else{// 비교문자열이 다르다면,
                    if(iter > 1) len += String.valueOf(iter).length() + i;
                    else len += i;
                    
                    iter = 1;
                    prev = curr; // 비교문자열 교체
                }
                idx = idx+i;
            }
            min = Math.min(min, len);
        }
        return min;
    }
}