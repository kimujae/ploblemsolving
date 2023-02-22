import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Contry[][] map;
    static boolean[][] visited;
    static Queue<Contry> queue = new LinkedList<>();
    static Queue<Contry> movingQ = new LinkedList<>();
    static int[] changeX = {-1, 0 ,1, 0};
    static int[] changeY = {0, -1, 0, 1};
    static int N, L, R, move;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// N*N 땅
        // L <= x <= R 범위 내에서 연합형성
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new Contry[N][N];
        visited = new boolean[N][N];
        move = -1;


        for(int i =  0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = new Contry(i, j, Integer.parseInt(st.nextToken()));
            }
        }


        //연합이 하나도 생기지 않을 때까지 반복
        //visited는 map모두 탐색 이후 초기화
        int count = 0;
        while(count < N*N) {
            count = 0;
            move++;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
        }

        bw.write(String.valueOf(move));
        bw.flush();
    }

    static class Contry{
        int x;
        int y;
        int people;

        Contry(int x, int y, int person){
            super();
            this.x = x;
            this.y = y;
            this.people = person;
        }
    }

    static void bfs(int x, int y){
        visited[x][y]= true;
        queue.add(map[x][y]);
        movingQ.add(map[x][y]);
        int person = map[x][y].people;

        while(!queue.isEmpty()){
            Contry now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;

            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > N-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(!checkUnion(nowX, nowY , nextX, nextY))
                    continue;

                visited[nextX][nextY] =  true;
                queue.add(map[nextX][nextY]);
                movingQ.add(map[nextX][nextY]); // 연합국가 add
                person += map[nextX][nextY].people; // 연합인구수
            }
        } // 연합 파악 완료
        moving(movingQ, person);
    }


    static boolean checkUnion(int nowX, int nowY, int nextX, int nextY){
        if(L <= Math.abs(map[nowX][nowY].people - map[nextX][nextY].people)
                && Math.abs(map[nowX][nowY].people - map[nextX][nextY].people) <= R) return true;
        else return false;
    }

    static void moving(Queue<Contry> queue, int person){
        int num = (int)person/queue.size();
        while(!queue.isEmpty()) {
            Contry now = queue.poll();
            now.people = num;
        }
    }

}
