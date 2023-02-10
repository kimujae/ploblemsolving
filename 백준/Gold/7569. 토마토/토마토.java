import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    //메모리 실행시간 줄인는 방법 알아보기
    public static int[][][] map, dist;
    public static Queue<int[]> queue = new LinkedList<int[]>();
    public static int N, M, H;
    public static Scanner scan = new Scanner(System.in);
    public static int[] changeX = {-1, 0, 1, 0, 0, 0};
    public static int[] changeY = {0, -1, 0, 1, 0, 0};

    public static int[] changeZ = {0, 0, 0, 0, 1, -1};


    public static boolean[][][] visited;
    public static int tomatocount;
    public static int answer;

    public static void main(String[] args) {
        N = scan.nextInt();
        M = scan.nextInt();
        H = scan.nextInt();
        map = new int[H][M][N];
        dist = new int[H][M][N];
        visited = new boolean[H][M][N];
        tomatocount = 0;

        for(int hInput = 0; hInput < H; hInput++) {
            for (int iInput = 0; iInput < M; iInput++) {
                for (int jInput = 0; jInput < N; jInput++) {
                    int number = scan.nextInt();
                    if (number == 1) queue.add(new int[]{hInput,iInput, jInput});
                    if (number == 0) tomatocount++;
                    map[hInput][iInput][jInput] = number;
                }
            }//map정보완성
        }
        //System.out.println(map[1][1][2]);

        Bfs();
        if(tomatocount > 0) System.out.print(-1);
        else System.out.print(answer);
    }

    static void Bfs() {
        int size, count;
        while (!queue.isEmpty()) {
            size = queue.size();
            count = 0;
            while (count < size){
                int[] now = queue.poll();
                int nowZ = now[0];
                int nowX = now[1];
                int nowY = now[2];
                count++;
                answer = dist[nowZ][nowX][nowY];
                visited[nowZ][nowX][nowY] = true;

                for (int i = 0; i < 6; i++) {
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];
                    int nextZ = nowZ + changeZ[i];

                    if (nextX < 0 || nextX > M - 1 || nextY < 0 || nextY > N - 1 || nextZ < 0 || nextZ > H - 1)
                        continue;

                    if (map[nextZ][nextX][nextY] == -1)
                        continue;

                    if (map[nextZ][nextX][nextY] == 1)
                        //이게 핵심...
                        continue;

                    if(visited[nextZ][nextX][nextY])
                        continue;

                    visited[nextZ][nextX][nextY] = true;
                    queue.add(new int[]{nextZ, nextX, nextY});
                    tomatocount--;
                    dist[nextZ][nextX][nextY] = dist[nowZ][nowX][nowY] + 1;
                }
            }
        }
    }
}
