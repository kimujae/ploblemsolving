import java.util.*;
import java.lang.*;
class Solution {
    public String solution(String p) {
        String answer = "";
        if(check(p)) return p;
        answer = solve(p);
        return answer;
    }
    
    
    
    String solve(String w){
        if(w.length() == 0) return "";
        
        int l = 0; 
        int r = 0;
        String u= "" , v = "";
        StringBuilder sb = new StringBuilder();
        
        
        for(int i = 0; i < w.length(); i++){
            if(w.charAt(i) == '(') l++; 
            else r++;
            
            if(l == r){ 
                u = w.substring(0, i+1);
                v = w.substring(i+1, w.length());
                
                if(check(u)) {
                    sb.append(u).append(solve(v)); // 균형잡힌 괄호
                    break;
                }
                else {
                    sb.append('(').append(solve(v)).append(')');
                    u = w.substring(1, i);
                    sb.append(reverse(u));
                    return sb.toString();
                }    
            }
        }
        return sb.toString();
    }
    
    String reverse(String u){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < u.length(); i++){
            if(u.charAt(i) == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }
    
    boolean check(String u){
        int c = 0;
        for(int i = 0; i < u.length(); i++){
            if(i == 0 && u.charAt(i)==')')return false;
            if(u.charAt(i) == ')'){ 
                c--;
                if(c < 0) return false;
            }
            else c++;
        }
        return true;
    }
    
}