import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> st = new Stack();
        char[] carr = s.toCharArray();
        // toCharArray() idx접근 vs String charAt() 시간복잡도?
        int right = 0;
        
        for(char c : carr){
            if(c == '(') right++;
            else right--;
            
            if(right <  0) return false;
        }
        
    
        if(right != 0) return false;
        return true;
    }
}