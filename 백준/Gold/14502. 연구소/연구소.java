import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int[][]map, wall;
    static boolean[][] visited;
    static boolean[]x_visited, y_visited;
    static int N, M, virus, ans, wallCount;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<int[]> queue = new LinkedList<int[]>();
    static int[] changeX = {-1, 0, 1, 0};
    static int[] changeY = {0, -1, 0, 1};

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        x_visited = new boolean[N];
        y_visited= new boolean[M];
        wall = new int[3][2];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j < M; j++){
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if(input == 1) wallCount++;
            }
        }// map정보 완성


        combination(0,0);
        System.out.print(ans);
    }


    static void dfs(int nowX, int nowY){
        visited[nowX][nowY] = true;
        virus++;
        for(int i =0; i < 4 ; i++) {
            int nextX = nowX + changeX[i];
            int nextY = nowY + changeY[i];

            if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                continue;

            if(visited[nextX][nextY])
                continue;

            if(map[nextX][nextY] != 0)
                continue;

            dfs(nextX, nextY);
        }
    }

    static int Bfs(int nowX, int nowY){
        int area = 0;
        visited[nowX][nowY] = true;
        queue.add(new int[]{nowX, nowY});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            area++;
            nowX = now[0];
            nowY = now[1];
            for(int i =0; i < 4 ; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(map[nextX][nextY] != 0)
                    continue;

                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }
        return area;
    }


    static void combination(int x, int depth){
        if(depth == 3){
            visited = new boolean[N][M];
            virus = 0;
            for(int k = 0; k < N; k++){
                for(int g = 0; g < M; g++){
                    if(map[k][g] == 2 && !visited[k][g])
                        dfs(k, g);
                }
            }

            ans = Math.max(N*M - (wallCount + 3 + virus), ans);
            return;
        }


        for(int i = x; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    combination(i, depth+1);
                    map[i][j] = 0;
                }
            }
        }
    }
}