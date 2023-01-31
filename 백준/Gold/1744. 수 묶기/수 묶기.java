import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        Integer[] numbers = new Integer[N];
        int answer, minus, one;

        one = 0;
        minus =0;
        answer = 0;

        for(int input = 0; input < N; input++){
            numbers[input] = scan.nextInt();
            if(numbers[input] <= 0) minus++;
            if(numbers[input] == 1) one++;
        }

        Arrays.sort(numbers, Collections.reverseOrder());


        if(N==1) answer = numbers[0];
        else if(numbers[N-1] >= 0) {
            //0이상의 정수로 이루어진 수열
            if ((N-minus-one) % 2 == 0) {
                //양수 원소가 짝수
                for (int i = 0; i < N - minus - one ; i = i + 2) {
                    answer += numbers[i] * numbers[i + 1];
                }
                answer += one;
            } else if((N-minus-one) % 2 == 1) {
                //양수 원소가 홀수
                for (int i = 0; i < N - minus - one - 1; i = i + 2) {
                    answer += numbers[i] * numbers[i + 1];
                }
                answer += numbers[N - minus - one - 1];
                answer += one;
            }
        } else if(numbers[0] <= 0){
            //음수 또는 0으로 구성된 수열
            if(N % 2 == 0){
                //원소가 짝수
                for(int i = N-1; i > 0; i = i-2){
                    answer += numbers[i]*numbers[i-1];

                }
            }else{
                for(int i = N-1; i > 1; i = i-2){
                    answer += numbers[i]*numbers[i-1];
                }
                answer += numbers[0];
            }

        }else{
            if((N-minus-one) % 2 == 0){
                for (int i = 0; i < N - minus - one; i = i + 2) {
                    answer += numbers[i] * numbers[i + 1];
                }
                answer += one;

                if(minus % 2 ==0){
                    for(int i = N-1; i > N-minus; i = i-2){
                        answer += numbers[i]*numbers[i-1];
                    }
                }else{
                    for(int i = N-1; i > N -minus; i = i-2){
                        answer += numbers[i]*numbers[i-1];
                    }
                    answer += numbers[N- minus];
                }

            }else{
                //원소가 홀수
                for (int i = 0; i < N - minus - one -1; i = i + 2) {
                    answer += numbers[i] * numbers[i + 1];
                }
                answer += numbers[N - minus - one -1];
                answer += one;

                if(minus % 2 ==0){
                    for(int i = N-1; i > N-minus; i = i-2){
                        answer += numbers[i]*numbers[i-1];
                    }
                }else{
                    for(int i = N-1; i > N - minus ; i = i-2){
                        answer += numbers[i]*numbers[i-1];
                    }
                    answer += numbers[N - minus];
                }
            }

        }
        System.out.println(answer);
    }
}
