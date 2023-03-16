import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n , m;
    static StringTokenizer st;
    static int[] dp;
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
        dp = new int[n+1];
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
        int dest = Integer.parseInt(st.nextToken());

        /*
        i 는 dest부터 시작해서 dp배열 역추적한다.
        종료조건은 i == prev 일때 빠져나온다.
        stack에 쌓아두고 size() 출력
        stack.pop() 출력
         */
        int city = dest;
        Stack<Integer> stack = new Stack<>();
        stack.push(city);
        while(dp[city] != city){
            stack.push(dp[city]);
            city = dp[city];
        }

        sb.append(co[dest]).append('\n');
        sb.append(stack.size()).append('\n');
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        
        System.out.print(sb);
    }

    static void djikstra(int start) {
        co[start] = 0;
        dp[start]  = start;
        pq.add(new int[]{start, 0});
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int current = node[0];
            int dist = node[1];

            if(co[current] < dist) continue;
            for(int i = 0; i < graph.get(current).size(); i++){
                //인접노드를 모두 파악한다.
                int next = graph.get(current).get(i)[0];
                int next_dist = dist + graph.get(current).get(i)[1];

                if(co[next] > next_dist) {
                    co[next] = next_dist;
                    dp[next] = current;
                    pq.add(new int[]{next, next_dist});
                }
            }
        }
    }

}
