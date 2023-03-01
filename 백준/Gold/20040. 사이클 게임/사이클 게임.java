import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        makeSet();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            if (!union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) && (ans == 0)) {
                ans = i+1;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
    }
    static void makeSet(){
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i = 1; i < n+1; i++){
            parent[i] = i;
        }
    }
    static int find(int x){
        if(parent[x] == x) return x;
        int y = find(parent[x]);
        return y ;
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y) {
            if(rank[x] > rank[y] || rank[x] == rank[y]) {
                parent[y] = x;
                rank[x] ++;
            } else{
                parent[x] = y;
                rank[y]++;
            }
            return true;
        }
        return false;
    }
}
