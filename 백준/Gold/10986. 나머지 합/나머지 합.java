import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long[] prefix_sum = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            int n = Integer.parseInt(st.nextToken());
            prefix_sum[i] = (prefix_sum[i-1] + n) % M;
        }// 누적합 완료



        /*
        완전 탐색
        start 와 end를 물고 간다.
        start < end break;
        end - start (구간합이)  %M ==0 이면 ans++ , start++;
        end - start 가 3보다 작으면 end ++
        end - start 가 3보다 크면 start++
         */

        int start = 0;
        int end = 1;
        long ans = 0;
        int[] part = new int[N+1];

        while(end <= N){
            if(prefix_sum[end] == prefix_sum[start]) {
                part[end] = part[start]+1;
                ans += part[end];
                start = end;
                end++;
            }
            else {
                if(start > 0)start--;
                else {
                    start = end;
                    end++;
                }
            }
        }
        System.out.println(ans);
    }
}
