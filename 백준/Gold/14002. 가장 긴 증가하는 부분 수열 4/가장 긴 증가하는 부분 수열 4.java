import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    static int N, answer, index;
    static int[] num_seq;
    static int[][] dp;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        num_seq = new int[N];
        st = new StringTokenizer(br.readLine());
        dp = new int[N][2];
        for(int  i = 0; i < N; i ++){
            dp[i][1]  = - 1;
        }


        for(int i = 0; i < N; i++){
            num_seq[i] =  Integer.parseInt(st.nextToken());
        }//수열완성

        for(int i = 0; i < N; i++){
            dp[i][0] = 1;
            for(int j = 0; j < i; j++){
                if(num_seq[i] > num_seq[j])
                    dp[i][1] = (dp[j][0] + 1 == (dp[i][0] = Math.max(dp[i][0], dp[j][0] +1))) ? j : dp[i][1];
            }
            index = (dp[i][0] == (answer = Math.max(answer, dp[i][0])) ? i : index);
        }


        pq.add(num_seq[index]);
        while(dp[index][1] != -1){
            pq.add(num_seq[dp[index][1]]);
            index = dp[index][1];
        }

        bw.write(String.valueOf(answer));
        bw.write('\n');
        while(!pq.isEmpty()) {
            bw.write(String.valueOf(pq.poll()));
            bw.write(" ");
        }
        bw.flush();
    }
}
