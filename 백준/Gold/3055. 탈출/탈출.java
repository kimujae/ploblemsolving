import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static Queue<int[]> queue;
    public static int R,C;//map 행, 열
    public static int[][] waterTimes;
    public static boolean[][] visited;
    public static int[][] answerTimes;
    public static int[][] map;

    public static Scanner scan = new Scanner(System.in);
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};
    public static int waterX, waterY, startX, startY, finX, finY;
    public static Queue<int[]> waterQ = new LinkedList<int[]>();

    public static void main(String[] args) {
        //1. bfs를 이용한 구현
        //2. 시작 node가 두개인 상황
        //3. bfs(물x좌표, 물y좌표)로 waterTime[][] 배열완성시킴
        //3-1. waterTime[][]에 water가 도달하는 데 걸리는 소요시간이 저장
        //4. bfs(비버x좌표, 비버y좌표)실행
        //4-1.벽인지 판단 : 인접노드가 waterTime 1이하 차이라면, 벽이다.
        //4-2.길이라면 : 진행.
        //모두 수행 후 만약 D의 time 정보가 0이라면 KAKTUS
        //아니라면 time 출력
        // 물이 여러방향에 있는경우를 고려 못했다.
        // 물이 들어오면, 좌표를 waterQ 에 삽입
        // 1회에 이동가능한 물을 최대 한칸 이동시켜야한다.
        // queue.size()만큼 진행하면된다?
        // 결국은 세력(1분안에 갈 수 있는 세력을 모두 계산하여 비교하여야하는 것이 핵심)

        R = scan.nextInt();
        C = scan.nextInt();

        waterTimes = new int[R][C];
        answerTimes = new int[R][C];
        visited = new boolean[R][C];
        map = new int[R][C];

        for(int Rinput = 0; Rinput < R; Rinput++){
            String line = scan.next();
            for(int Cinput = 0; Cinput < C; Cinput++){
                char character = line.charAt(Cinput);
                if(character == 'S'){
                    map[Rinput][Cinput] = 9;// 비버는 9
                    startX = Rinput;
                    startY = Cinput;
                }
                else if(character == 'X'){
                    map[Rinput][Cinput] = 1; //돌은 1
                }
                else if(character == '*'){
                    map[Rinput][Cinput] = 8; // 물은 8
                    waterX = Rinput;
                    waterY = Cinput;
                    waterQ.add(new int[]{waterX, waterY});
                    visited[waterX][waterY] = true;
                }
                else if(character == 'D'){
                    map[Rinput][Cinput] = 7; // 굴은 7
                    finX = Rinput;
                    finY = Cinput;
                }
                else map[Rinput][Cinput] = 0; // 길은 0
            }
        } //map 채우기 완료

        bfs(startX, startY);

        if(answerTimes[finX][finY] == 0) System.out.println("KAKTUS");
        else System.out.println(answerTimes[finX][finY]);


    }

    static void bfs(int nowX, int nowY){
        visited = new boolean[R][C];
        visited[nowX][nowY] = true;
        queue = new LinkedList<int[]>();
        queue.add(new int[]{nowX, nowY});
        int[] node;

        while(!queue.isEmpty()) {
            int Wcount = 0;
            int count = 0;
            int Wsize = waterQ.size();
            int size = queue.size();

            while (Wcount < Wsize) {
                waterX = waterQ.peek()[0];
                waterY = waterQ.peek()[1];
                waterQ.poll();
                Wcount++;

                for (int i = 0; i < 4; i++) {
                    int waterNextX = waterX + changeX[i];
                    int waterNextY = waterY + changeY[i];

                    if (waterNextX < 0 || waterNextX > R - 1 || waterNextY < 0 || waterNextY > C - 1)
                        continue; // 맵 범위 초과 pass

                    if (map[waterNextX][waterNextY] != 9 && visited[waterNextX][waterNextY])
                        continue; // 방문한적 있으면 pass

                    if (map[waterNextX][waterNextY] == 7)
                        continue;

                    if (map[waterNextX][waterNextY] == 1)
                        continue;

                    waterQ.add(new int[]{waterNextX, waterNextY});
                    visited[waterNextX][waterNextY] = true;
                    map[waterNextX][waterNextY] = 8;
                }

            }

            while (count < size) {
                node = queue.poll();
                count++;
                nowX = node[0];
                nowY = node[1];
                for (int i = 0; i < 4; i++) {
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];

                    if (nextX < 0 || nextX > R - 1 || nextY < 0 || nextY > C - 1)
                        continue; // 맵 범위 초과 pass

                    if (visited[nextX][nextY])
                        continue; // 방문한적 있으면 pass

                    if (map[nextX][nextY] == 1 || map[nextX][nextY] == 8)
                        continue;

                    queue.add(new int[]{nextX, nextY});

                    visited[nextX][nextY] = true;
                    map[nextX][nextY] = 4;
                    answerTimes[nextX][nextY] = answerTimes[nowX][nowY] + 1;
                }
            }
        }
    }
}

