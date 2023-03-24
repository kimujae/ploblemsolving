import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dp, line;
    static int N, lis;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        line = new int[N];

        for(int i = 0; i < N; i++){
            line[i] = Integer.parseInt(br.readLine());
        }//줄 입력 완료


        for(int i = 0; i < N; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(line[i] > line[j]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            lis = Math.max(lis, dp[i]);
        }

        System.out.print(N - lis);
    }
}
