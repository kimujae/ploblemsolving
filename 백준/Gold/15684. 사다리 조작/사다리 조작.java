import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M, H, ans;
    private static boolean f = false;
    private static int[][] map;
    private static ArrayList<int[]> loc = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H+1][N];
        for(int i = 0; i < M; i++) {
            st =new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y =  Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1;
            map[x][y+1] = 2;
        }
        ans = -1;
        for(int i = 0; i < 4; i++) {
            if(f) break;
            dfs(0, 0, i);
        }

        System.out.println(ans);
    }

    public static void dfs(int x, int depth, int l) {
        if(depth == l) {
            if(f) return;

            boolean isOk = true;
            for(int i = 0; i < N; i++) {
                int num = move(new int[]{0, i});
                if(num != i) {
                    isOk = false;
                    break;
                }
            }

            if(isOk) {
                f = true;
                ans = l;
            }
            return;
        }

        
        for(int i = x; i < H; i++) {
            for(int j = 0; j < N - 1; j++) {
                if(f) return;
                if(map[i][j] != 0 || map[i][j + 1] != 0) continue;

                map[i][j] = 1;
                map[i][j + 1] = 2;
                if(j == N - 2) dfs(i + 1, depth + 1, l);
                else dfs(i , depth + 1, l);
                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
        }


    }

    public static int move(int[] num) {
        int y = num[1];
        for(int x = 0; x < H+1; x++) {
            if(map[x][y] == 1) y++;
            else if(map[x][y] == 2) y--;
        }
        return y;
    }

}
