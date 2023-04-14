/*
 대회는 N명이 참가
 토너먼트 형식
 
 
처음 라운드에서 A번을 가진 참가자는 
경쟁자로 생각하는 B번 참가자와 몇 번째 라운드에서 만나는지 궁금

게임 참가자 수 N, 참가자 번호 A, 경쟁자 번호 B

몇 번째 라운드에서 만나는지 return 
*/

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        
        while(Math.abs(a-b) >= 1){
            if(a%2 == 1) a += 1;
            if(b%2 == 1) b += 1;

            a /= 2;
            b /= 2;
            answer++;
        }
        

        return answer;
    }
}