import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, M;

    static ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> station = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    static boolean[] visited;

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[M+1];

        for(int i = 0; i < M+1; i++){
            lines.add(new ArrayList<>());
        }

        for(int i = 0; i < N+1; i++){
            station.add(new ArrayList<>());
        }



        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < K; j++){
                int sta = Integer.parseInt(st.nextToken());
                lines.get(i).add(sta);
                station.get(sta).add(i);
            }
        }// 역 배열 입력 완료


        int ans = bfs(1, N);
        System.out.print(ans);
    }


    static int bfs(int start, int end){
        q.add(new int[]{start, 1});
        boolean[] sVisited = new boolean[N+1];


        while(!q.isEmpty()){
            int[] node = q.poll();
            int now = node[0];
            int nowDist = node[1];
            if (now == end) return nowDist;


            for (int i = 0; i < station.get(now).size(); i++) {
                int line = station.get(now).get(i);

                if(visited[line])
                    continue;

                visited[line]= true;
                for(int j = 0; j < lines.get(line).size(); j++) {
                    int next = lines.get(line).get(j);
                    int dist = nowDist;
                    
                    if(sVisited[next])
                        continue;

                    q.add(new int[]{next,dist+1});
                    sVisited[next]= true;
                }
            }
        }
        return -1;
    }
}
