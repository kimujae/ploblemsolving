import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, ans;
    static int dp[][][];

    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());
        dp = new int[N][10][1<<10];


        for(int num = 1; num < 10; num++){
            int len = 0;
            dp[len][num][1 << num] = 1;
        }

        for(int len = 1; len < N; len++){
            for(int num = 0; num < 10; num++){
                for(int set = 0; set < 1<<10; set++) {
                    int nowSet = set | 1 << num; // 탐색 경우의 수에 원소 추가
                    if (num == 0) dp[len][num][nowSet] = (dp[len][num][nowSet] + dp[len - 1][num + 1][set])% 1000000000;
                    else if (num == 9) dp[len][num][nowSet] = (dp[len][num][nowSet] + dp[len - 1][num - 1][set])% 1000000000;
                    else dp[len][num][nowSet] = (dp[len][num][nowSet] + dp[len - 1][num + 1][set] + dp[len - 1][num - 1][set]) % 1000000000;
                }
            }
        }

        for(int num = 0; num < 10; num++) ans = (ans + dp[N-1][num][(1<<10) - 1])% 1000000000;
        System.out.println(ans);
    }
}
