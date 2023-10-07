import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int N, L;
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int ans;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // row check
       for(int i = 0; i < N; i++) {
           boolean[] v = new boolean[N];
           int prev = map[i][0];
           int curr = 0;
           int seq = 1;

           check :
           for(int j = 1; j <= N; j++) {
               if(j == N) {
                   // 도달
                   ans ++;
                   continue;
               }


               curr = map[i][j];
               if(prev == curr) {
                   seq++;
                   continue;
               }

               if(prev == curr + 1) {
                   if(j + L > N) break check;

                   for(int road = j; road < j + L; road++) {
                       if(map[i][road] != curr) break check;
                   }
                   j += L; // 경사로 다음
                   if(j == N) {
                       ans++;
                       continue ;
                   }
                   if(map[i][j] > curr) break check;
                   if(curr - map[i][j] >= 2) break check;


                   seq = 0;
                   prev = curr;

                   j -= 1;
                   continue;
               }

               if(prev + 1 == curr) {
                   if(seq < L) break check;

                   prev = curr;
                   seq = 1;
                   continue;
               }

               else break check;
           }
       }

        // col check
        for(int i = 0; i < N; i++) {
            boolean[] v = new boolean[N];
            int prev = map[0][i];
            int curr = 0;
            int seq = 1;

            check :
            for(int j = 1; j <= N; j++) {
                if(j == N) {
                    // 도달
                    ans ++;
                    continue;
                }


                curr = map[j][i];
                if(prev == curr) {
                    seq++;
                    continue;
                }

                if(prev == curr + 1) {
                    if(j + L > N) break check;

                    for(int road = j; road < j + L; road++) {
                        if(map[road][i] != curr) break check;
                    }
                    j += L; // 경사로 다음
                    if(j == N) {
                        ans++;
                        continue ;
                    }
                    if(map[j][i] > curr) break check;
                    if(curr - map[j][i] >= 2) break check;


                    seq = 0;
                    prev = curr;

                    j -= 1;
                    continue;
                }

                if(prev + 1 == curr) {
                    if(seq < L) break check;

                    prev = curr;
                    seq = 1;
                    continue;
                }

                else break check;

            }
        }



        System.out.println(ans);
    }
}
