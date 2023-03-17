import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static long ans;
    static PriorityQueue<int[]> problem = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2){
            if(o1[0] == o2[0]) return o2[1] - o1[1];
            return o2[0] - o1[0];
        }
    });

    static PriorityQueue<Integer> ramen = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            problem.add(new int[]{Integer.parseInt(st.nextToken()) ,Integer.parseInt(st.nextToken())});
        }//문제 정보 입력 완료


        int max = problem.peek()[0];
        while(!problem.isEmpty() || max > 0){
            while(!problem.isEmpty() && max == problem.peek()[0]){
                ramen.add(problem.poll()[1]);
                if(problem.isEmpty()) break;
            }
            if(!ramen.isEmpty())ans += ramen.poll();
            max--;
        }

        System.out.print(ans);
    }
}
