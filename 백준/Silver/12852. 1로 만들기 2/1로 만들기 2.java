import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] dp;

    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());


        tablation();
        bw.write(String.valueOf(dp[N]));
        bw.write('\n');
        reverseTracking(N);
        bw.flush();
    }

    static void tablation(){
        dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for(int i = 1; i < N+1; i++){
            if(i*3 <= N && dp[i*3] > dp[i]+1) dp[i*3] = dp[i] + 1;
            if(i*2 <= N && dp[i*2] > dp[i]+1) dp[i*2] = dp[i] + 1;
            if(i+1 <= N && dp[i+1] > dp[i]+1) dp[i+1] = dp[i] + 1;
        }//tablation 완료
    }

    static void reverseTracking(int n)throws IOException {
        int min;

        while(n != 0){
            bw.write(String.valueOf(n));
            bw.write(String.valueOf(" "));

            if(n % 6 == 0){
                min = Math.min(dp[n-1], Math.min(dp[n/2], dp[n/3]));
                if(min == dp[n-1])n = n-1;
                else if(min == dp[n/2]) n = n/2;
                else n = n/3;
            }
            else if(n % 3 == 0) {
                min = Math.min(dp[n-1], dp[n/3]);
                if(min == dp[n-1])n = n-1;
                else n = n/3;
            }
            else if(n % 2 == 0) {
                min = Math.min(dp[n-1], dp[n/2]);
                if(min == dp[n-1]) n = n-1;
                else n = n/2;
            }
            else n = n-1;
        }
    }
}
