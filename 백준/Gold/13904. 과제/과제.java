import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        PriorityQueue<int[]> days = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o2[1] - o1[1];
                return o2[0] - o1[0];
            }
        });

        PriorityQueue<int[]> scores = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o2[0] - o1[0];
                return o2[1] - o1[1];
            }
        });


        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[][] works = new int[N][2];
        int answer = 0;
        int day = 0;


        for(int input = 0; input < N; input++){
            works[input][0] = scan.nextInt();
            works[input][1] = scan.nextInt();
            days.add(works[input]);
        }


        int count = days.peek()[0];

        while(!days.isEmpty()) {
            //days큐 원소가 없어질 때 까지 실행

            day = days.peek()[0];

            while (!days.isEmpty()
                    && days.peek()[0] == day) {
                scores.add(days.poll());
            }

            while(!days.isEmpty()&& count > days.peek()[0]){
                if(!scores.isEmpty()) answer += scores.poll()[1];
                count--;
            }
        }


        while(!scores.isEmpty()
                && count != 0){
            answer+= scores.poll()[1];
            count--;
        }


        System.out.print(answer);
    }
}
