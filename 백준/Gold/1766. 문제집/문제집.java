import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static PriorityQueue<Integer> q = new PriorityQueue<>();
    static ArrayList<ArrayList<Integer>> problem = new ArrayList<>();
    static int N, M;
    static int[] indegree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree= new int[N+1];
        visited = new boolean[N+1];

        for(int i = 0;  i < N+1; i++){
            problem.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            problem.get(first).add(second);
            indegree[second]++;
        }

        for(int i = 1; i < N+1; i++){
            Collections.sort(problem.get(i));
        }//난이도 오름차순 정렬


        boolean isProblem = true;

        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        topologySort();
        System.out.print(sb);
    }

    static void topologySort(){
        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(' ');

            for (int i = 0; i < problem.get(now).size(); i++) {
                int next = problem.get(now).get(i);


                if (--indegree[next] == 0){
                    q.add(next);
                }
            }
        }
    }
}