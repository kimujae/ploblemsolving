import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static Queue<int[]> meltQ = new LinkedList<>();
    static Queue<int[]> queue = new LinkedList<>();

    static boolean[][] visited;
    static int[][] map;
    static int[] parent, L;
    static int R, C, days;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] changeX = {-1, 0, 1, 0};
    static int[] changeY = {0, 1, 0, -1};


    /*
    이동할 수 있는 물(영역) 을 집합덩어리로 본다.
    영역확장을 하면서 빙판 -> 물로 녹으면 union을 진행
    만약 백조들이 서로 같은 집합에 속하면(이동할 수 있다면) break;
     */

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];
        L = new int[2]; // 백조 위치정보 저장
        int l = 0;
        makeSet();

        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                if(str.charAt(j) == '.'){
                    //이동할 수 있는 물
                    map[i][j] = 0;
                }
                else if(str.charAt(j) == 'X'){
                    //이동할 수 없는 빙판
                    map[i][j] = 1;
                }
                else if(str.charAt(j) == 'L') {
                    //백조
                    map[i][j] = 9;
                    if(l == 0){
                        L[0] = i* C + j;
                        l++;
                    }
                    else L[1] = i* C + j;
                }
            }
        }//map 완성

        //1. 물영역파악
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] == 0 || map[i][j] == 9 && !visited[i][j]) bfs(i, j);
            }
        }



        //2. 탐색을 통해 빙판을 녹임
        melt();
        bw.write(String.valueOf(days));
        bw.flush();
    }


    static void melt(){
        while(!meltQ.isEmpty()) {
            int count = 0;
            int size = meltQ.size();

            if(find(L[0]) == find(L[1])) break;

            //동시에 모두 녹임
            while (count < size) {

                int[] now = meltQ.poll();
                count++;
                int nowX = now[0];
                int nowY = now[1];

                for (int i = 0; i < 4; i++) {
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];

                    if (nextX < 0 || nextX > R - 1 || nextY < 0 || nextY > C - 1)
                        continue;

                    if (find(nowX*C + nowY) == find(nextX*C + nextY))
                        continue;

                    if (map[nextX][nextY] == 9)
                        continue;

                    if(map[nextX][nextY] == 0 && find(nowX * C + nowY) != find(nextX * C + nextY)){
                        union(nowX * C + nowY, nextX * C + nextY);
                    }


                    visited[nextX][nextY] = true;
                    map[nextX][nextY] = 0;
                    union(nextX * C + nextY, nowX * C + nowY);
                    checkMovable(nextX, nextY);
                    meltQ.add(new int[]{nextX, nextY});
                    // 집합 합체

                }
            }
            days++;

            /*

            for(int i = 0; i < R; i++){
                for(int j  = 0; j < C; j++){
                    System.out.print(parent[i*C + j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
             */

        }
    }

    static void bfs(int x, int y){
        queue.add(new int[]{x, y});
        meltQ.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > R-1 ||  nextY < 0 || nextY > C-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(map[nextX][nextY] == 1)
                    continue;

                visited[nextX][nextY] = true;
                queue.add(new int[]{nextX, nextY});
                meltQ.add(new int[]{nextX, nextY});
                // 집합 합체
                union(nextX * C + nextY, nowX * C + nowY);
            }
        }
    }

    static void checkMovable(int x, int y){
        for(int i = 0; i < 4; i++){
            int nextX = x + changeX[i];
            int nextY = y + changeY[i];

            if(nextX < 0 || nextX > R-1 || nextY < 0 || nextY > C-1)
                continue;

            if(map[nextX][nextY] == 0) {
                union(nextX * C + nextY, x*C + y);
            }
        }
    }


    static void makeSet(){
        parent = new int[R*C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                parent[i*C + j] = i*C + j;
            }
        }
    }
    static int find(int x){
        if(parent[x] == x) return x;
        else{
            int y = find(parent[x]);
            parent[x] = y;
            return y;
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;
        if(x > y) parent[x] = y;
        else parent[y] = x;

    }
}
