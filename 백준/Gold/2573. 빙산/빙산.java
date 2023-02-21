import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[] changeX = {-1, 0 ,1, 0};
    static int[] changeY = {0, -1, 0, 1};
    static int N, M, year, iceburg;
    static Queue<int[]> queue = new LinkedList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        iceburg = 1;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }//map정보 완성


        while(iceburg != 0) {
            iceburg = 0;
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] != 0) {
                        iceburg++;
                        if(iceburg > 1) break;
                        //bfs(i, j);
                        dfs(i, j);
                    }
                    if(iceburg > 1) break;
                }
                if(iceburg > 1) break;
            }
            if(iceburg > 1) break;
            year++;
        }
        //90000*10 = 최대 900000회 * x 연산

        if(iceburg == 0) bw.write(String.valueOf(0));
        else bw.write(String.valueOf(year));
        bw.flush();
    }


    static void bfs(int X, int Y){
        visited[X][Y] = true;
        melt(X, Y);
        queue.add(new int[]{X, Y});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(map[nextX][nextY] == 0)
                    continue;

                //빙산조각을 만났을 때 녹인다.
                melt(nextX, nextY);
                visited[nextX][nextY] = true;
                queue.add(new int[]{nextX, nextY});
            }
        }
    }

    static void dfs(int X, int Y){
        visited[X][Y] = true;
        melt(X, Y);

        for(int i = 0; i < 4; i++){
            int nextX = X + changeX[i];
            int nextY = Y + changeY[i];

            if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                continue;

            if(visited[nextX][nextY])
                continue;

            if(map[nextX][nextY] == 0)
                continue;

            //빙산조각을 만났을 때 탐색 지속
            dfs(nextX, nextY);
        }
    }

    static void melt(int X, int Y){
        int water = 0;
        for(int i = 0; i < 4; i++){
            int nextX = X + changeX[i];
            int nextY = Y + changeY[i];

            if(!visited[nextX][nextY] && map[nextX][nextY] == 0)
                // 탐색하지 않은 물(빙하가 녹아 물이 된 경우 제외)
                water++;
        }
        if(map[X][Y] - water < 0) map[X][Y] = 0;
        else map[X][Y] -= water;
    }
}
