import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int V, E;
    private static int[] p;
    private static PriorityQueue<int[]> pq =
            new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.add(new int[]{A, B, C});
        }// pq 초기화

        initSet();
        int conn = 0;
        long dist = 0;
        while(!pq.isEmpty()) {
            //if(conn == E - 1) break;

            int[] now = pq.poll();
            int s = now[0];
            int n = now[1];
            int d = now[2];

            if(find(s) == find(n)) continue;
            union(s, n);
            dist += d;
            conn++;
        }

        System.out.print(dist);
    }



    public static void initSet() {
        p = new int[V+1];
        for(int i = 1; i < V+1; i++) {
            p[i] = i;
        }
    }

    public static int find(int x) {
        if(p[x] == x) return p[x];

        int y = find(p[x]);
        p[x] = y;
        return y;
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) p[y] = x;
    }
}
