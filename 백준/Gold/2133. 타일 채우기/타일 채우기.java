import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int[] DP = new int[31];
        DP[2] = 3;
        DP[4] = 11;

        for(int i = 6; i < 31; i++){
            if(i % 2 ==0){
                DP[i] = DP[i - 2] * DP[2] + 2;
                for(int j = i-4; j >= 2; j = j-2) {
                     DP[i] += 2 * DP[j] ;
                }
            }
        }
        System.out.println(DP[input]);
    }

}
