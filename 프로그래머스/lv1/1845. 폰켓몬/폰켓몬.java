/*
총 N 마리의 폰켓몬 중에서 N/2마리를 가져가도 좋다
연구실의 폰켓몬은 종류에 따라 번호를 붙여 구분
같은 종류의 폰켓몬은 같은 번호

최대한 많은 종류의 폰켓몬을 포함해서 N/2마리를 선택하려 합니다
*/

import java.util.*;
class Solution {
    private Map<Integer, Integer> pocketmon = new HashMap<>();
    public int solution(int[] nums) {
        int answer = 0;
        answer = check(nums);
        return answer;
    }
    
    int check(int[] nums){
        for(int i = 0; i < nums.length; i++){
            pocketmon.put(nums[i], pocketmon.getOrDefault(nums[i],0)+1);
        }
        
        int target = nums.length/2; 
        if(pocketmon.size()>= target) return target;
        else return pocketmon.size();
    }
    
}