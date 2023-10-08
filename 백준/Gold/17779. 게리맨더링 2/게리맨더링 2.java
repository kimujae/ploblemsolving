import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        map = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;
        // x, y를 기준점으로 정함
        for(int x = 1; x < N+1; x++) {
            for(int y = 1; y < N+1; y++) {
                //경계의 길이를 정함
                for(int d1 = 1; d1 <= N; d1++) {
                    for(int d2 = 1; d2 <= N; d2++) {
                       if(!((x < x + d1 + d2 && x + d1 + d2 <= N) &&
                                (1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N))) continue;

                        int[][] m = new int[N+1][N+1];
                        drawLine(x, y, d1, d2, m);
                        int res = checkPeople(
                                new int[]{
                                        1, x + d1,
                                        1, x + d2,
                                        x + d1, N,
                                        x + d2, N
                                        },
                                new int[]{
                                        1, y,
                                        y, N,
                                        1, y - d1 + d2,
                                        y - d1 + d2, N
                                }, d1, d2 , m);

                        ans = Math.min(ans, res);
                        // 1, 2, 3, 4번 선거구 bfs 이후 5번 선거구 bfs
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static int checkPeople(int[] rRange, int[] cRange, int d1, int d2, int[][] m) {
        int[] p = new int[6];
        boolean[][] v = new boolean[N+1][N+1];
        int a = 0;

        for(int i = rRange[0] ; i < rRange[1] ; i++) {
            for(int j = cRange[0] ; j <= cRange[1] ; j++) {
                if (m[i][j] == 5) break;

                p[1] += map[i][j];
                v[i][j] = true;
            }
        }

        for(int i = rRange[4] ; i <= rRange[5] ; i++) {
            for(int j = cRange[4] ; j < cRange[5] ; j++) {
                if(m[i][j] == 5) break;

                p[3] += map[i][j];
                v[i][j] = true;
            }
        }

        for(int i = rRange[2] ; i <= rRange[3] ; i++) {
            for(int j = cRange[3] ; j > cRange[2] ; j--) {
                if(m[i][j] == 5) break;

                p[2] += map[i][j];
                v[i][j] = true;
            }
        }

        for(int i = rRange[6] + 1 ; i <= rRange[7] ; i++) {
            for(int j = cRange[7] ; j >= cRange[6] ; j--) {
                if(m[i][j] == 5) break;

                p[4] += map[i][j];
                v[i][j] = true;
            }
        }

        for(int i = 1; i < N+1; i++) {
            for(int j =1; j < N+1; j++) {
                if(v[i][j]) continue;

                p[5] += map[i][j];
            }
        }


        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int idx = -1;
        for(int i : p) {
            idx++;
            if(idx == 0) continue;


            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        return max - min;

    }

    public static int[][] drawLine(int x, int y, int d1, int d2, int[][] m) {
        for(int i = x, j = y; i <= x+ d1; i++, j--) {
            for(int k = i, p = j; k <= i + d2; k++, p++) {
                m[k][p] = 5;
            }
        }
        return m;
    }
}
