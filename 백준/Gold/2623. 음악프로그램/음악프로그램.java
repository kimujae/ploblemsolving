import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static PriorityQueue<Integer> q = new PriorityQueue<>();
    static ArrayList<ArrayList<Integer>> problem = new ArrayList<>();
    static int N, M, complete;
    static int[] indegree;

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree= new int[N+1];

        for(int i = 0;  i < N+1; i++){
            problem.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = 0;

            for(int j = 0; j < count-1; j++){
                second = Integer.parseInt(st.nextToken());
                problem.get(first).add(second);
                first = second;
                indegree[second]++;
            }
        }

        for(int i = 1; i < N+1; i++){
            if(indegree[i] == 0) q.add(i);
        }
        topologySort();
        if(complete == N) System.out.print(sb);
        else {
        //sb.setLength(0);
        System.out.print(0);
        }

    }

    static void topologySort(){
        while(!q.isEmpty()){
            int now = q.poll();
            complete++;
            sb.append(now).append('\n');


            for(int i = 0; i < problem.get(now).size(); i++){
                int next = problem.get(now).get(i);

                if(--indegree[next] == 0) q.add(next);
            }
        }
    }


}
/*
6 3
3 1 4 3
4 6 2 5 4
2 3 2
 */