import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = -1;
        answer = solve(skill, skill_trees);
        return answer;
    }
    
    int solve(String skill, String[] skill_trees){
        Map<Character, Integer> indegree = new HashMap<>();
        int ans = 0;
        
        for(int i = 0; i < skill.length(); i++){
            char ch = skill.charAt(i);
            indegree.put(ch, i+1);
        }
        
        a: for(int i = 0; i < skill_trees.length; i++){
            String target = skill_trees[i];
            int search = 1;
            for(int j = 0; j < target.length(); j++){
                // 타겟 문자열 순회 
                char curr = target.charAt(j);
                if(indegree.get(curr) == null)
                    continue;
                
                if(indegree.get(curr) == search) search++;
                else continue a;
            }
            ans++;
        }        
        
        return ans;
    }
}