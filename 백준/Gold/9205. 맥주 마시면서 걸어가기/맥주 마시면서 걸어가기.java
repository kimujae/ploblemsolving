import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.*;

public class Main {
    static boolean[] visited;
    static Queue<int[]> queue = new LinkedList<>();
    static int t, n;
    static int[] house, festival;
    static int[][] convin;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        for(int test = 0 ; test < t; test++) {
            input();
            visited = new boolean[n+1];
            queue.clear();
            //큐 삭제가 중요하다.
            if(bfs(house[0],house[1])) bw.write("happy"+"\n");
            else bw.write("sad"+"\n");
        }
        bw.flush();
    }

    static boolean bfs(int nowx, int nowy){
        visited[0] = true;
        queue.add(new int[]{nowx, nowy});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            if(Math.abs(nowX - festival[0]) + Math.abs(nowY - festival[1]) <= 1000)
                return true;

            for(int i = 0; i < n; i++){
                if(visited[i+1])
                    //방문한 곳이면 pass
                    continue;

                if(Math.abs(nowX - convin[i][0]) + Math.abs(nowY - convin[i][1]) > 1000)
                    //범위 내 있지 않은 편의점 pass
                    continue;

                visited[i+1] = true;
                queue.add(new int[]{convin[i][0], convin[i][1]});
            }
        }
        return false;
    }

    static void input() throws IOException{
        n = Integer.parseInt(br.readLine());
        convin = new int[n][2];

        st = new StringTokenizer(br.readLine());
        house = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        //상근이 위치 정보

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            convin[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }//편의점 위치 정보

        st = new StringTokenizer(br.readLine());
        festival = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        //페스티벌 위치 정보
    }
}
