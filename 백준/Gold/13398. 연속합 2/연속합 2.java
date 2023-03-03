import java.util.StringTokenizer;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n;
    static int[] numbers;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        dp = new int[n][2];
        numbers = new int[n];

        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }//수열 완성


        dp[0][0] = numbers[0];
        dp[0][1] = 0;

        int max = numbers[0];

        for(int i = 1; i < n; i++){
            if(dp[i-1][0] < 0) dp[i][0] = numbers[i];
            else dp[i][0] = dp[i-1][0] + numbers[i];
            dp[i][1] = Math.max(dp[i-1][1] + numbers[i] , dp[i-1][0]);
            max = Math.max(dp[i][0] , Math.max(max, dp[i][1]));
        }
        
        bw.write(String.valueOf(max));
        bw.flush();
    }
}