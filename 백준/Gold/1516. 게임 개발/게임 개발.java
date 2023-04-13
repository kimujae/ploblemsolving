import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] indegree, time, ans;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> build = new ArrayList<>();

    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        indegree = new int[N+1];
        time = new int[N+1];
        ans = new int[N+1];

        for(int i = 0; i < N+1; i++){
            build.add(new ArrayList<>());
        }

        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if(num == -1) break;
                build.get(num).add(i);
                indegree[i]++;
            }
        }// 인접 리스트 입력 완료

        Queue<Integer> q = new LinkedList<>();
        visited =new boolean[N+1];
        for(int i = 1; i < N+1; i++){
            if(indegree[i] == 0) {
                q.add(i);
                visited[i] = true;
                ans[i] = time[i];
            }
        }

        topsort(q);
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t < N+1; t++){
            sb.append(ans[t]).append('\n');
        }
        System.out.print(sb);
    }
    static void topsort(Queue<Integer> q){
        while(!q.isEmpty()){
            int now = q.poll();

            for(int i = 0; i < build.get(now).size(); i++){
                int next = build.get(now).get(i);

                if(visited[next])
                    continue;

                ans[next] = Math.max(ans[next], ans[now] + time[next]);
                if(--indegree[next] == 0){
                    q.add(next);
                    visited[next]= true;
                }
            }
        }
    }
}
