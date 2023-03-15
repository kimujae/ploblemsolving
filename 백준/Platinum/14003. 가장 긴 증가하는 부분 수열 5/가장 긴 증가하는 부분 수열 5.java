import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, ans, max;
    static int[] A, dp, lis;
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> stack = new Stack();

    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];
        lis = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }//수열 입력 완료


        tablation();
        sb.append(max).append('\n');
        push();

        while(stack.size()>0){
            sb.append(stack.pop()).append(" ");
        }
        System.out.printf(sb.toString());
    }

    static void tablation(){
        // tablation dp
        lis[0] = A[0];
        dp[0] = 1;
        max = 1;
        for(int i = 1; i < N; i++) {
            dp[i] = 1;
            int left = 0;
            int right = max-1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (A[i] <= lis[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            dp[i] = left+1;
            lis[left] = A[i];
            max = Math.max(max, left + 1);
        }
    }


    static void push(){
        int i = N-1;
        int count  = max;

        while(count != 0){
            if(i == -1) break;
            if(dp[i] == count){
                stack.push(A[i]);
                count--;
            }
            i--;
        }
    }
}