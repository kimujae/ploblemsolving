import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M , K;
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[][] map; // N*M지도
    private static int[] cx = {0, 1, 0, -1};
    private static int[] cy = {1, 0, -1, 0};
    private static class Cubic{
        int x;
        int y;
        int vec; // 동 남 서 북
        int[] dice;

        public Cubic() {
            this.x = 0;
            this.y = 0;
            this.vec = 0;
            this.dice = new int[]{2, 4, 1, 3, 5, 6};
        }

        public void rotate(int cubicInt, int mapInt) {
            if(cubicInt > mapInt) {
                //90도 시계방향 회전
                vec = (vec + 1) % 4;

            }else if(cubicInt < mapInt) {
                vec = (vec - 1 + 4) % 4;
            }else return;
        }

        public void revVec() {
            if(vec == 0) {
                vec = 2;
                return;
            } else if(vec == 1) {
                vec = 3;
                return;
            } else if(vec == 2) {
                vec = 0;
                return;
            } else vec = 1;
        }

        public int move() {
            // nx, ny 유효성 체크
            // 유효하지 않으면 회전
            // nx , ny로 한칸 이동
            int nx = x + cx[vec];
            int ny = y + cy[vec];
            int cv = vec;

            if(nx > N-1 || ny > M-1 || nx < 0 || ny < 0) {
                revVec();
                nx = x + cx[vec];
                ny = y + cy[vec];
            }

            x = nx;
            y = ny;


            // colQ, rowQ 변경
            int[] copy = new int[6];
            for(int i = 0; i < 6; i++) {
                copy[i] = dice[i];
            }

            if(vec == 0) {
                //  동
                dice[1] = copy[5];
                dice[2] = copy[1];
                dice[3] = copy[2];
                dice[5] = copy[3];
            } else if(vec == 2) {
                dice[1] = copy[2];
                dice[2] = copy[3];
                dice[3] = copy[5];
                dice[5] = copy[1];
            } else if(vec == 1) {
                dice[0] = copy[5];
                dice[2] = copy[0];
                dice[4] = copy[2];
                dice[5] = copy[4];
            } else {
                dice[0] = copy[2];
                dice[2] = copy[4];
                dice[4] = copy[5];
                dice[5] = copy[0];
            }


            int A = dice[5];
            int B = map[x][y];
            rotate(A, B);

            int size = bfs(new int[]{x, y}, B);
            return size * B;
        }

    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Cubic cubic = new Cubic();
        int score = 0;
        for(int i = 0; i < K; i++) {
            score += cubic.move();
        }
        System.out.println(score);
    }


    public static int bfs(int[] now, int B) {
        Queue<int[]> q= new LinkedList<>();
        q.add(now);
        boolean[][] v = new boolean[N][M];
        int size = 0;


        while(!q.isEmpty()) {
            int[] n = q.poll();
            size ++;
            int x = n[0];
            int y = n[1];
            v[x][y] = true;


            for(int i = 0; i < 4; i++) {
                int nx = x + cx[i];
                int ny = y + cy[i];

                if(nx > N-1 || ny > M-1 || nx < 0 || ny < 0
                        || v[nx][ny] || map[nx][ny] != B)
                    continue;

                v[nx][ny] = true;
                q.add(new int[]{nx, ny});

            }
        }
        return size;
    }
}
