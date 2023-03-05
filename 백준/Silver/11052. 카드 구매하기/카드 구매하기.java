import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dp;
    static int N , max;
    static int[] cost;

    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());
        cost = new int[N];
        dp = new int[N+1];

        for(int i = 0; i < N; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }//카드팩 가격 완성

        for(int i = 1; i < N+1; i++){
            max = cost[i-1];
            for(int j = 1; j <= i/2 ; j++){
                max = Math.max(max, dp[i - j] + dp[j]);
            }
            dp[i] = max;
        }
        System.out.print(dp[N]);
    }
}