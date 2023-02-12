import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static int[][]map, dist;
    public static boolean[][] visited;
    public static int N, M;
    public static Scanner scan = new Scanner(System.in);
    public static Queue<int[]> queue = new LinkedList<int[]>();
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};

    public static void main(String[] args) {
        N = scan.nextInt();
        M = scan.nextInt();
        map= new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M];
        int max = 0;

        for(int i = 0 ; i < N; i++){
            String str = scan.next();
            for(int j =0; j < M; j++){
                if(str.charAt(j)=='W'){
                    map[i][j] = 0;
                    visited[i][j] = true;
                }
                else map[i][j] = 1;
            }
        }// map완성


        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1){
                    visited = new boolean[N][M];
                    dist = new int[N][M];
                    max = Math.max(max, Bfs(i, j));
                }
            }
        }
        System.out.println(max);

    }

    static int Bfs(int nowX, int nowY){
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
                dist[nextX][nextY]  = dist[nowX][nowY] + 1;
            }
        }
        return dist[nowX][nowY];
    }

}
