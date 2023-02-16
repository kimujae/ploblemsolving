import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static boolean[][] visited;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int T, I;
    public static int[] start, end;
    public static int[] changeX ={-1, -2, 1, 2, 1, 2, -1, -2};
    public static int[] changeY ={-2, -1, 2, 1, -2, -1, 2, 1};

    public static void main(String[] args)throws IOException {

        T = Integer.parseInt(br.readLine());
        for(int input = 0; input < T; input++){
            I = Integer.parseInt(br.readLine());
            visited = new boolean[I][I];
            st = new StringTokenizer(br.readLine());
            start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            st = new StringTokenizer(br.readLine());
            end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            //입력 완료
            Bfs(start[0], start[1]);

        }
    }
    static class Position{
        int x,y,dist;

        public Position(int x, int y, int cnt) {
            super();
            this.x = x;
            this.y = y;
            this.dist = cnt;
        }
    }
    static void Bfs(int startX, int startY){
        Queue<Position> queue = new LinkedList<>();
        visited[startX][startY] = true;
        queue.add(new Position(startX, startY, 0));

        while(!queue.isEmpty()){
            //if(visited[end[0]][end[1]]) break; 이 코드 유무가 왜 결과값의 차이를 가져오나?
            Position cur = queue.poll();
            int nowX = cur.x;
            int nowY = cur.y;

            if(nowX == end[0] && nowY == end[1]){
                System.out.println(cur.dist);
                break;
            }

            for(int i = 0; i < 8; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > I-1 || nextY < 0 || nextY > I-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                queue.add(new Position(nextX, nextY, cur.dist + 1));
                visited[nextX][nextY] = true;
            }
        }
    }
}
