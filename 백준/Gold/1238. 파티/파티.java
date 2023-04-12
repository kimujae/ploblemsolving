import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    다익스트라의 핵심은 1:N 노드의 최단경로를 알 수 있다는 점이다.
    기준 : X번 마을

    단방향 경로이므로, N개의 노드에서 x번 마을로 오는 최단경로를 알 수 있는 것은 역방향 경로 저장
    x번 마을에서 N개의 노드로 가는 최단경로는 정방향 경로 저장
    총 2번의 다익스트라를 수행하여 최대합을 갖는 노드를 반환
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X;
    static int[] dist, rev_dist;
    static ArrayList<ArrayList<int[]>> rev_path = new ArrayList<>();
    static ArrayList<ArrayList<int[]>> path = new ArrayList<>();


    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        dist = new int[N+1];
        rev_dist = new int[N+1];


        for(int i = 0; i< N+1; i++){
            rev_path.add(new ArrayList<>());
            path.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            rev_path.get(start).add(new int[]{end, dist});
            path.get(end).add(new int[]{start, dist});
        }

        Arrays.fill(rev_dist, Integer.MAX_VALUE);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = rev_dist[X] = 0;

        for(int i = 0; i <rev_path.get(X).size(); i++){
            int[] node = rev_path.get(X).get(i);
            int town = node[0];
            int d = node[1];

            rev_dist[town] = d;
        }

        for(int i = 0; i <path.get(X).size(); i++){
            int[] node = path.get(X).get(i);
            int town = node[0];
            int d = node[1];

            dist[town] = d;
        }

        djikstra(rev_dist, rev_path);
        djikstra(dist, path);

        int ans = 0;
        for(int i = 0; i < N+1; i++){
            int in = rev_dist[i];
            int out = dist[i];
            ans = Math.max(ans, in+out);
        }
        System.out.print(ans);
    }

    static void djikstra(int[] distance, ArrayList<ArrayList<int[]>> path){
        Queue<int[]> q  = new LinkedList<>();
        q.add(new int[]{X, 0});

        while(!q.isEmpty()){
            int[] now = q.poll();
            int n = now[0];
            int d = now[1];

            if(d > distance[n])
                continue;

            for(int i = 0; i < path.get(n).size(); i++){
                int[] next = path.get(n).get(i);
                int nextN = next[0];
                int nextD = next[1];


                if(d + nextD > distance[nextN])
                    continue;

                distance[nextN] = d + nextD;
                q.add(new int[]{nextN, d+nextD});
            }
        }

    }
}
