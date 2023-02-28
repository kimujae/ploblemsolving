import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    최장 증가 수열 + 최장 감소 수열 의 합 -1 이 max인 idx를 찾는 문제?
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] dp;
    static int[] A;
    static int N, ans;

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        dp = new int[N][2];
        A = new int[N];


        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }// 수열 입력 완료

        //1.최장증가수열 찾기
        for(int i = 0; i < N; i++){
            dp[i][0] = 1;
            for(int j = 0; j < i; j++){
                if(A[i] > A[j]) dp[i][0] = Math.max(dp[i][0] , dp[j][0] + 1);
            }
        }//최장증가 수열 dp완성


        //2.역최장증가수열 탐색 하면서 max값 탐색
        for(int i = N-1; i >= 0; i-- ){
            dp[i][1] = 1;
            for(int j = N-1; j > i; j--){
                if(A[i] > A[j]) dp[i][1] = Math.max(dp[i][1] , dp[j][1] + 1);
            }
            //dp[i][1]완성
            ans = Math.max(ans, dp[i][1] + dp[i][0] - 1);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
