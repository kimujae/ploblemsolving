import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{
    public static int[][] something;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int[][] memo;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int pack = Integer.parseInt(st.nextToken());
        something = new int[N][2];
        memo = new int[pack + 1][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            something[i][0] = Integer.parseInt(st.nextToken());
            something[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int bag = 1; bag <= pack; bag++){
            for(int j = 0; j < N; j++){ //물건 선택
                int weight = bag - something[j][0];
                if(weight >= 0){ //배낭에 선택 물건을 넣을 수 있을 때
                    if(j != 0) memo[bag][j] = Math.max(memo[weight][j-1] + something[j][1], memo[bag][j-1]);
                    else memo[bag][j] = something[j][1];
                }
                else { // 배낭에 선택 물건을 넣을 수 없을 때
                    if(j != 0) memo[bag][j] = memo[bag][j-1];
                    else memo[bag][j] = 0;
                }
            }
        }

        System.out.println(memo[pack][N-1]);
    }
}
