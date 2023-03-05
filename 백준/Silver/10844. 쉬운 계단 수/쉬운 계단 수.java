import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[][] dp;
    static int n;
    static long ans;

    public static void main(String[] args)throws IOException{
        n = Integer.parseInt(br.readLine());
        dp = new long[n][10];

        for(int i = 0; i < 10; i++){
            dp[0][i] = 1;
        }
        for(int i = 1; i < 10; i++){
            ans = (ans +  dp(n-1, i)) % 1000000000;
        }
        
        System.out.print(ans % 1000000000);
    }


    static long dp(int len, int num){
        if(len == 0 || dp[len][num] != 0) return dp[len][num];

        if(num == 0) dp[len][num] = dp(len-1, num + 1);
        else if(num == 9) dp[len][num] = dp(len-1 , num - 1);
        else dp[len][num] = dp(len-1, num-1) + dp(len-1, num+1);
        return dp[len][num] % 1000000000;
    }
}
