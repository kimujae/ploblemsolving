import java.util. ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static ArrayList<ArrayList<Integer>> network = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Queue<Integer> queue = new LinkedList<>();
    static int N, M;
    static boolean[] visited;
    static int[][] value;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        value = new int[N+1][2];

        for(int i = 0; i < N+1; i++){
            network.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            network.get(node1).add(node2);
            //network.get(node2).add(node1);
        }//네트워크 완성


        for(int i = 1; i < N+1; i++){
            visited = new boolean[N+1];
            value[i][0] = i;
            visited[i] = true;
            //bfs(i);
            dfs(i);
        }

        Arrays.sort(value, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o2[1] - o1[1];
            }
        });

        int i =0;
        int num = 0;
        do {
            num = value[i][1];
            int com = value[i][0];
            bw.write(String.valueOf(com)+" ");
            i++;
        } while(i < N+1 && num == value[i][1]);

        bw.flush();
    }

/*
    static void bfs(int x){
        visited[x] = true;
        queue.add(x);

        while(!queue.isEmpty()){
            for(int next : network.get(queue.poll())){
                if(visited[next])
                    continue;

                visited[next] = true;
                value[x][1]++;
                queue.add(next);
            }
        }
    }
*/
    static void dfs(int x){
        for(int next : network.get(x)){
            if(visited[next])
                continue;

            value[next][1]++;
            visited[next] = true;
            dfs(next);
        }
    }
}
