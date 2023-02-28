import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class Main{
    static int n, answer;
    static int[] dp;
    static int[][] elec_map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        elec_map = new int[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            elec_map[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }// 전기줄 map 완성

        Arrays.sort(elec_map, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        dp = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(!((elec_map[i][0] > elec_map[j][0] && elec_map[i][1] < elec_map[j][1])
                        || (elec_map[i][0] < elec_map[j][0] && elec_map[i][1] > elec_map[j][1])))
                    dp[i] = Math.max(dp[j]+1, dp[i]);
            }
            answer= Math.max(answer, dp[i]);
        }

        System.out.print(n - answer);
    }
}
