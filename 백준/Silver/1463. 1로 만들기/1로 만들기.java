import java.io.*;
import java.util.Arrays;

public class Main {
    public static int[] memo;
    public static int N;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        memo = new int[N+1];
        memo[1] = 1;
        bw.write(String.valueOf(Dp(N) - 1));
        bw.flush();
    }

    static int Dp(int number){
        if(memo[number] != 0) return memo[number];//메모 활용
        //else if(number == 0) return memo[number];
        else if(number % 6 == 0) return memo[number] = Math.min(Dp(number/3), Math.min(Dp(number/2), Dp(number - 1))) + 1;
        else if(number % 3 == 0) return memo[number] = Math.min(Dp(number/3), Dp(number -1)) + 1;
        else if(number % 2 == 0) return memo[number] = Math.min(Dp(number/2), Dp(number -1)) + 1;
        else return memo[number] = Dp(number-1) + 1;
    }
}
