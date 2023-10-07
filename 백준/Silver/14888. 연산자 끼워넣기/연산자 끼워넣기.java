import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
    private static StringTokenizer st;
    private static int N, plus, minus, div, multi;
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;
    private static int[] nums;
    public static void main(String args[]) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        multi = Integer.parseInt(st.nextToken());
        div = Integer.parseInt(st.nextToken());


        dfs(0, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int depth, int result) {
        if(depth == N - 1) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }


        // 사칙연산 순회

        if(plus > 0) {
            plus -= 1;
            dfs(depth+1, result + nums[depth + 1]);
            plus += 1;
        }

        if(minus > 0) {
            minus -= 1;
            dfs(depth+1, result - nums[depth + 1]);
            minus += 1;
        }

        if(div > 0) {
            div -= 1;
            int nextResult = result/nums[depth + 1];
            boolean currInt = true;
            boolean nextInt = true;

            if(result < 0) currInt = false;
            if(nums[depth + 1] < 0) nextInt = false;

            if(!currInt == nextInt) nextResult = - (Math.abs(result) / Math.abs(nums[depth + 1]));
            dfs(depth+1, nextResult);
            div += 1;
        }

        if(multi > 0) {
            multi -= 1;
            dfs(depth+1, result * nums[depth + 1]);
            multi += 1;
        }
    }
}
