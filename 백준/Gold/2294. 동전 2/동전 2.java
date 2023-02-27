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
        min = new int[k+1];

        for(int i = 0; i < n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }//coin배열 완성


        Arrays.fill(min, 10001);
        min[0] = 0;

        for(int i = 0; i < n; i++){
            int coin = coins[i];
            for(int j = coin; j < k+1; j++){
                min[j] = Math.min(min[j -coin] + 1, min[j]);
            }
        }

        if(min[k] >= 10001) bw.write(String.valueOf(-1));
        else bw.write(String.valueOf(min[k]));
        bw.flush();
    }
}
