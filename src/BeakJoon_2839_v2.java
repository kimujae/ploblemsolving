import java.util.*;

class BeakJoon_2839_v2 {

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        System.out.println(solution(input));
    }

    public static int solution(int input){
        int count = 0;

        while(input != 0){

            if(input<0){
                return -1;
            }
            else if(input% 5 ==0){
                return count + input/5;
            }
            else {
                input = input -3;
                count++;
            }
        }
        return count;
    }

}