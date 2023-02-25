import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] memo, coins;
    static int n, k;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args)throws IOException {
       st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       coins = new int[n];
       memo = new int[k+1];


       for(int i = 0; i < n; i++){
           coins[i] = Integer.parseInt(br.readLine());
       }//동전 배열 완성

        memo[0] = 1;
        for(int i = 0 ; i < n; i++){
            for(int j = coins[i]; j< k+1; j++){
                if(j - coins[i] < 0) continue;
                memo[j] += memo[j - coins[i]];
            }
        }

        bw.write(String.valueOf(memo[k]));
        bw.flush();
    }
}
