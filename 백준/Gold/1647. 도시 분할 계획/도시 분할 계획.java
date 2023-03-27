import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<ArrayList<Integer>> town = new ArrayList<>();
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2){
            return o1[2] - o2[2]; //cost 기준 min-heap
        }
    });
    static int[] parent;
    static int N, M, maxCost, ans, link;
    public static void main(String[] args)throws IOException {
        //st = new StringTokenizer(br.readLine());
        N = readInt();
        M = readInt();


        for(int i = 0; i < M; i++){
            //st = new StringTokenizer(br.readLine());
            int house1 = readInt();
            int house2 = readInt();
            int cost = readInt();

            pq.add(new int[]{house1, house2, cost});
        }

        makeSet();
        for(int i = 0; i < M; i++){
            int[] rel = pq.poll();
            int house1 = rel[0];
            int house2 = rel[1];
            int cost = rel[2];

            if(find(house1) != find(house2)){
                union(house1, house2);
                ans += cost;
                //maxCost = Math.max(maxCost, cost);
                link++;
            }
            if(link == N-2) break;
        }


        System.out.print(ans);
    }

    static void makeSet(){
        parent = new int[N+1];
        for(int i = 1; i < N+1; i++){
            parent[i] = i;
        }
    }
    static int find(int x){
        if(x == parent[x]) return x;

        int y = find(parent[x]);
        parent[x] = y;
        return y;
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            if(x > y) parent[y] = x;
            else parent[x] = y;
        }
    }



    static int readInt() throws IOException {
        int val = 0;
        do {
            int c = System.in.read();
            if (c == ' ' || c == '\n') break;
            val = 10*val + c-48;
        } while (true);
        return val;
    }
}
