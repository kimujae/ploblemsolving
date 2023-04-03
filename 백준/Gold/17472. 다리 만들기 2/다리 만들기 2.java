import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2){
            return o1[2] - o2[2];
        }
    });
    static Queue<int[]> q = new LinkedList<>();

    static class World{
        int groundNum;
        boolean ground;
        boolean east;
        boolean west;
        boolean south;
        boolean north;// 동서남북

        World(boolean ground){
            this.ground = ground;// 땅 여부
        }
    }
    static int N, M, ans;
    static World[][] map;
    static boolean[][] visited;
    static int[] changeX = {-1, 1, 0, 0}; // 북 남
    static int[] changeY = {0, 0, -1, 1}; // 서 동
    static int[] parent;

    public static void main(String[] args)throws IOException {
        /*
        1. dfs를 통해 각 땅의 번호를 붙인다.
        2. 땅의 개수 -1 회 다리의 최단 거리를 파악한다.
        3. 최소 스패닝 트리를 위해 우선순위 큐에 삽입한다?
         */

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new World[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) map[i][j] = new World(true);
                else map[i][j] = new World(false);
            }
        }// 맵 입력 완료

        int groundNum = 0;
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j] && map[i][j].ground) dfs(i,j, ++groundNum);
            }
        }// 땅 번호 부여


        visited = new boolean[N][M];
        bfs();


        makeSet(groundNum);
        int count =  0;
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int x = now[0];
            int y = now[1];
            int dist = now[2];

            if(find(x) != find(y)){
                union(x, y);
                ans += dist;
                count++;
            }
            if(count == groundNum-1) break;
        }


        if(count == groundNum-1)System.out.print(ans);
        else System.out.print(-1);
    }

    static void bfs(){
        // 바다와 인접한 땅과 방향이 q에 들어 있고, 동시에 최단거리를 계산한다.
        while(!q.isEmpty()){
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            int vector = now[2];
            int dist = now[3];
            int nowGround = now[4];

            int nextX = nowX + changeX[vector];
            int nextY = nowY + changeY[vector];

            if(nextX < 0 || nextX > N-1 || nextY <0 || nextY > M-1)
                continue;


            if(map[nextX][nextY].ground ){
                // 다리를 연결하다가 땅을 만났다면.
                // 여기의 조건을 디테일하게 생각 못했다... 반례는 아래
                if(nowGround != map[nextX][nextY].groundNum && dist > 1) pq.add(new int[]{nowGround, map[nextX][nextY].groundNum, dist});
                continue;
            }

            q.add(new int[]{nextX, nextY, vector, dist+1, nowGround});
        }

    }
    static void dfs(int x, int y, int groundNum){
        visited[x][y] = true;
        map[x][y].groundNum = groundNum;
        for(int i = 0; i < 4; i++){
            int nextX = x + changeX[i];
            int nextY = y + changeY[i];

            if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1)
                continue;

            if(visited[nextX][nextY])
                continue;

            if(!map[nextX][nextY].ground){
                //땅이 아닌 바다라면,
                if(i == 0) map[x][y].north  =true;
                else if(i == 1) map[x][y].south = true;
                else if(i == 2) map[x][y].west = true;
                else map[x][y].east = true;

                q.add(new int[]{x, y, i, 0, groundNum});
                continue;
            }

            dfs(nextX, nextY, groundNum);
        }
    }

    static int find(int x){
        if(x == parent[x]) return x;

        int y = find(parent[x]);
        parent[x]  = y;
        return y;
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            if(x < y) parent[y] = x;
            else parent[x] = y;
        }
    }

    static void makeSet(int groundNum){
        parent = new int[groundNum+1];
        for(int i = 0; i < groundNum+1; i++){
            parent[i] = i;
        }
    }
}