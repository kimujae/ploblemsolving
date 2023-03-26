import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<int[]> queue = new LinkedList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, area, count;
    static boolean[][] visited;
    static int[][] map, dist;
    static int[] changeX = {1, 0, -1, 0};
    static int[] changeY = {0, 1, 0, -1};

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        dist = new int[N][M];


        for(int i = 0; i < N; i++){
            String str= br.readLine();
            for(int j = 0; j < M; j++){
                if(str.charAt(j) == '1'){
                   map[i][j] = 1;
                   dist[i][j] = 1;
                } 
                else map[i][j] = 0;
            }
        }// map 정보 입력 완료

        for(int i = 0; i < N; i++){
            for(int j =0; j < M; j++){
                if(map[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    area++; // 영역개수
                    count = 0; //영역넓이
                    getAreaCount(i, j);
                    bfs();
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j =0; j < M; j++){
                if(map[i][j] == 0) sb.append("0");
                else sb.append(String.valueOf(dist[i][j]));
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static void bfs(){
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            
            dist[nowX][nowY] = (dist[nowX][nowY] + count) % 10;
            visited[nowX][nowY] = false;
        }
    }

    static int getAreaCount(int x, int y){
        count++;

        for(int i = 0; i < 4; i++) {
            int nextX = x + changeX[i];
            int nextY = y + changeY[i];


            if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > M - 1)
                continue;


            if (visited[nextX][nextY])
                continue;

            if(map[nextX][nextY] == 1){
                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
                continue;
            }

            visited[nextX][nextY] = true;
            getAreaCount(nextX, nextY);
        }
        return count;
    }
}