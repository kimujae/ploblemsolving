import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
    public static Stack<int[]> stack = new Stack<>();
    public static boolean[][] visited;
    public static int[][] map;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int n, m, size;
    public static int[] changeX ={0, 1, 0, -1};
    public static int[] changeY ={1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];


        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }// 맵 완성

        int count = 0;
        int max = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                size = 0;
                if(visited[i][j]== false && map[i][j] == 1) {
                    Dfs(i, j);
                    count ++;
                    max = Math.max(size, max);
                }
            }
        }

        System.out.println(count);
        System.out.println(max);
    }

    public static void Dfs(int startX, int startY) {
        visited[startX][startY] = true;
        size++;

        for (int i = 0; i < 4; i++) {
            int nextX = startX + changeX[i];
            int nextY = startY + changeY[i];

            if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1)
                continue;

            if (visited[nextX][nextY])
                continue;

            if(map[nextX][nextY] == 0)
                continue;

            Dfs(nextX, nextY);
        }
    }
}