import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, ans;

    static int[] arr;
    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        ans = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i= 0; i< N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }//배열 완성

        int start= 0, end = 0;
        int sum = 0;
        while(true){
            if(sum >= M) sum -= arr[start++]; // start 이동조건
            else if(end >= N) break; //탈출조건
            else sum += arr[end++]; //end 이동조건
            
            if(sum >= M) ans = Math.min(ans, end - start); //정답조건
        }
        if(ans == Integer.MAX_VALUE) System.out.print(0);
        else System.out.print(ans);
    }
}