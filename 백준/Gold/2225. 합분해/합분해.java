import java.io.*;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static long[][] dp;
    //long 타입 선언 조건에 대해 공부하자.
    //%100000000 이라 답이 10억이내로 나오지만 dp배열 내에선 더 큰 수가 들어 올 수 있다.

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        initArray();

        for(int i = 0; i < N+1; i++){
            for(int j = 1; j < K; j++){
                for(int x = 0; x <= i; x++){
                    dp[i][j] += dp[x][j-1] % 1000000000;
                }
            }
        }

        bw.write(String.valueOf(dp[N][K-1]% 1000000000));
        bw.flush();
    }


    static void initArray(){
        dp = new long[N+1][K];
        for(int i = 0 ; i < N+1; i++) {
            dp[i][0] = 1;
        }
    }
}
