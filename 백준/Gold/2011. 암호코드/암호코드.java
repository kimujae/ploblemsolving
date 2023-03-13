import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int max, count, n, answer;
    static boolean error;
    static int[] ans, dp;
    static char curr, prev;

    public static void main(String[] args)throws IOException {
        String code = "";
        code = br.readLine();

        if(code.equals("0")){
            System.out.println(0);
            return;
        }
        ans = new int[code.length()];

        for(int i = code.length() - 1; i >= 0; i--){
            curr = code.charAt(i); // 입력받기
            //잘못된 코드 예외처리
            if(i == 0 && curr == '0' || (prev == '0' && (curr != '1' && curr != '2'))){
                error = true;
                break;
            }

            //조건문 분기
            if(n==0) n++; //curr가 들어오면 일단 n++;
            else if(n>=1 && prev =='0' && (curr =='1' || curr =='2')){
                //10, 20의 경우 1가지 경우의 수로 처리한다.
                max  = Math.max(max, n);
                ans[count] = n;
                n=0;
                count++;
            }
            else if(n>=1 && curr == '1') n++; // 11~19
            else if(n>=1 && curr =='2' && !(prev =='7' || prev == '8' || prev =='9')) n++; // 21~ 26
            else if(n>=1 && !(curr == 1 || curr == 2)){
                //30~ 이상의 수가 나오면 자리수 끊기
                max  = Math.max(max, n);
                ans[count] = n;
                n = 1;
                count++;
            }
            if(i == 0){
                ans[count] = n;//마지막 탐색 종료
                max  = Math.max(max, n);
            }
            prev = curr;
        }

        if(error) System.out.println(0);
        else{
            initTable();
            answer = 1;
            for(int i = 0; i < count+1; i++){
                if(ans[i] == 0)break;
                answer = (answer * (dp[ans[i]])) % 1000000;
            }
            System.out.print(answer);
        }
    }

    static void initTable(){
        dp = new int[max+1];
        dp[0] =1;
        dp[1] =1;

        for(int i = 2; i < max+1; i++){
            dp[i] = dp[i-1]+ dp[i-2];
            dp[i]%=1000000;
            //여기 mod연산 빼서 개빡치게 틀림 하 ㅅ1발
        }
    }
}
