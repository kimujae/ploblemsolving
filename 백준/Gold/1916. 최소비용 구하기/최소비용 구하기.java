import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    private static int N, M, s, e;
    private static int[] d;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        d = new int[N+1];

        for(int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new int[]{end, cost});
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());


        Arrays.fill(d, Integer.MAX_VALUE);
//        for(int i = 0; i < graph.get(s).size(); i++) {
//            int[] info = graph.get(s).get(i);
//            int node = info[0];
//            int cost = info[1];
//
//            d[node] = cost;
//        }
        d[s] = 0;

        dijkstra(s);
        System.out.print(d[e]);
    }


    public static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.add(new int[]{start, 0});
        while(!pq.isEmpty()) {
            int[] n = pq.poll();
            int now = n[0];
            int dist = n[1];

            if(d[now] < dist) continue;
            for(int i = 0; i < graph.get(now).size(); i++) {
                int[] ne = graph.get(now).get(i);
                int next = ne[0];
                int cost = ne[1];

                if(dist + cost < d[next]) {
                    d[next] = dist + cost;
                    pq.add(new int[]{next, dist + cost});
                }
            }
        }
    }
}
