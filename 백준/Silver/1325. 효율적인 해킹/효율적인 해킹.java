import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, ans;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static TreeMap<Integer, List<Integer>> rank = new TreeMap<>(Collections.reverseOrder());
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());

        // pc graph 초기화
        for(int i = 0; i < N; i++) {

            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pc = Integer.valueOf(st.nextToken());
            int trustedPc = Integer.valueOf(st.nextToken());
            // 단방향 그래프 : trustedPc -> pc

            graph.get(trustedPc - 1).add(pc - 1);

        }


        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            ans = 0;
            dfs(i, 1);
            rank.computeIfAbsent(ans, k -> new ArrayList<>());
            rank.get(ans).add(i + 1);
        }

        for(Integer key : rank.keySet()) {
            for(int node : rank.get(key)) {
                System.out.print(node);
                System.out.print(" ");
            }
            break;
        }
    }

    private static void dfs(int node, int depth) {
        if(visited[node]) return;

        visited[node] = true;
        for(int nextNode : graph.get(node)) {
            if(visited[nextNode]) continue;
            ans+=1;
            dfs(nextNode, depth + 1);
        }

    }

}
