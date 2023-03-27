import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] visited;
    static int[][] alphMap;
    static int R, C, ans;
    static int[] changeX = {0, 1, 0, -1};
    static int[] changeY = {1, 0, -1, 0};

    public static void main(String[] args)throws IOException {
        st =new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphMap= new int[R][C];
        visited = new boolean[26];

        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                alphMap[i][j] = str.charAt(j) - 65;
                //System.out.println(alphMap[i][j]);
            }
        }
        dfs(0, 0, 1);
        System.out.print(ans);
    }

    static void dfs(int x, int y, int depth){
        ans = Math.max(ans, depth);
        visited[alphMap[x][y]] = true;
        for(int i = 0; i < 4; i++){
            int nextX = x + changeX[i];
            int nextY = y + changeY[i];

            if(nextX < 0 || nextX > R-1 || nextY < 0 || nextY > C-1
                    || visited[alphMap[nextX][nextY]])
                continue;

            visited[alphMap[nextX][nextY]] = true;
            dfs(nextX, nextY, depth+1);
            visited[alphMap[nextX][nextY]] = false;
        }
        visited[alphMap[x][y]] = false;
    }
}
