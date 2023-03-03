import java.util.StringTokenizer;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n;
    static int[] numbers;
    static int[][] dp;

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        numbers = new int[n];
        dp = new int[n][2];

        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }//수열 완성


        int max = dp[0][0] = numbers[0];

        for(int i = 1; i < n; i++){
            if(dp[i-1][0] < 0) dp[i][0] = numbers[i];
            else dp[i][0] = dp[i-1][0] + numbers[i];
            if(numbers[i] < 0) dp[i][1] = dp[i-1][0];

            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        bw.write(String.valueOf(max));
        bw.flush();
    }
}
