import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] ans;
    static int N;
    static boolean[] visited;
    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        ans = new int[N];
        visited = new boolean[N+1];
        dfs(0, 0);
        System.out.println(sb);
    }

    static void dfs(int depth, int bitmask){
        if(depth == N){
            for(int a : ans){
                sb.append(a).append(' ');
            }
            sb.append('\n');
        }

        for(int i = 1; i <= N; i++){
            //if((bitmask & 1<< i) != 0)
            if(visited[i])
                continue;

            //bitmask |= 1<<i;
            visited[i] = true;
            ans[depth] = i;
            dfs(depth+1, bitmask);
            visited[i]=false;
            //bitmask &= ~(1<<i);
        }
    }
}
