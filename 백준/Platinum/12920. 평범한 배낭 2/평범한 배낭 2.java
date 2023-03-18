import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static ArrayList<int[]> gold = new ArrayList<>();
    static int[][] dp;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        int items = 0;
        gold.add(new int[]{0,0});
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            int exp = 1;
            int number = count;

            while(exp <= number){
                items++;
                gold.add(new int[]{exp*weight, exp*value});
                number -= exp;
                exp *= 2;
            }

            if(number > 0) {
                items++;
                gold.add(new int[]{number*weight, number * value});
            }
        }//물건 정보 입력 완료 (무게, 가치)

        dp = new int[M+1][items+1];
        for(int i = 1; i < M+1; i++){
            for(int j = 1; j < items+1; j++){
                if(i < gold.get(j)[0]) dp[i][j] = dp[i][j-1];
                else dp[i][j] = Math.max(gold.get(j)[1] + dp[i - gold.get(j)[0]][j-1], dp[i][j-1]);
            }
        }
        
        System.out.print(dp[M][items]);
    }
}
