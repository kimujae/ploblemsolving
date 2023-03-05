import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans, r, g, b;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];


        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }//cost 완성

        initTable();
        r = dp(0,0,0);
        initTable();
        g =dp(0, 1, 1);
        initTable();
        b = dp(0, 2, 2);

        ans = Math.min(r, Math.min(g, b));
        System.out.println(ans);
    }

    static int dp(int depth, int color, int firstHouseColor) {
        if (depth == N-1) return dp[depth][color];
        if (dp[depth][color] != 0) return dp[depth][color];
        if (depth < N - 2) {
            if (color == 0) dp[depth][color] = cost[depth][color] + Math.min(dp(depth + 1,1,firstHouseColor), dp(depth + 1, 2 ,firstHouseColor));
            else if (color == 1) dp[depth][color] = cost[depth][color] + Math.min(dp(depth + 1,0,firstHouseColor), dp(depth + 1, 2 ,firstHouseColor));
            else if (color == 2) dp[depth][color] = cost[depth][color] + Math.min(dp(depth + 1,0,firstHouseColor), dp(depth + 1, 1 ,firstHouseColor));
        } else{
            if(firstHouseColor == 0) {
                if (color == 0) dp[depth][color] = cost[depth][color] + Math.min(dp(depth + 1,1,firstHouseColor), dp(depth + 1, 2 ,firstHouseColor));
                else if (color == 1) dp[depth][color] = cost[depth][color] + dp(depth + 1, 2 ,firstHouseColor);
                else if (color == 2) dp[depth][color] = cost[depth][color] + dp(depth + 1, 1 ,firstHouseColor);
            }
            else if(firstHouseColor == 1) {
                if (color == 1) dp[depth][color] = cost[depth][color] + Math.min(dp(depth + 1,0,firstHouseColor), dp(depth + 1, 2 ,firstHouseColor));
                else if (color == 0) dp[depth][color] = cost[depth][color] + dp(depth + 1, 2 ,firstHouseColor);
                else if (color == 2) dp[depth][color] = cost[depth][color] + dp(depth + 1, 0 ,firstHouseColor);
            }
            else if(firstHouseColor == 2) {
                if (color == 2) dp[depth][color] = cost[depth][color] + Math.min(dp(depth + 1,0,firstHouseColor), dp(depth + 1, 1 ,firstHouseColor));
                else if (color == 0) dp[depth][color] = cost[depth][color] + dp(depth + 1, 1 ,firstHouseColor);
                else if (color == 1) dp[depth][color] = cost[depth][color] + dp(depth + 1, 0 ,firstHouseColor);
            }
        }
        return dp[depth][color];
    }

    static void initTable(){
        dp = new int[N][3];
        dp[N-1][0] = cost[N-1][0];
        dp[N-1][1] = cost[N-1][1];
        dp[N-1][2] = cost[N-1][2];
    }

}
