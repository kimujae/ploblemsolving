import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main{
    static int[] parent;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int N;
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());
        visited= new boolean[N+1];
        parent = new int[N+1];

        for(int input = 0; input <= N; input++){
            // graph에 입력받을 인접노드+1(인덱스 0은 활용x) 만큼 배열리스트 추가
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }

        dfs(1);
        for(int i = 2; i < N+1; i++){
            bw.write(String.valueOf(parent[i]));
            bw.write('\n');
        }
        bw.flush();
    }
    static void dfs(int x){
        visited[x] = true;


        for(int i = 0; i < tree.get(x).size(); i++){
            int node = tree.get(x).get(i);

            if(visited[node])
                continue;

            parent[node] = x;
            dfs(node);
        }
    }

}
