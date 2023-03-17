import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.*;
import java.util.PriorityQueue;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb  = new StringBuilder();
    static int N, K;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[]o1, int[] o2){
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        }
    });

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        char[] number = br.readLine().toCharArray();


        for(int i = 0; i <= K; i++){
            pq.add(new int[]{number[i] - 48, i});
        }// 우선순위 큐 초기값


        int[] num = pq.poll();
        sb.append(num[0]);
        for(int i = K+1; i < N; i++){
            pq.add(new int[]{number[i] - 48, i});
            while(num[1] > pq.peek()[1]){
                pq.poll();
            }
            num = pq.poll();
            sb.append(num[0]);
        }

        System.out.print(sb);
    }
}
