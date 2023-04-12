import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    문없이 획득할 수 있는 열쇠는 바로 추가한다.
    bfs를 통해 Map<Character, ArrayList<Character>> door 에 문:열쇠 혹은 문:문 혹은 문:문서 정보를 담는다.
    열쇠가 생길때마다, Map을 탐색한다.
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int keys;
    static int t, h, w;
    static int[][] map;
    static int[][] visited;
    static int[] changeX = {1, -1, 0 , 0};
    static int[] changeY = {0, 0 ,-1, 1};


    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            keys = 0;
            visited = new int[w+2][h+2]; // 키를 가지고 다닐 경우의 수 -> 완전탐색 활용
            map = new int[w+2][h+2]; // 아무것도 없는 테두리 처리 하는 것이 키포인트였다...... 나머지는 ok
            
            for(int j = 0; j < w+2; j++) {
                Arrays.fill(visited[j], -999);
            }// keys 가 0일 경우를 위해 음수로 채움

            
            for(int j = 1 ; j <= w; j++){
                String str = br.readLine();
                for(int k = 1 ; k <= h; k++){
                    if(str.charAt(k-1) == '*') map[j][k] = 1;
                    else if(str.charAt(k-1) == '.') map[j][k] = 0;
                    else if(str.charAt(k-1) == '$') map[j][k] = -1;
                    else {
                        map[j][k] = (int)str.charAt(k-1) - 'A' + 10;
                    }
                }
            }

            String key = br.readLine();
            if(!key.equals("0")) {
                for (int j = 0; j < key.length(); j++) {
                    keys |= 1<<(key.charAt(j) - 'A' -32);
                }
            }// 갖고 있는 키 입력 완료

            System.out.println(bfs(0,0));
        }
    }

    static int bfs(int x, int y){
        /*
        10~36 문
        42~68 열쇠
         */
        Queue<int[]> bfsQ = new LinkedList<>();
        bfsQ.add(new int[]{x, y});
        visited[x][y] = keys;
        int ans = 0;

        while(!bfsQ.isEmpty()) {
            int[]n = bfsQ.poll();
            int nowX = n[0];
            int nowY = n[1];


            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextX > w+1 || nextY < 0 || nextY > h+1)
                    continue;

                if(visited[nextX][nextY] == keys || map[nextX][nextY] == 1)
                    continue;

                visited[nextX][nextY] = keys;



                //문서를 만난경우
                if(map[nextX][nextY] == -1) ans += 1;

                //열쇠를 만난 경우
                if(42 <= map[nextX][nextY] && map[nextX][nextY] <=67) {
                    keys |= 1 << (map[nextX][nextY] - 42);
                }
                
                //문을 만난경우
                if(10 <= map[nextX][nextY] && map[nextX][nextY] <= 35) {
                    if ((keys & 1<< (map[nextX][nextY] - 10)) == 0) continue;
                }

                
                //문서, 열쇠, 문 만나면 없애고 큐에 넣고 탐색 실행
                map[nextX][nextY] = 0;
                bfsQ.add(new int[]{nextX, nextY});
            }
        }
        return ans;
    }


}
