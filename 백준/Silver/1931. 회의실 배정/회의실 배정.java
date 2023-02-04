import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        PriorityQueue<int[]> endQ = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1])return o1[0] - o2[0]; // 종료시간 오름차순
                return o1[1] - o2[1]; // 시작시간 오름차순
            }
        });
        

        int N = scan.nextInt();
        int end;
        int answer = 0;

        for(int input = 0; input < N ;input++){
            int[] time = {scan.nextInt(), scan.nextInt()};
            endQ.add(time);
        }

        end = endQ.poll()[1];
        answer++;
        

        while (!endQ.isEmpty()){
            while(endQ.peek()[0] < end){
                endQ.poll();
                if(endQ.isEmpty()) break;
            }

            if(!endQ.isEmpty()) {
                end = endQ.poll()[1];
                answer++;
            }
        }

        System.out.print(answer);
    }
}
