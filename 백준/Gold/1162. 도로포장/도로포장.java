import java.util.*;
import java.io.*;

public class Main {
    static class Road{
        int city;
        long cost;
        int k;

        public Road(int city, long cost, int k){
            this.city = city;
            this.cost = cost;
            this.k = k;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static long[][] dp;
    static PriorityQueue<Road> city = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[N+1][K+1];//각 도시의 경로, 포장하거나 하지 않거나.

        for(int i = 0; i < N+1; i++){
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new int[]{dest, cost});
            graph.get(dest).add(new int[]{start, cost});
        }

        djikstra(1);
        long ans = dp[N][0];
        for(int i = 1; i < K+1; i++){
            ans = Math.min(dp[N][i], ans);
        }
        System.out.print(ans);
    }

    static void djikstra(int start){
        dp[start][0] = 0;
        city.add(new Road(start, 0, 0));

        while(!city.isEmpty()){
            Road curr = city.poll();
            int curCity = curr.city;
            long curCost = curr.cost;
            int curK = curr.k;

            if(dp[(int)curCity][(int)curK] < curCost) continue;
            for(int i = 0; i < graph.get(curCity).size(); i++){
                int[] next = graph.get(curCity).get(i);
                int nextCity = next[0];
                int nextCost = next[1];

                if(dp[nextCity][curK] > curCost + nextCost) {
                    dp[nextCity][curK] = curCost + nextCost;
                    city.add(new Road(nextCity, curCost + nextCost, curK));
                }


                if(curK < K && dp[nextCity][curK+1] > curCost){
                        dp[nextCity][curK + 1] = curCost;
                        city.add(new Road(nextCity, curCost, curK + 1));
                }
            }
        }
    }
}
