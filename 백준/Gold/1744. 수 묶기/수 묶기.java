import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }




        /*
        1. 두가지 수를 묶는다.
        2. 오름차순 정렬을 한다.
        3. 수를 두개씩 뽑는다.
        4. 만약 두 수가 모두 음수 혹은 0이라면 곱한다.
        5. 만약 두 수가 모두 양수라면 곱한다.
        6. 정답을 반환한다.
         */
        int ans = 0;
        while(!pq.isEmpty()){
            if(pq.size() ==1) {
                ans+= pq.poll();
                break;
            }
            //두가지 수를 뽑는다.
            int num1 = pq.poll();
            int num2 = pq.poll();

            // 둘 모두 0 또는 음수인 경우 -> 묶는다.
            if(num1<=0 && num2 <=0){
                ans += num1*num2;
                continue;
            }
            // 둘 모두 양수인 경우 -> 묶는다.
            if(pq.size() %2 == 0 && num1 > 1 && num2 > 1){
                ans += num1* num2;
                continue;
            }

            // 그 이외의 경우 : 음수,0 & 0,양수 -> 묶지 않는다.
            ans += num1;// 1또는 음수를 일단 더함
            if(num2 == 0 || num2 == 1) {
                ans += num2;
                continue;
            }
            if(pq.size() % 2  == 1)ans += num2* pq.poll();
            else ans += num2;
        }

        System.out.println(ans);
    }
}
