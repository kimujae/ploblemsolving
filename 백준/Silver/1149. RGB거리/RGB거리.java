import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static int[][] memo, cost;
    public static int N;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        memo = new int[N+1][3];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }// cost입력 완료


        memo[1][0] = cost[0][0];
        memo[1][1] = cost[0][1];
        memo[1][2] = cost[0][2];
        Dp(N,0);
        Dp(N,1);
        Dp(N,2);

        System.out.print(Math.min(memo[N][0], Math.min(memo[N][1], memo[N][2])));


    }

    public static int Dp(int number, int color){
        if(memo[number][color] != 0) return memo[number][color];// 베이스 조건
        else if(color == 0)return memo[number][0] = cost[number-1][0] + Math.min(Dp(number-1, 1), Dp(number-1, 2));
        else if(color == 1)return memo[number][1] = cost[number-1][1] + Math.min(Dp(number-1, 0), Dp(number-1, 2));
        else return memo[number][2] = cost[number-1][2] + Math.min(Dp(number-1, 0), Dp(number-1, 1));
        }
}
