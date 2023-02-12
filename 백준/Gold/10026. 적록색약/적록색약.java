import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[][]map;
    public static boolean[][] visited;
    public static int N;
    public static Scanner scan = new Scanner(System.in);
    public static Queue<int[]> queue = new LinkedList<int[]>();
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};

    public static void main(String[] args) {
        N = scan.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String str = scan.next();
            for(int j = 0; j < N; j++){
                if(str.charAt(j) == 'R'){
                    map[i][j] = 1;
                }
                else if(str.charAt(j) == 'G'){
                    map[i][j] = 2;
                }
                else map[i][j] = 3;
            }
        }//map정보 완성

        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j =0; j < N; j++){
                if(visited[i][j] == false) {
                    Bfs(i, j , map[i][j], false);
                    answer++;
                }
            }
        }
        System.out.print(answer + " ");

        answer = 0;
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j =0; j < N; j++){
                if(visited[i][j] == false) {
                    Bfs(i, j , map[i][j], true);
                    answer++;
                }
            }
        }
        System.out.print(answer);

    }


    static void Bfs(int nowX, int nowY, int color, boolean colorweak){
        visited[nowX][nowY] = true;
        queue.add(new int[]{nowX, nowY});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            nowX = now[0];
            nowY = now[1];
            for(int i =0; i < 4 ; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > N-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if((color ==1 || color==2) && colorweak == true && map[nextX][nextY] == 3)
                    continue;

                if((color == 3 || colorweak == false) && map[nextX][nextY] != color)
                    continue;

                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }
    }
}
