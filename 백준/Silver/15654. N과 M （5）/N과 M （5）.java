import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] nums, comb;
    static boolean[] visited;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        comb = new int[M];
        visited = new boolean[N];

        st= new StringTokenizer(br.readLine());
        for(int i =0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }//수열 입력 완료
        Arrays.sort(nums);

        combination(0);
        System.out.print(sb);
    }

    static void combination(int depth){
        if(depth == M){
            for(int number : comb){
                sb.append(String.valueOf(number)).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i = 0; i < N; i++){
            //순열이 아니라 수열문제다. 따라서 i = 0부터 시작
            if(visited[i]) continue;
            visited[i] = true;
            comb[depth] = nums[i];
            combination(depth+1);
            visited[i] = false;
        }
    }
}
