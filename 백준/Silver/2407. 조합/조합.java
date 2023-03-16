import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static long ans;
    static boolean[] visited;
    static BigInteger[][] dp;

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new BigInteger[N+1][N+1];
        dp(N);
        System.out.print(dp[N][M]);
    }

    static void dp(int num){
        for(int i = 1; i <= num; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0|| j == i) dp[i][j] = new BigInteger("1");
                else dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
            }
        }
    }
}