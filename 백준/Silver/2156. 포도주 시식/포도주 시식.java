import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] alchol;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        alchol = new int[n];
        dp = new int[n][2][3];

        for (int i = 0; i < n; i++) {
            alchol[i] = Integer.parseInt(br.readLine());
        }//포도주 완성


        init();
        System.out.print(Math.max(dp(n-1, 0, 0), Math.max(dp(n-1, 1,0), Math.max(dp(n-1, 0, 1), Math.max(dp(n-1, 1, 1), dp(n-1, 0, 2))))));
    }

    //파라미터 : 현재 탐색 위치, 선택여부, 누적잔
    static int dp(int idx, int select, int acc) {
        if (idx == 0 || dp[idx][select][acc] != -1) return dp[idx][select][acc];
        if (select == 0) {
            if (acc == 0)
                dp[idx][select][acc] = Math.max(dp(idx - 1, 0, 0), Math.max(dp(idx - 1, 0, 1), dp(idx - 1, 0, 2)));
            else if (acc == 1) dp[idx][select][acc] = dp(idx - 1, 1, 0);
            else dp[idx][select][acc] = dp(idx - 1, 1, 1);
        } else {
            if (acc == 0)
                dp[idx][select][acc] = Math.max(dp(idx - 1, 0, 0), Math.max(dp(idx - 1, 0, 1), dp(idx - 1, 0, 2))) + alchol[idx];
            else dp[idx][select][acc] = dp(idx - 1, 1, 0) + alchol[idx];
        }
        return dp[idx][select][acc];
    }

    static void init(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k <3; k++){
                    dp[i][j][k] = -1;
                }
            }
        }

        dp[0][1][0] = alchol[0];
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        dp[0][1][1] = 0;
        dp[0][0][2] = 0;
        dp[0][1][2] = 0;
    }
}
