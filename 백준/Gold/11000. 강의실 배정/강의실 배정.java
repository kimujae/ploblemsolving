import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> startQ = new PriorityQueue<Integer>();//시작 시간은 오름차순 정렬
        PriorityQueue<Integer> finishQ = new PriorityQueue<Integer>();//종료 시간도 오름차순 정렬

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[][] timetables = new int[N][2];
        int answer = 0;

        for(int input = 0; input < N; input++){
            startQ.add(scan.nextInt());
            finishQ.add(scan.nextInt());
        }



        while(!finishQ.isEmpty()){
            while(!startQ.isEmpty()
                    && startQ.peek() < finishQ.peek()){
                if(startQ.peek() == finishQ.peek()){startQ.poll();}
                else{
                    startQ.poll();
                    answer++;
                }
            }
            if(!startQ.isEmpty()){startQ.poll();}
            finishQ.poll();
        }


        System.out.println(answer);

    }
}
