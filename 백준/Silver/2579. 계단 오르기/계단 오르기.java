import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] stair;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        stair = new int[N+1];
        dp = new int[N+1];

        for(int i = 1 ; i <= N; i++){
            stair[i] = Integer.parseInt(br.readLine());
        }// 계단 입력 완i료
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = stair[1];
        if(N >= 2) {
            dp[2] = stair[1] + stair[2];
        }

        System.out.println(dp(N));
    }

    static int dp(int idx){// 탐색 계단 높이
        if(dp[idx] != -1) return dp[idx];
        return dp[idx] = Math.max(dp(idx - 2), stair[idx - 1] + dp(idx - 3)) + stair[idx];
    }
}