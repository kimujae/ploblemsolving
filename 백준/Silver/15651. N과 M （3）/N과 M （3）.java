import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        combination(0,  new int[M]);
        System.out.println(sb.toString());
    }

    public static void combination(int depth ,  int[] n) {
        if(depth == M) {
            for(int i : n){
                sb.append(i);
                sb.append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i = 1; i <= N; i++) {
            n[depth] = i;
            combination(depth+1, n);
        }
    }
}
