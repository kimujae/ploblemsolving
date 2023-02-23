import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, dist;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    static int testCase, w,h, ans;
    static int[] changeX ={-1, 0, 1,0};
    static int[] changeY = {0, -1, 0, 1};
    static int[] position;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());


        for(int t = 0; t < testCase; t++) {
            init();
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    if (str.charAt(j) == '#') map[i][j] = 1; // 벽이면 1
                    else if (str.charAt(j) == '*') {
                        map[i][j] = 2; // 불이면 2
                        queue.add(new int[]{i, j});
                        visited[i][j] = true;
                    } else if (str.charAt(j) == '@') {
                        map[i][j] = 9; // 상근이는 9
                        position = new int[]{i, j};
                    } else map[i][j] = 0; // 이동할 수 있는 곳이면 0
                }
            }// map완성
             queue.add(position);// 상근이의 위치를 불을 모두 넣은 이후 add

            bfs();
/*
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    System.out.print(dist[i][j]);
                }
                System.out.println();
            }


 */

            if ((ans = checkExit()) != Integer.MAX_VALUE) bw.write(String.valueOf(ans));
            else bw.write("IMPOSSIBLE");
            bw.write('\n');
        }
        bw.flush();
    }

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        dist = new int[h][w];
        visited = new boolean[h][w];
    }
    static void bfs(){
        while(!queue.isEmpty()){
            int count = 0;
            int size = queue.size();
            while(count < size){
                int[] now = queue.poll();
                count++;
                int nowX = now[0];
                int nowY = now[1];
                if(now == position) visited[nowX][nowY] = true;


                for(int i = 0; i < 4; i++){
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];

                    if(nextX < 0 || nextX > h-1 || nextY < 0 || nextY > w-1)
                        continue;

                    if(visited[nextX][nextY])
                        continue;

                    if(map[nextX][nextY] == 1)// 벽인 경우 불, 상근 모두 이동 불가
                        continue;

                    // 불이 먼저 영역확장을 하니까 불에대한 pass조건을 하지 않아도 된다.


                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX,nextY});

                    //현재 탐색 중인게 상근이인 경우
                    //여기서 map[nowX][nowY] = 0을 해준것이 tc실패원인.
                    //무지성으로 하지말고 설계하고 쓰자.
                    if(map[nowX][nowY] == 9) {
                        map[nextX][nextY] = 9;
                        dist[nextX][nextY] = dist[nowX][nowY] + 1;
                    }
                }
            }
        }
    }


    static int checkExit(){
        int min = Integer.MAX_VALUE;

        for(int j = 0; j < w; j++){
            if(map[0][j] == 9) min = Math.min(min, dist[0][j]+1);
        }
        for(int j = 0; j < w; j++){
            if(map[h-1][j] == 9) min = Math.min(min, dist[h-1][j]+1);
        }
        for(int i = 1; i < h-1; i++){
            if(map[i][0] == 9) min = Math.min(min, dist[i][0]+1);
        }
        for(int i = 1; i < h-1; i++){
            if(map[i][w-1] == 9) min = Math.min(min, dist[i][w-1]+1);
        }

        return min;
    }
}
/*

 */
