class Solution {
    public StringBuilder sb = new StringBuilder();
    public String solution(int n, int t, int m, int p) {
        String answer = solve(n, t, m, p);
        sb = new StringBuilder();
        
        for(int i = 0; i < answer.length(); i++)
            if(i%m == p-1) sb.append(answer.charAt(i));
        
        return sb.toString();
    }
    
    
   String solve(int n, int t, int m, int p){
       StringBuilder ans = new StringBuilder();
        a : for(int i = 0; i <= t*m; i++){
                String target = Integer.toString(i, n);
                for(int j = 0; j < target.length(); j++){
                    if(target.charAt(j) - 'a' >= 0) ans.append((char)('A' + (target.charAt(j) - 'a')));
                    else ans.append(target.charAt(j));
                    if(ans.length() >= t*m) break a;
                    }
        }
        return ans.toString();
    }
}