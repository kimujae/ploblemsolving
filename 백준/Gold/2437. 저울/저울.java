import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int [] weight = new int[N];
        int result  = 1; // 정답을 저장하는 변수
        int sum = 0;
        
        for(int input = 0; input < N; input++){
            weight[input] = scan.nextInt(); // 무게추의 입력을 배열로 저장
            sum += weight[input];
        }

        Arrays.sort(weight); // 무게추의 오름차순 정렬


        int [] prefix_sum = new int[N];
        // 추들의 누적합들을 저장하는 배열
        // 0을 원소로 갖는 최소값이 정답이 됨.

        // 알고리즘 작성부분
        for(int i = 1; i <= N; i++){
                if(i == 1 && weight[0] != 1){
                    result = 1;
                    //첫 원소가 1이 아니면, 바로 break
                    break;
                }
                //맞다면,
                else if(i == 1 && weight[0] == 1){
                    prefix_sum[0] = weight[0];
                }
                else if(weight[i-1] - prefix_sum[0] <= prefix_sum[i-2]){
                        if(i == sum){
                            result = sum;
                        }
                        else {
                            result = weight[i - 1] + prefix_sum[i - 2];
                            prefix_sum[i - 1] = result;
                        }
                    }
                else break;
                }
        if(result == 1){}
        else result += 1;

        System.out.println(result); // 정답 출력
        }
}
