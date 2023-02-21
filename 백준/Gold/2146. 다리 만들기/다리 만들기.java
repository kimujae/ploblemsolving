import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, min;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> islandQ = new LinkedList<>();
    static int[] changeX = {-1, 0, 1, 0};
    static int[] changeY = {0, -1, 0, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        min = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }// map 완성

        //1. 각 섬(영역) 확인
        int island = 0;
        for(int i  = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    island++; //섬 발견
                    check(i, j, island);
                }
            }
        }//섬 구별 완료

        //2. 영역확장시작
        bridge();
        bw.write(String.valueOf(min));
        bw.flush();
    }


    static void check(int X, int Y, int island){
        visited[X][Y] = true;
        map[X][Y] = island;
        queue.add(new int[]{X, Y});
        islandQ.add(new int[]{X, Y});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > N-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(map[nextX][nextY] == 0)
                    continue;

                visited[nextX][nextY] = true;
                map[nextX][nextY] = island;
                queue.add(new int[]{nextX, nextY});
                islandQ.add(new int[]{nextX, nextY});
            }
        }
    }

    static void bridge(){
        while(!islandQ.isEmpty()) {
            int count = 0;
            int size = islandQ.size();
            
            while (count < size) {
                int[] now = islandQ.poll();
                count++;
                int nowX = now[0];
                int nowY = now[1];

                for (int i = 0; i < 4; i++) {
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];

                    if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                        continue;

                    if (visited[nextX][nextY] && (map[nowX][nowY] == map[nextX][nextY])) // 현재 영역
                        continue;

                    if (visited[nextX][nextY] && (map[nowX][nowY] != map[nextX][nextY])){ // 다른 섬과 맞닿는 지점
                        min = Math.min(min, dist[nextX][nextY] + dist[nowX][nowY]);
                        continue;
                    }

                    visited[nextX][nextY] = true;
                    map[nextX][nextY] = map[nowX][nowY];
                    dist[nextX][nextY] = dist[nowX][nowY] + 1;
                    islandQ.add(new int[]{nextX, nextY});
                }
            }
            if(min != Integer.MAX_VALUE) return;
        }
    }
}
