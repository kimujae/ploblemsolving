import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static boolean[] visited;
    static PriorityQueue<int[]> pq  = new PriorityQueue<int[]>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if(o1[2] == o2[2])return o1[0] - o2[0];
            else return o1[2] - o2[2];
        }
    });
    static int V, E, ans;
    static int[] parent;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    pq에 가중치 우선순위 저장
    union-find로 탐색
     */

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visited = new boolean[V+1];
        parent = new int[V+1];

        for(int i = 0; i < V+1; i++){
            parent[i] = i;
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }//우선순위큐 완성

        while(!pq.isEmpty()){
            int[] graph = pq.poll();

            if(find(graph[0]) == find(graph[1]))
                continue;

            union(graph[0], graph[1]);
            ans+= graph[2];
        }

        bw.write(String.valueOf(ans));
        bw.flush();
    }


    static int find(int x){
        if(x == parent[x]) return x; // 루트노드
        else{
            int y = find(parent[x]);
            parent[x] = y; // 집합의 루트 노드를 반환
            return y;
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x!=y){
            parent[y] = x;
        }
    }
}
