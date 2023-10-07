import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] s;
    private static StringTokenizer st;
    private static boolean[] v;
    private static int min = Integer.MAX_VALUE;
    private static int[] start, link;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        s = new int[N][N];
        v = new boolean[N];
        start = new int[N/2];
        link = new int[N/2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(min);

    }
    private static void combination(int depth, int stpl) {
        if(depth == N || stpl == N/2) {
            if(stpl < N/2) return;

            int st = 0;
            int li = 0;
            for(int i = 0; i < N/2; i++) {
                int player = start[i];
                for(int j = 0; j < N; j++) {
                    if(v[j])st += s[player][j];
                }
            }

            int idx = 0;
            int p = 0;
            for(boolean b : v){
                if(!b) link[p++] = idx;
                idx += 1;
            }


            for(int i = 0; i < N/2; i++) {
                int player = link[i];
                for(int j = 0; j < N; j++) {
                    if(!v[j])li += s[player][j];
                }
            }

            min = Math.min(min, Math.abs(st - li));
            return;
        }



        start[stpl] = depth;
        v[depth] = true;
        combination(depth+1, stpl + 1);
        v[depth] = false;
        start[stpl] = 0;

        combination(depth + 1, stpl);
    }
}
