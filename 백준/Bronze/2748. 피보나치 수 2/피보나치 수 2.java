import java.io.*;
import java.util.Arrays;
public class Main {
    static int n;
    static long[] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new long[n+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;


        bw.write(String.valueOf(dp(n)));
        bw.flush();
    }


    static long dp(int number){
        if(number == 1 || number == 0) return dp[number];
        //베이스

        if(dp[number] != -1) return dp[number];
        //메모이제이션

        //리턴로직
        return dp[number] = dp(number-1) + dp(number-2);
    }
}
