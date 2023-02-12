import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static int[][]map;
    public static boolean[][] visited;
    public static int T, N, M, K;
    public static Scanner scan = new Scanner(System.in);
    public static Queue<int[]> queue = new LinkedList<int[]>();
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};



    public static void main(String[] args) {
        T = scan.nextInt();


        for(int test = 0 ; test < T; test++){
            int answer = 0;
            N = scan.nextInt();
            M = scan.nextInt();
            K = scan.nextInt();
            map = new int[N][M];
            visited = new boolean[N][M];
            for(int cabbage = 0; cabbage < K; cabbage++){
                map[scan.nextInt()][scan.nextInt()] = 1;
            }// map완성
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] == 1) {
                        Bfs(i, j);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }


    static void Bfs(int nowX, int nowY){
        visited[nowX][nowY] = true;
        queue.add(new int[]{nowX, nowY});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            nowX = now[0];
            nowY = now[1];
            for(int i =0; i < 4 ; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(map[nextX][nextY] == 0)
                    continue;

                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
                map[nextX][nextY] = 0;
            }
        }
    }
}
