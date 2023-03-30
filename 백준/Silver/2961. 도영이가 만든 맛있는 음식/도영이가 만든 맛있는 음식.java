import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, ans;
    static int[][] ingre;

    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        ingre = new int[N][2];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int sour = Integer.parseInt(st.nextToken());
            int bitter = Integer.parseInt(st.nextToken());
            ingre[i][0] = sour;
            ingre[i][1] = bitter;
        }//재료 입력 완료

        int sour  = 1;
        int bitter = 0;
        ans = Integer.MAX_VALUE;
        for(int i = 0; i < 1<<N; i++){
            sour = 1;
            bitter = 0;
            for(int k = 0; k < N; k++){
                if((i & 1<<k) != 0) {
                    sour *= ingre[k][0];
                    bitter += ingre[k][1];
                }
            }
            if(sour == 1 || bitter == 0)
                continue;
            ans = Math.min(ans, Math.abs(sour-bitter));
        }

        System.out.print(ans);
    }
}
