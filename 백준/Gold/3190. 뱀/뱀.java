import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] changeX = {0, 1, 0, -1};
    static int[] changeY = {1, 0, -1, 0};
    static class Dummy {
        int length = 1;
        int vec; // 0 1 2 3 동 서 남 북
        int[] head;
        int[] tail;

        public Dummy() {
            this.vec = 0;
            this.head = new int[2];
            this.tail = new int[2];
        }

        void setVec(int vec) {
            this.vec = vec;
        }

        int getVec() {
            return this.vec;
        }

        void setLength(int len) {
            this.length = len;
        }

        int getLength() {
            return this.length;
        }

        void setHead(int x, int y){
            this.head[0] = x;
            this.head[1] = y;
        }

        void setTail(int x, int y){
            this.tail[0] = x;
            this.tail[1] = y;
        }

        int[] getHead(){
            return this.head;
        }

        int[] getTail(){
            return this.tail;
        }
    }

    public static void main(String[] args) throws IOException {
        /*
            사과 먹으면 => 뱀 길이 늘어남
            벽 또는 자기 자신과 부딪히면 게임over

            NxN 정사각 보드
            사과, 벽, 뱀(좌상단, 길이 = 1)

            뱀 이동 규칙
            - 매초마다 이동
            1. 먼저 뱀은 몸길이를 늘려
            2. 머리를 다음칸에 위치시킨다.
            3. 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
            - 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
            - 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
         */
        int N = Integer.parseInt(br.readLine());
        int[][][] map = new int[3][N][N]; // tail의 포인터 정보도 가지고 있어야한다.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[1][i][j] = i;
                map[2][i][j] = j;
            }
        }// 포인터 정보 초기화

        int apple = Integer.parseInt(br.readLine());

        for (int i = 0; i < apple; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[0][x][y] = 2;
        }

        char[] vecs = new char[10001];
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());

            vecs[idx] = st.nextToken().charAt(0);
        }

        int time = 0;
        Dummy sneak = new Dummy();

        map[0][0][0] = 1;
        while (true) {
            time++;

            int[] now = sneak.getHead();
            int vec = sneak.getVec();
            int nextX = now[0] + changeX[vec];
            int nextY = now[1] + changeY[vec];

            if (nextX < 0 || nextY < 0 || nextX > N - 1 || nextY > N - 1 || map[0][nextX][nextY] == 1) {
                break;
                // 벽 혹은 뱀의 몸에 접근하면 fail
            }


            map[1][now[0]][now[1]] = nextX;
            map[2][now[0]][now[1]] = nextY;
            sneak.setHead(nextX, nextY);


            if (map[0][nextX][nextY] != 2) {
                // 사과가 없다면

                int[] tail = sneak.getTail();
                map[0][tail[0]][tail[1]] = 0;
                //System.out.println(time + " " + tail[0] + " "+ tail[1]);
                //System.out.println(map[1][tail[0]][tail[1]] + " "+ map[2][tail[0]][tail[1]]);
                sneak.setTail(map[1][tail[0]][tail[1]], map[2][tail[0]][tail[1]]);

                //map[1][tail[0]][tail[1]] = tail[0];
                //map[2][tail[0]][tail[1]] = tail[1];
            } else sneak.setLength(sneak.getLength() + 1);


            map[0][nextX][nextY] = 1;

            if (vecs[time] == 'D' || vecs[time] == 'L') {
                int lr = 0;
                if (vecs[time] == 'D') lr = 1;

                sneak.setVec(getVec(sneak.getVec(), lr));
            }
        }
        System.out.print(time);
    }
    static int getVec(int vec, int lr){
        // left, right 방향전환에 따라 뱀의 vec를 계산해주는 함수
        if(lr == 0)return (vec + 3) % 4;
        else return (vec + 5) % 4;
    }

}