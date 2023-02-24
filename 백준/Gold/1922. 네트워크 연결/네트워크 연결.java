import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //우선순위큐에 가중치(비용) 오름차순(== 최소비용) 정렬
    //union-find를 통해 cycle이 생기지 않는 최소 비용 트리 완성
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[2] - o2[2];
        }
    });
    //12: 25~  12: 38
    static int N, M, cost;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        initParent();

        for(int input = 0; input < M; input++){
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }// 우선순위큐 완성

        bw.write(String.valueOf(computeMinCost()));
        bw.flush();
    }



    static int computeMinCost(){
        while(!pq.isEmpty()){
            int[] graph = pq.poll();

            if(find(graph[0]) == find(graph[1]))
                //서로소가 아닌경우 (같은 부모노드를 가진 경우)
                continue;

            union(graph[0], graph[1]);
            cost += graph[2];
        }
        return cost;
    }

    static int find(int x){
        //노드의 루트노드를 찾는 함수
        if(parent[x] == x) return x;
        else{
            int y = find(parent[x]);
            parent[x] = y;
            return y;
        }
    }


    static void union(int x, int y){
        x= find(x);
        y = find(y);

        if(x != y){
            parent[x] = y;
        }
    }

    static void initParent(){
        for(int root = 0; root < N+1; root++){
            parent[root] = root;
        }
    }


}
