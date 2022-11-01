import java.util.*;

public class Main {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        System.out.println(solution(input));
    }

    public static int solution(int input) {

        if (input == 7 || input == 4) {
            return -1;
        }
        else if(input % 5 ==0){
            return input /5 ;
        } else if (input % 5 == 1) {
            return input / 5 + 1;
        } else if (input % 5 == 2) {
            return input / 5 + 2;
        } else if (input % 5 == 3) {
            return input / 5 + 1;
        } else return input / 5 + 2;


    }
}