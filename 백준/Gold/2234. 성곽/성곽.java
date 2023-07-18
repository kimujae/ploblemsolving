import java.util.LinkedList;
import java.util.Queue;

public class Main{
    static Queue<int[]> q = new LinkedList<>();
    static int N, M, ans1, ans2,ans3, count;
    static int[][] map;
    static int[][][] area;
    static boolean[][] visited;
    static int[] changeX = {0, -1, 0, 1};
    static int[] changeY = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        area = new int[M][N][2];
        map = new int[M][N];
        visited = new boolean[M][N];

        for(int i = 0; i< M; i++){
            for(int j = 0 ; j < N; j++){
                int num = read();
                map[i][j] = num;
            }
        }// 방, 벽 정보 입력 완료


        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]) {
                    dfs(i, j ,++ans1);
                    ans2 = Math.max(ans2, count);
                    setArea();
                }
            }
        }

        visited = new boolean[M][N];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                breakWall(i, j);
            }
        }

        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
    }


    static void dfs(int nowX, int nowY, int areas){
        visited[nowX][nowY] = true;
        q.add(new int[]{nowX, nowY});
        area[nowX][nowY][0] = areas;
        ++count;

        int i = map[nowX][nowY];
        for(int wall = 0; wall < 4; wall++){// 서, 북, 동, 남
            if((i & (1 << wall)) != 0)
                continue;


            int nextX = nowX + changeX[wall];
            int nextY = nowY + changeY[wall];

            if(nextX < 0 || nextX > M-1 || nextY < 0 || nextY > N-1)
                continue;

            if(visited[nextX][nextY])
                continue;


            dfs(nextX, nextY, areas);
        }
    }


    static void breakWall(int nowX, int nowY){
        int size = area[nowX][nowY][1];
        int areas = area[nowX][nowY][0];

        for(int i = 0; i < 4; i++){
            int nextX = nowX + changeX[i];
            int nextY = nowY + changeY[i];

            if(nextX < 0 || nextX > M-1 || nextY < 0 || nextY > N-1)
                continue;

            if(areas == area[nextX][nextY][0])
                continue;

            if(visited[nextX][nextY])
                continue;

            ans3 = Math.max(ans3, area[nextX][nextY][1] + size);
        }
    }


    static void setArea(){
        while(!q.isEmpty()){
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];

            area[nowX][nowY][1] = count;
        }
        count = 0;
    }


    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
