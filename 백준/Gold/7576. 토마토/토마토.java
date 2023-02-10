import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static int[][] map, dist;
    public static Queue<int[]> queue = new LinkedList<int[]>();
    public static int N, M;
    public static Scanner scan = new Scanner(System.in);
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};
    public static boolean[][] visited;
    public static int tomatocount;
    public static int answer;

    public static void main(String[] args) {
        N = scan.nextInt();
        M = scan.nextInt();
        map = new int[M][N];
        dist = new int[M][N];
        visited = new boolean[M][N];
        tomatocount = 0;

        for (int iInput = 0; iInput < M; iInput++) {
            for (int jInput = 0; jInput < N; jInput++) {
                int number = scan.nextInt();
                if (number == 1) queue.add(new int[]{iInput, jInput});
                if (number == 0) tomatocount++;
                map[iInput][jInput] = number;
            }
        }//map정보완성

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
                int nowX = now[0];
                int nowY = now[1];
                count++;
                answer = dist[nowX][nowY];
                visited[nowX][nowY] = true;

                for (int i = 0; i < 4; i++) {
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];

                    if (nextX < 0 || nextX > M - 1 || nextY < 0 || nextY > N - 1)
                        continue;

                    if (map[nextX][nextY] == -1)
                        continue;

                    if (map[nextX][nextY] == 1)
                        //이게 핵심...
                        continue;

                    if(visited[nextX][nextY])
                        continue;

                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                    tomatocount--;
                    dist[nextX][nextY] = dist[nowX][nowY] + 1;
                }
            }
        }
    }
}
