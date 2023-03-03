import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    static int N, max, index;
    static int[] num_seq;
    static int[] dp;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        num_seq = new int[N];
        st = new StringTokenizer(br.readLine());
        dp = new int[N];
        
        for(int i = 0; i < N; i++){
            num_seq[i] =  Integer.parseInt(st.nextToken());
        }//수열완성
        
        
        for(int i = 0; i < N; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(num_seq[i] > num_seq[j])
                    dp[i] = Math.max(dp[i], dp[j] +1);
            }
            max = Math.max(max, dp[i]);
        }
        
        index = max;
        boolean[] visited = new boolean[index];
        for(int i = N-1; i >= 0; i--){
            if(!visited[index-1] && dp[i] == index) {
                pq.add(num_seq[i]);
                index--;
            }
            if(index == 0) break;
        }
        
        bw.write(String.valueOf(max));
        bw.write('\n');
        while(!pq.isEmpty()){
            bw.write(String.valueOf(pq.poll()));
            bw.write(" ");
        }
        bw.flush();
    }
}
