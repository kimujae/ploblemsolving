import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2){
            return o1[0] - o2[0];
        }
    });
    public static void main(String[] args)throws IOException {
        int tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc; t++){
            int n = Integer.parseInt(br.readLine());
            int ans = n;

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int s1 = Integer.parseInt(st.nextToken());
                int s2 = Integer.parseInt(st.nextToken());

                pq.add(new int[]{s1, s2});
            }

            int[] now = pq.poll();
            int prev = now[1];
            while(!pq.isEmpty()){
                if(pq.peek()[1] > prev) ans--;
                else prev = pq.peek()[1];

                pq.poll();
            }
            System.out.println(ans);
        }
    }
}
