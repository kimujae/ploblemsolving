class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        for(int i = 1; i <= sum ; i++) {
            if(sum % i != 0) continue;
            int j = sum / i;
            
            
            if((i - 2)*(j - 2) != yellow || (i*j) - yellow != brown) continue;
            answer = new int[]{j, i};
            break;
        }
        
        
        return answer;
    }
}