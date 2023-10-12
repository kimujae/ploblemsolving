import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    private static StringTokenizer st;
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long ans;
    private static int[][] A; // 모래의 양
    private static boolean[][] v;
    private static int[] cx = {0, 1, 0, -1};
    private static int[] cy = {-1, 0, 1, 0};
    private static int[][] rateMap = {
            {0, 0, 2, 0, 0},
            {0, 10, 7, 1, 0},
            {5, 0, 0, 0, 0},
            {0, 10, 7, 1, 0},
            {0, 0, 2, 0, 0}
    };

    private static class Tornado{
        int x;
        int y;
        int vec;

        public Tornado(int x, int y, int vec){
            this.x = x;
            this.y = y;
            this.vec = vec;
            v[x][y] = true;
        }


        public boolean move() {
            int x = this.x;
            int y = this.y;
            int nvec = (vec + 1) % 4;

            int nx, ny;
            if(x == N/2 && y == N/2) {
                nx = x + cx[vec];
                ny = y + cy[vec];
            } else {
                nx = x + cx[nvec];
                ny = y + cy[nvec];
            }

            if(nx > N-1 || ny > N-1 || nx < 0 || ny < 0 || v[nx][ny]) {
                //vec = (vec + 1) % 4;
                nx = x + cx[vec];
                ny = y + cy[vec];

            } else {
                if(!(x == N/2 && y == N/2)) {
                    vec = nvec;
                    rateMap = rotate();
                }
            }

            this.x = nx;
            this.y = ny;
            v[x][y] = true;

            if(this.x == 0 && this.y == 0) return false;
            return true;
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());


         /*
            반복
            1. 이동
            2. 모래 이동
            3. 모래 흩날리기
         */

        A = new int[N][N];
        v = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Tornado t = new Tornado(N/2, N/2, 0);
        while(true) {
            boolean b = t.move();
            moveSand(new int[]{t.x, t.y}, t.vec);
            //print();
            //printT(t);
            if(!b) break;
        }

        System.out.println(ans);
    }

    public static int[][] rotate() {
        int[][] nMap = new int[5][5];
        for(int y = 4, i = 0 ; y >= 0; y--, i++) {
            for(int x = 0, j = 0; x < 5; x++, j++) {
                nMap[i][j] = rateMap[x][y];
                //System.out.print(rateMap[x][y]+ " ");
            }
            //System.out.println();
        }
        //System.out.println();

        return nMap;
    }


    public static void moveSand(int[] now, int vec) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{2, 2});
        int ox = now[0];
        int oy = now[1];
        //System.out.println(ox + " " + oy);
        int sand = A[ox][oy];

        boolean[][] localV = new boolean[5][5];
        localV[2][2] = true;
        int sum = 0;
        while(!q.isEmpty()) {
            int[] n = q.poll();
            int x = n[0];
            int y = n[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + cx[i];
                int ny = y + cy[i];
                int nox = nx + (ox - 2);
                int noy = ny + (oy - 2);

                if(nx > 4 || ny > 4
                        || nx < 0 || ny < 0 || localV[nx][ny])
                    continue;

                int s = (int) ((((double)sand/100)) * (double)rateMap[nx][ny]);


                if(!(nox > N - 1  || noy > N - 1 || nox < 0 || noy < 0)) A[nox][noy] += s;
                else ans += s;

                sum += s;
                q.add(new int[]{nx, ny});
                localV[nx][ny] = true;
            }
        }

        sand -= sum;
        if(sand > 0) {
            int nox = ox + cx[vec];
            int noy = oy + cy[vec];
            if(nox > N - 1|| noy > N-1 || nox < 0 || noy < 0) ans += sand;
            else A[nox][noy] += sand;
        }
    }

    public static void print() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void printT(Tornado t) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == t.x && j == t.y)System.out.print(1 + " ");
                System.out.print(0 + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
