import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, ans, max;
    static int[] A, dp, lis;

    public static void main(String[] args)throws IOException{
        /*
        매개변수 탐색 : 매개변수는 증가하는 부분수열의 길이
        타겟 : 길이의 최대값
        범위 : 0 ~ 배열의 크기(N)

        길이를 매개변수로 이분탐색 해야하는 것 같은데..
        매개변수가 통과되는지에 대한 판단이 너무 오래걸린다. -> 기존 dp 활용하면서 매개변수 탐색 할 수는 없나?
         */
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];
        lis = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }//수열 입력 완료


        tablation();
        System.out.print(max);
    }

    static void tablation(){
        // tablation dp
        /*
                1. 정렬 -> 무엇을 기준으로? 증가 수열 길이를 기준으로 :
                정렬의 경우 최악의 복잡도일 때는 n^2인데 정렬로 접근하는 것은 아닌 것 같다.
                2. A[i]보다 작은 A[j]에 대하여 증가 수열 길이의 최대값인 것을 선택하면 된다?
                증가수열 길이의 범위 : 0 ~ j
                이에 대해 매개변수 탐색을 진행? mid값을 놓고 만약 a[i] > a[j]라면 우측값 탐색,
                아니라면, 좌측값 탐색
                 */
        Arrays.fill(lis, Integer.MAX_VALUE);
        lis[0] = A[0];
        dp[0] = 1;
        max = 1;
        for(int i = 1; i < N; i++) {
            dp[i] = 1;
            int left = 0;
            int right = max;
            while (left <= right) {
                int mid = (left + right) / 2;

                if (A[i] <= lis[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            lis[left] = A[i];
            max = Math.max(max, left+1);
        }
    }
}
