import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        }
    });

    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static int V, E, K;
    static int[] cost;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        cost = new int[V+1];

        Arrays.fill(cost, Integer.MAX_VALUE);
        for(int i = 0; i <= V; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 출발노드
            int v = Integer.parseInt(st.nextToken()); // 도착노드
            int w = Integer.parseInt(st.nextToken()); // 가중치

            graph.get(u).add(new int[]{v, w});
        }//간선정보 입력 완료

        djikstra(K);
        for(int i = 1; i < V+1; i++){
            if(cost[i] == Integer.MAX_VALUE) sb.append("INF").append('\n');
            else sb.append(cost[i]).append('\n');
        }
        System.out.print(sb);
    }

    static void djikstra(int start){
        cost[start] = 0;
        pq.add(new int[]{start, cost[start]});


        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int dist = curr[1];
            
            if (dist > cost[node]) continue;
            //어떤 경우를 걸러내기 위한 조건문인가?
            /*
            1의 인접노드 {2, 3}
            2의 인접노드 {4}
            3의 인접노드 {4} 
            일 때, 1-2-4 의 가중치보다 1-3-4의 가중치가 낮을 때(이미 cost[4]는 2회 갱신함) 
            pq.poll[1] ==  1-2 가중치 + 2-4가중치 이면 1-3가중치 + 3+4 가중치로 갱신된 값보다 크기때문에 continue 패스조건 
             */
            for (int i = 0; i < graph.get(node).size(); i++) {
                int next = graph.get(node).get(i)[0];
                int next_dist = dist + graph.get(node).get(i)[1];

                if(cost[next] > next_dist){
                    cost[next] = next_dist;
                    pq.add(new int[]{next, next_dist});
                }
            }

        }
    }
}
