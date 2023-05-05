import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] changeX = {0, 1, 0, -1};
    static int[] changeY = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                int ans = 0;
                if (!visited[i][j]) ans = bfs(i, j);
                if (ans == 1) {
                    System.out.println("Yes");
                    return;
                }
            }
        }

        System.out.println("No");
    }

    static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, -1}); // x, y, prev

        while(!q.isEmpty()){
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            int prev = now[2];

            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                    continue;
                
                if(map[nowX][nowY] != map[nextX][nextY] || nextX * M + nextY == prev)
                    continue;

                if(visited[nextX][nextY]) return 1;
                
                visited[nextX][nextY] = true;
                q.add(new int[]{nextX, nextY, (nowX*M) + nowY});
            }

        }
        return 0;
    }
}
