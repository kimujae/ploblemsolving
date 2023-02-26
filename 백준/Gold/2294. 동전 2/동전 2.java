import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, k;
    static int[] memo, min, coins;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n];
        memo = new int[k+1];
        min = new int[k+1];

        for(int i = 0; i < n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }//coin배열 완성


        memo[0] = 1;
        Arrays.fill(min, Integer.MAX_VALUE);
        min[0] = 0;

        for(int i = 0; i < n; i++){
            int coin = coins[i];
            for(int j = coin; j < k+1; j++){
                memo[j] += memo[j - coin];
                if(memo[j - coin] != 0)min[j] = Math.min(min[j -coin]+1, min[j]);
            }
        }

        if(min[k] == Integer.MAX_VALUE) bw.write(String.valueOf(-1));
        else bw.write(String.valueOf(min[k]));
        bw.flush();
    }
}
