import java.util.Scanner;

public class BeakJoon_2839 {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        if (input == 7 || input == 4) {
            System.out.println(-1);
        }
        else if(input % 5 ==0){
            System.out.println(input /5);
        } else if (input % 5 == 1 || input % 5 == 3) {
            System.out.println(input / 5 + 1);
        } else if (input % 5 == 2 || input % 5 == 4) {
            System.out.println(input / 5 + 2);
        }

    }
}
//3a + 5b = N
//a + b 의 최소값
//