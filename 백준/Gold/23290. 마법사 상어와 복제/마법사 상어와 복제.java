import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, M, S;
    private static BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static Block[][] map; // 4*4 행렬
    private static int[] cx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] cy = {0, -1, -1, -1, 0, 1, 1 ,1};
    private static int[] fx = {-1,0,1,0};
    private static int[] fy = {0,-1,0,1};
    private static Queue<Fish> fQ;
    private static class Fish{
        int x;
        int y;
        int vec;

        public Fish(int x, int y, int vec) {
            this.x = x;
            this.y = y;
            this.vec = vec;
        }

        //2. 모든 물고기 한 칸 이동 (상어, 물고기 냄새 있는 칸, 격자 벗어난 칸 이동 불가)
        //    - 이동가능 할 때 까지 45도로 반시계 방향 회전
        public void move(int[] jaws) {
            int nx = x;
            int ny = y;
            for(int i = 0; i < 8; i++) {
                nx += cx[vec];
                ny += cy[vec];

                if(nx > 3 || ny > 3 || nx < 0 || ny < 0
                        || map[nx][ny].fishSmell > 0 || (jaws[0] == nx && jaws[1] == ny)) {
                    nx -= cx[vec];
                    ny -= cy[vec];
                    rotate();
                    continue;
                }

                this.x = nx;
                this.y = ny;

                break;
            }
        }

        public void rotate() {
            vec = (vec+1) % 8;
        }
    }

    public static class Jaws{
        int x;
        int y;
        int f;
        int[] path;
        int[] p;

        public Jaws(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initPath() {
            this.path = new int[3];
            this.p = new int[3];
            this.f = -1;
        }

        public void findPath(int depth) {
            if(depth == 3){
                int ret = retFishes(path);
                if(ret == -1) return;
                if(f < ret) {
                    f = ret;
                    for(int i = 0; i < 3; i++) {
                        p[i] = path[i];
                    }
                }
                return;
            }

            for(int i = 0 ; i < 4; i++) {
                path[depth] = i;
                findPath(depth+1);
            }
        }


        public void move() {
            int nx = x;
            int ny = y;
            for(int i : p) {
                nx += fx[i];
                ny += fy[i];
                
                if(map[nx][ny].fishes.size() > 0) map[nx][ny].fishSmell = 3;
                map[nx][ny].fishes.clear();
            }
            this.x= nx;
            this.y= ny;
        }

        public int retFishes(int[] path) {
            boolean[][] v = new boolean[4][4];
            int nx = x;
            int ny = y;
            int sum = 0;
            //v[nx][ny] = true; // 이게 문제....
            
            for(int i : path) {
                nx += fx[i];
                ny += fy[i];


                if(nx > 3 || ny > 3 || nx < 0 || ny < 0) return -1;

                if(!v[nx][ny]) sum += map[nx][ny].fishes.size();
                v[nx][ny] = true;
            }
            return sum;
        }
    }

    public static class Block{
        ArrayList<Fish> fishes;
        int fishSmell;

        public Block() {
            fishes = new ArrayList<>();
            fishSmell = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        /*
            격자 칸 하나에 물고기 총 M마리 - 둘 이상 물고기가 같은 칸에 있을 수도 있음
            이동방향 8방 (상하좌우대각)
            상어도 격자 한칸 차지

            map[][] 에는 리스트 : 물고기들 정보, 리스트 : 물고기 냄새들 정보
            물고기 , 상어 , inform 3가지 클래스
            물고기 : 방향, 위치
            상어 : 방향, 위치

            상어의 마법연습(S번 반복)
            1. 모든 물고기 복제 -> 5번에서 복제 물고기 나타남 ok
            2. 모든 물고기 한 칸 이동 (상어, 물고기 냄새 있는 칸, 격자 벗어난 칸 이동 불가)
            - 이동가능 할 때 까지 45도로 반시계 방향 회전
            3. 상어 연속 3칸 이동
            - 상하좌우 이동 가능 / 물고기 마주치면 물고기 삭제 -> 물고기 냄새 남기기
            - 이동 가능한 방법 중 제외되는 물고기 수 가장 많은 방법으로 이동함
            - 그러한 경우가 많다면, 상1 좌2 하3 우4 방향을 이어붙여 정수를 만든다.
            최소값을 우선으로 한다.

           4. 두 번 전 연습에서 생긴 물고기의 냄새 격자에서 사라짐
           5. 복제 물고기 발생 -> 1의 물고기 위치와 방향 그대로 갖게됨 ok
         */
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new Block[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                map[i][j] = new Block();
            }
        }

         for(int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());
             int x = Integer.parseInt(st.nextToken()) - 1;
             int y = Integer.parseInt(st.nextToken()) - 1;
             int vec = Integer.parseInt(st.nextToken()) - 1;
             Fish f;

             if(vec == 0) f = new Fish(x, y, 2);
             else if(vec == 1) f = new Fish(x, y, 1);
             else if(vec == 2) f = new Fish(x,y, 0);
             else if(vec == 3) f = new Fish(x, y, 7);
             else if(vec == 4) f = new Fish(x, y, 6);
             else if(vec == 5) f = new Fish(x, y, 5);
             else if(vec == 6) f = new Fish(x, y, 4);
             else f = new Fish(x, y, 3);

             map[x][y].fishes.add(f);
         }

         st = new StringTokenizer(br.readLine());
         int x = Integer.parseInt(st.nextToken()) - 1;
         int y = Integer.parseInt(st.nextToken()) - 1;
         Jaws jaws = new Jaws(x,y);
         for(int i = 0; i < S; i++) {
             copyFish();
             Block[][] nMap = new Block[4][4];
             nMap = initMap(nMap);

             for(int k = 0; k < 4; k++) {
                 for(int j = 0; j < 4; j++) {
                     for(Fish f : map[k][j].fishes) {
                         //System.out.print(f.vec);
                         f.move(new int[]{jaws.x, jaws.y});
                         //System.out.println(f.x + " " + f.y);
                         nMap[f.x][f.y].fishes.add(f);
                     }
                 }
             }

             copyMap(nMap);
             jaws.initPath();
             jaws.findPath(0);
             jaws.move();
             removeSmell();
             appearFish();
             //print();
         }

         System.out.println(ret());
    }

    public static void copyFish() {
        fQ = new LinkedList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                ArrayList<Fish> fishes = map[i][j].fishes;

                for(Fish f : fishes) {
                    Fish fish = new Fish(f.x, f.y, f.vec);
                    fQ.add(fish);

                }
            }
        }
    }

    public static void appearFish() {
        for(Fish f : fQ) {
            int x = f.x;
            int y = f.y;

            map[x][y].fishes.add(f);
        }
    }

    public static void removeSmell() {
        for(Block[] block : map) {
            for (Block b : block) {
                if(b.fishSmell > 0)b.fishSmell--;
            }
        }
    }

    public static int ret() {
        int sum = 0;
        for(int i = 0; i <4; i++) {
            for(int j = 0; j < 4; j++) {
                sum += map[i][j].fishes.size();
            }
        }
        return sum;
    }

    public static Block[][] initMap(Block[][] m) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                m[i][j] = new Block();
            }
        }
        return m;
    }

    public static void copyMap(Block[][] m) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                map[i][j].fishes = m[i][j].fishes;
                //map[i][j].fishSmell = m[i][j].fishSmell;
            }
        }
    }

    public static void print() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(map[i][j].fishSmell+ " ");
            }
            System.out.println();
        }
    }
}
