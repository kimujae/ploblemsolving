import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader((System.in)));
    private static int N;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[2][N+1];
        StringTokenizer st;
        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(1, 0));
    }
    static int dfs(int depth, int sum) {
        if(depth == N) {
            if (arr[0][depth] == 1) return sum + arr[1][depth]; // 퇴사일과 마감일이 같은경우 더함
            else return sum; // 퇴사일 이후 마감일에 대해서 못함
        }
        if(depth > N) return sum; //

        int max = 0;
        int next = arr[0][depth] + depth;

        max = Math.max(max, dfs(depth + 1, sum));

        if(next > N+1) return max;
        else return Math.max(max, dfs(next, sum + arr[1][depth]));
    }
}
