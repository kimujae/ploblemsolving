class Solution {
    public int[] solution(String s) {
        int[] answer;
        answer = solve(s);
        return answer;
    }
    
    int[] solve(String s){
        int zero_sum = 0; // 0삭제 개수
        int count = 0; // 반복 횟수
        
        
        while(true){
            count++;// 반복횟수 추가
            int zero = 0; // 현재 문자열에서 삭제된 0개수 저장 
            
            for(int i =0; i < s.length(); i++){
                if(s.charAt(i)  == '0') zero++; // 0삭제
            }
            
            
            zero_sum += zero;
            
            int num = s.length() - zero; // 타겟 문자
            s = Integer.toString(num, 2); // 2진수 변경
            if(s.equals("1"))break;
        }
        return new int[]{count, zero_sum};
    }
}