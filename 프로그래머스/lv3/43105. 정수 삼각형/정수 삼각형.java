import java.util.*;
class Solution {
    private int[][] dp;
    public int solution(int[][] triangle) {
        dp = new int[triangle.length][triangle.length];
        for(int i = 0; i < triangle.length; i++) {
            for(int j  = 0; j < triangle.length; j++) {
                if(i == triangle.length - 1) dp[i][j] = triangle[i][j];
                else dp[i][j] = -1;
            }
        }
        
        dp(0, 0, triangle);
        return dp[0][0];
    }
    
    public int dp(int depth, int breadth, int[][] triangle) {
        if(depth == triangle.length - 1 || dp[depth][breadth] != -1) return dp[depth][breadth];
        
        return dp[depth][breadth] = triangle[depth][breadth] +  
            Math.max(dp(depth + 1, breadth, triangle), dp(depth + 1, breadth + 1, triangle));
    }
}