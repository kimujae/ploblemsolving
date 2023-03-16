import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n , m, ans, destCity;
    static StringTokenizer st;
    static int[][] dp;
    static int[] co;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2){
            if(o1[1] == o2[1])return o1[0] - o2[0];
            return o1[1] - o2[1];
        }
    });

    public static void main(String[] args)throws IOException{
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        co = new int[n+1];
        dp = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];


        Arrays.fill(co, Integer.MAX_VALUE);


        for(int i = 0; i < n+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new int[]{dest, cost});
            //co[dest] = cost;
        }//출발도시, 도착도시, 비용 입력 완료

        st = new StringTokenizer(br.readLine());
        djikstra(Integer.parseInt(st.nextToken()));
        System.out.println(co[Integer.parseInt(st.nextToken())]);
    }

    static void djikstra(int start) {
        co[start] = 0;
        pq.add(new int[]{start, 0});
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int current = node[0];
            int dist = node[1];

            if(co[current] < dist) continue;
            for(int i = 0; i < graph.get(current).size(); i++){
                int next = graph.get(current).get(i)[0];
                int next_dist = dist + graph.get(current).get(i)[1];

                if(co[next] > next_dist) {
                    co[next] = next_dist;
                    pq.add(new int[]{next, next_dist});
                }
            }
        }
    }

}
