import java.io.*;
import java.util.StringTokenizer;
public class Main {
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] triangle;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        triangle = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++ ){
               triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }//정수 삼각형 완성

        bw.write(String.valueOf(dp(0, 0)));
        bw.flush();
    }

    static int dp(int floor, int y){
        if(floor == n-1){
            visited[floor][y] = true;
            return triangle[floor][y];
        }

        if(visited[floor][y]) return triangle[floor][y];
        visited[floor][y] = true;

        return triangle[floor][y] += Math.max(dp(floor+1, y), dp(floor+1,y+1));
    }
}
