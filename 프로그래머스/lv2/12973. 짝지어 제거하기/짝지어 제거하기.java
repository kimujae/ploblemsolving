


import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        answer = solve(s);
        return answer;
    }
    // 반복

    //먼저 문자열에서 같은 알파벳이 2개 붙어 있는 짝을 찾
    // 그 둘을 제거
    //앞뒤로 문자열을 이어 붙입니다

    // 문자열을 모두 제거한다면 짝지어 제거하기가 종료
    
    int solve(String s){
        Stack<Character> q = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char c =s.charAt(i);
            
            if(!q.isEmpty() && q.peek() == c) q.pop();
            else q.push(c);
        }
        
        if(q.isEmpty()) return 1;
        else return 0;
        
    }
}