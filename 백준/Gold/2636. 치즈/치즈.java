import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, time, cheezeSize;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> cheezeQ = new LinkedList<>();
    static int[] changeX = {-1, 0, 1, 0};
    static int[] changeY = {0, -1, 0, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }//map정보 완성

        air(0,0);
        melt();
        bw.write(String.valueOf(time));
        bw.append('\n');
        bw.append(String.valueOf(cheezeSize));
        bw.flush();
    }

    static void air(int X, int Y){
        visited[X][Y] = true;
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

                if(map[nextX][nextY] == 1){
                    //치즈와 공기가 만날때
                    cheezeQ.add(new int[]{nextX, nextY});
                    map[nextX][nextY]= 0;
                    visited[nextX][nextY] = true;
                    continue;
                }

                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }
    }

    static void melt(){
        while(!cheezeQ.isEmpty()){
            int count = 0;
            int size = cheezeSize =cheezeQ.size();
            if(size > 0) time++;

            while(count < size){
                int[] now = cheezeQ.poll();
                count++;
                int nowX = now[0];
                int nowY = now[1];

                for(int i = 0; i < 4; i++){
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];

                    if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                        continue;

                    if(visited[nextX][nextY])
                        continue;

                    if(map[nextX][nextY] == 1){
                        cheezeQ.add(new int[]{nextX, nextY});
                        map[nextX][nextY]= 0;
                        visited[nextX][nextY] = true;
                        continue;
                    }

                    //새로운 공기구멍을 만나면
                    air(nextX, nextY);
                }
            }
        }

    }
}
