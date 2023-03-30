import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, ans;
    static int[][] map;
    static int[] coins;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;
        map = new int[N][N];
        coins = new int[N];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                if(str.charAt(j) == 'T') coins[i] |= 1<<j;
            }
        }// 동전 뒷면 개수 입력 완료

        dfs(0);
        System.out.print(ans);
    }

    static void dfs(int depth){
        if(depth == N){
            int sum =0;
            for(int i = 0; i < N; i++){
                int tail =0;
                for(int j = 0; j < N; j++){
                    if((1 << i & coins[j]) != 0)tail++;
                }
                sum += Math.min(tail, N-tail);
            }
            ans = Math.min(ans, sum);
            return;
        }

        dfs(depth+1); //뒤집지 않거나
        coins[depth] = ~coins[depth];
        dfs(depth+1); //뒤집거나
    }
}