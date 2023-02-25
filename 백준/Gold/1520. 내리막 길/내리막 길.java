import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int [][] map, memo;
    static int N, M, ans;
    static boolean[][] visited;
    static int[] changeX = {-1, 0, 1, 0};
    static int[] changeY = {0, -1, 0, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map= new int[N][M];
        memo = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }// map 완성

        for(int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }
        memo[N-1][M-1] = 1;
        dfs(0,0);
        

        if(memo[0][0] == -1) memo[0][0]++;
        bw.write(String.valueOf(memo[0][0]));
        bw.flush();
    }

    static int dfs(int x, int y){
        boolean block = true;
        if(memo[x][y] >= 0) return memo[x][y];
        //도달 못했을 때 처리는 어떻게 해야할까?

        else {
            for (int i = 0; i < 4; i++) {
                int nextX = x + changeX[i];
                int nextY = y + changeY[i];

                if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > M - 1 || !isDown(x, y, nextX, nextY))
                    continue;


                block = false;
                if(memo[x][y] == -1) memo[x][y] += dfs(nextX, nextY)+1;
                else memo[x][y] += dfs(nextX, nextY);
            }
        }
        if(block) return 0;
        else return memo[x][y];
    }

    static boolean isDown(int nowX, int nowY, int nextX, int nextY){
        if(map[nowX][nowY] > map[nextX][nextY]) return true;
        else return false;
    }
}
