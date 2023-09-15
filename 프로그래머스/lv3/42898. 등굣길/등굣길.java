import java.util.*;
class Solution {
    private int[][] dp;
    private int[] cx = {1, 0};
    private int[] cy = {0, 1};
    
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[m][n];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], 0);
        }
        for(int i = 0; i < puddles.length; i++) {
            dp[puddles[i][0] - 1][puddles[i][1] - 1] = -2;
        }
        
        return dp(0, 0, puddles, m, n);
    }
    
    public int dp(int x, int y, int[][] puddles, int m, int n) {
        if(x == m - 1 && y == n - 1) return 1;
        
        if(dp[x][y] == -2) return 0;
        if(dp[x][y] != 0) return dp[x][y];
        
        for(int i = 0; i < 2; i++) {
            int nx = x + cx[i];
            int ny = y + cy[i];
            
            if(nx > m - 1 || ny > n - 1) continue;
            dp[x][y] = (dp[x][y] + dp(nx, ny, puddles, m, n)) % 1000000007;
        }
        return dp[x][y];
    }
}