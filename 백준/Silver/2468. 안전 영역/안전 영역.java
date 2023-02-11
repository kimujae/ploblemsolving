import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static int[][] map, checkMap;
    public static boolean[][] visited;
    //1930
    public static int N, max, count, answer;
    public static int[] changeX = {-1, 0 , 1, 0};
    public static int[] changeY = {0, -1 , 0, 1};
    public static Scanner scan = new Scanner(System.in);
    public static Queue<int[]> queue = new LinkedList<int[]>();

    public static void main(String[] args) {
        max = Integer.MIN_VALUE;
        answer = Integer.MIN_VALUE;

        N = scan.nextInt();
        map = new int[N][N];
        checkMap = new int[N][N];

        for(int iInput = 0; iInput < N; iInput++){
            for(int jInput = 0; jInput < N; jInput++){
                map[iInput][jInput] = scan.nextInt();
                if(map[iInput][jInput] > max) max = map[iInput][jInput];
            }
        }


        for(int iter = 0 ; iter <= max ; iter++) {
            visited = new boolean[N][N];
            count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j] == false && map[i][j] > iter) {
                        bfs(i, j , iter);
                        count++;
                    }
                }
            }
            if(count > answer) answer = count;
        }

        System.out.println(answer);
    }


    static void bfs(int nowX , int nowY, int rain){
       
        visited[nowX][nowY] = true;
        queue.add(new int[]{nowX, nowY});

        while(!queue.isEmpty()){
            int[] node = queue.poll();
            nowX = node[0];
            nowY = node[1];
            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > N-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(rain >= map[nextX][nextY])
                    continue;

                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
                checkMap[nextX][nextY] = 1;
            }
        }
    }
}
