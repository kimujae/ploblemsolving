import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N,K,min,answer;

        N = scan.nextInt();
        K = scan.nextInt();

        int[] sensor = new int[N];
        Integer[] differ = new Integer[N-1];


        for(int input = 0; input < N; input++){
            sensor[input] = scan.nextInt();
        }
        Arrays.sort(sensor);


        for(int i = 0; i < N-1; i++){
            differ[i] = sensor[i+1]-sensor[i];
        }
        Arrays.sort(differ, Collections.reverseOrder());

        answer = 0;
        if(K>=N){}
        else {
            for(int x = K-1; x < N-1; x++){
                answer += differ[x];
            }
        }

        System.out.println(answer);
    }
}
