import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, T;
    private static int[][] map;
    //공기청정기는 항상 1번열에 설치, 크기는 두행을 차지함
    private static class Cleaner{
        int x;
        int y;

        public Cleaner(int x){
            this.x = x;
            this.y = 0;
        }

        public void topClean() {
            // 상, 우, 하, 좌
            int[] cx = {-1, 0, 1, 0};
            int[] cy = {0, 1, 0, -1};

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{x, y});
            int vec = 0;
            boolean[][] v = new boolean[R][C];
            v[x][y] = true;

            while(!q.isEmpty()) {
                int[] now = q.poll();
                int x = now[0];
                int y = now[1];


                int nx = x + cx[vec];
                int ny = y + cy[vec];


                if(nx > R-1 || ny > C -1 || nx < 0 || ny < 0 || (vec == 2 && nx == this.x + 1)) {
                    vec = (vec + 1) % 4;
                    nx = x + cx[vec];
                    ny = y + cy[vec];
                }

                if(v[nx][ny]) break;


                if(map[x][y] != -1) map[x][y] = map[nx][ny];
                map[nx][ny] = 0;

                v[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }

        }

        public void bottomClean() {
            // 하 우 상 좌
            int[] cx = {1, 0, -1, 0};
            int[] cy = {0, 1, 0, -1};

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{x+1, y});
            int vec = 0;
            boolean[][] v = new boolean[R][C];
            v[x+1][y] = true;

            while(!q.isEmpty()) {
                int[] now = q.poll();
                int x = now[0];
                int y = now[1];


                int nx = x + cx[vec];
                int ny = y + cy[vec];


                if(nx > R-1 || ny > C -1 || nx < 0 || ny < 0 || (vec == 2 && nx == this.x)) {
                    vec = (vec + 1) % 4;
                    nx = x + cx[vec];
                    ny = y + cy[vec];
                }

                if(v[nx][ny]) break;


                if(map[x][y] != -1) map[x][y] = map[nx][ny];
                map[nx][ny] = 0;
                v[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

    }

    private static int[][] windMap;
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        /*
            1. 미세먼지 확산 : 확산은 미세먼지가 있는 모든 칸에서 동시에 발생
            2. r,c에 있는 미세먼지는 인접한 사방으로 확산
            2-1. 인접한 곳에 공청 혹은 칸 없으면 확산 x
            3. 확산되는 양은 미세먼지 양/ 5 -> 소수점 버림
            4. r,c 에 남은 미세먼지 양 : 미세먼지양 - (미세먼지양/5 * 확산 영역 개수)
            5. 공기청정기 작동 : 위 반시계방향 순환(우 상 좌 하) 아래 시계방향 순환(우 하 좌 상)

            목표 : T초가 지난 후 미세먼지의 총 양의 합은?
         */
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        boolean isFind = false;
        Cleaner c = null;
        map = new int[R][C];
        windMap = new int[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == -1 && !isFind) {
                    c = new Cleaner(i);
                    isFind = true;
                }
                map[i][j] = n;
            }
        }// 지도 채우기


        for(int t = 0; t < T; t++) {
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(map[i][j] == -1 || map[i][j] == 0) continue;

                    expandDust(i, j, map[i][j]);
                }
            }

            copyMap(c.x, c.x+1, c.y);

            //printMap();
            c.topClean();
            c.bottomClean();
            //printMap();

        }

        System.out.print(retAnswer());
    }



    public static void expandDust(int x, int y, int dust) {
        int[] cx = {0, 1, 0, -1};
        int[] cy = {1, 0, -1, 0};

        int area = 0;
        int d = dust / 5;

        for(int i = 0; i < 4; i++) {
            int nx = x + cx[i];
            int ny = y + cy[i];

            if(nx > R -1 || ny > C -1 || nx < 0 || ny < 0 || map[nx][ny] == -1) continue;

            windMap[nx][ny] += d;
            area++;
        }

        windMap[x][y] += (map[x][y] - (area*d));
    }

    public static int retAnswer() {
        int sum = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == -1 || map[i][j] == 0) continue;

                sum += map[i][j];
            }
        }
        return sum;
    }

    public static void copyMap(int x, int x1, int y) {
        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0 ; j < C; j++) {
                map[i][j] = windMap[i][j];
            }
        }

        map[x1][y] = map[x][y] = -1;

        windMap = new int[R][C];
    }

    public static void printMap() {
        for(int i = 0; i < R; i++) {
            for(int j = 0 ; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
