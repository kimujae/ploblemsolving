import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] board;
    static int N, M, fills, min;
    static boolean start, color;

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        board = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == 'B') board[i][j] = true;
                else board[i][j] = false;
            }
        }// 보드 입력 완료

        
        for (int s = 0; s < 2; s++){
            start = !start;
            for (int i = 0; i < N - 7; i++) {
                for (int j = 0; j < M - 7; j++) {
                    color = start;
                    fills = 0;
                    for (int x = i; x < i + 8; x++) {
                        for (int y = j; y < j + 8; y++) {
                            if(color != board[x][y]) fills++;
                            color = !color;
                        }
                        color = !color;
                    }
                    min = Math.min(min, fills);
                }
            }
        }

        System.out.print(min);
    }

}
