import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//11:02~11:43
public class Main {
    static int N, cost;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[2] - o2[2];
        }
    });

    static int[][] planetX,planetY, planetZ;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] parent;

    /*
    x, y, z를 오름차순으로 정렬
    n-1 n의 abs(diff)를 구하고 이를 간선 정보에 저장
    x, y, z 간선 정보를 우선순위큐에 저장
    union-find 실행
     */
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        planetX = new int[N][2];
        planetY = new int[N][2];
        planetZ = new int[N][2];
        parent = new int[N+1];

        init();

        for(int input = 0; input < N; input++){
            st = new StringTokenizer(br.readLine());
            planetX[input] = new int[]{input, Integer.parseInt(st.nextToken())};
            planetY[input] = new int[]{input, Integer.parseInt(st.nextToken())};
            planetZ[input] = new int[]{input, Integer.parseInt(st.nextToken())};
        }//행성 좌표 완성


        sort();
    
        for(int i = 0; i < N-1; i++){
            pq.add(new int[]{planetX[i][0], planetX[i+1][0], Math.abs(planetX[i][1] - planetX[i+1][1])});
            pq.add(new int[]{planetY[i][0], planetY[i+1][0], Math.abs(planetY[i][1] - planetY[i+1][1])});
            pq.add(new int[]{planetZ[i][0], planetZ[i+1][0], Math.abs(planetZ[i][1] - planetZ[i+1][1])});
        }
        // 우선순위큐 완성

        while(!pq.isEmpty()){
            int[] graph = pq.poll();

            if(find(graph[0]) == find(graph[1]))
                continue;

            union(graph[0], graph[1]);
            cost += graph[2];
        }

        bw.write(String.valueOf(cost));
        bw.flush();
    }

    static void init(){
        for(int i = 0; i < N+1; i++){
            parent[i] = i;
        }
    }

    static int find(int x){
        if(x == parent[x]) return x;
        else{
            int y = find(parent[x]);
            parent[x] = y;
            return y;
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            parent[x] = y;
        }
    }

    static void sort(){
        Arrays.sort(planetX, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        Arrays.sort(planetY, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Arrays.sort(planetZ, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //x,y,z 정렬
    }
}
