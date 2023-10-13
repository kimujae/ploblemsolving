import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M, F;
    private static int[][] pMap; // 승객 map -> 벽정보도 함께 있음
    private static int[] cx = {1, 0 , -1, 0};
    private static int[] cy = {0, 1, 0, -1};
    private static class Inform{
        int[] passanger;
        int[] goal;

        public Inform(int[] p, int[] g) {
            this.passanger = p;
            this.goal = g;
        }
    }


    private static class Taxi{
        int fuel;
        int x;
        int y;

        public Taxi(int fuel, int x, int y) {
            this.fuel = fuel;
            this.x = x;
            this.y = y;
        }


        public int selectPassanger() {
            //선택된 승객 번호를 반환
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                }
            });
            Queue<int[]> q = new LinkedList<>();
            boolean[][] v = new boolean[N][N];

            //택시 자리에 승객 있으면 바로 반환
            if(pMap[x][y] > 1) return pMap[x][y] - 2;
            q.add(new int[]{x, y, 0});
            v[x][y] = true;

            boolean isFind = false;
            while(!q.isEmpty()) {
                if(isFind) break; // 승객들 찾았으면 탈출

                int size = q.size();
                int count = 0;
                while(size > count) {
                    count++;
                    int[] now = q.poll();
                    int x = now[0];
                    int y = now[1];
                    int d = now[2];


                    for(int i = 0; i < 4; i++) {
                        int nx = x + cx[i];
                        int ny = y + cy[i];
                        int nd = d + 1;

                        if(nx > N -1 || ny > N -1 || nx < 0 || ny < 0
                                || v[nx][ny] || pMap[nx][ny] == 1 || nd > fuel)
                            continue;



                        v[nx][ny] = true;
                        if(pMap[nx][ny] > 1) {
                            pq.add(new int[]{nx, ny, nd, pMap[nx][ny] - 2});
                            isFind = true;
                            continue;
                        }
                        q.add(new int[]{nx, ny, nd});
                    }
                }
            }

            if(pq.isEmpty()) return -1; // 연료부족해서 승객 못태우면 실패

            int[] p = pq.poll();
            this.x = p[0];
            this.y = p[1];
            this.fuel -= p[2];

            return p[3]; // n번째 승객
        }

        public boolean move(int selectPassanger, Inform[] informs) {
            Queue<int[]> q = new LinkedList<>();
            int[] goal = informs[selectPassanger].goal;
            int gx = goal[0];
            int gy = goal[1];

            boolean[][] v = new boolean[N][N];
            v[x][y] = true;
            q.add(new int[]{x, y, 0});
            int px = x;
            int py = y;

            while(!q.isEmpty()) {
                int[] now = q.poll();
                int x = now[0];
                int y = now[1];
                int d = now[2];

                for(int i = 0; i < 4; i++) {
                    int nx = x + cx[i];
                    int ny = y + cy[i];
                    int nd = d + 1;


                    if(nx > N -1 || ny > N -1 || nx < 0 || ny < 0
                            || v[nx][ny] || pMap[nx][ny] == 1)
                        continue;

                    if(nd > fuel) return false;
                    if(nx == gx && ny == gy) {
                        this.x = nx;
                        this.y = ny;
                        //택시 이동
                        this.fuel -= nd;
                        this.fuel += (nd * 2);

                        //승객, 목적지 제거
                        pMap[px][py] = 0;

                        return true;
                    }

                    v[nx][ny] = true;
                    q.add(new int[]{nx, ny, nd});
                }
            }
            return false;
        }
    }
    private static Inform[] informs; // N개의 어레이

    public static void main(String[] args) throws IOException {
        /*
            도착지 가면 연료 충전, 연료 바닥나면 업무 종료
            M명 태우는 것이 목표
            격자 각 칸은 비어있거나 벽
            택시는 상하좌우로 이동  - 특정 위치로 이동 시 항상 최단경로로만 이동함
            승객은 빈칸 중 하나에 위치 - 다른 빈칸 중 하나로 이동 원함 (현재위치, 목적지)
            여러 승객이 같이 탑승하는 경우 없음
            따라서, 백준이 승객 태우고 내리는 걸 M번 반복해야함

            승객 고르기 : 가장 가까운 손님 (1. 행번호 최소 2. 열 번호 최소)
            택시 - 승객 같은 위치면 최단거리 0임 : 택시 위치부터 체크해야한다.

            연료 : 한칸 이동 시 1 소모
            목적지 이동 : 소모 연료 2배 충전
            이동 도중 연료 바닥나면 업무 종료
         */

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        pMap = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                pMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        Taxi t = new Taxi(F, x, y);
        informs = new Inform[M];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int px = Integer.parseInt(st.nextToken()) - 1;
            int py = Integer.parseInt(st.nextToken()) - 1;

            int gx = Integer.parseInt(st.nextToken()) - 1;
            int gy = Integer.parseInt(st.nextToken()) - 1;


            pMap[px][py] = i + 2;
            informs[i]  = new Inform(new int[]{px, py}, new int[]{gx, gy});
        }

        boolean isFail = false;
        for(int i = 0; i < M; i++) {
            int ret = t.selectPassanger();
            if(ret == -1) {
                isFail = true;
                break;
            }
            boolean r = t.move(ret, informs);
            if(!r) {
                isFail = true;
                break;
            }

        }

        if(isFail) System.out.println(-1);
        else System.out.println(t.fuel);
    }
}
