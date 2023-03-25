import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, max;
    static boolean[] visited;
    static ArrayList<ArrayList<int[]>> tree = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        dist = new int[n+1];

        for(int i = 0; i < n+1; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            tree.get(node1).add(new int[]{node2, dist});
            //tree.get(node2).add(new int[]{node1, dist});
        }//tree 입력 완료

        dfs(1);
        System.out.print(max);
    }

    static int dfs(int num){
        visited[num] = true;

        for(int i = 0; i < tree.get(num).size();i++){
            int[] next = tree.get(num).get(i);
            int nextNode = next[0];
            int distance = next[1];

            if(visited[nextNode])
                continue;

            int totalDist = distance + dfs(nextNode);
            max = Math.max(max, dist[num] + totalDist);
            dist[num] = Math.max(dist[num], totalDist);
        }
        return dist[num];
    }
}
