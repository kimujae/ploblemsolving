import java.util.*;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<int []> dayqueue = new PriorityQueue<>(new Comparator<int []>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[1] == o2[1]) return Integer.compare(o2[0], o1[0]);
                return Integer.compare(o1[1], o2[1]);
            }
        });

        PriorityQueue<int []> payqueue = new PriorityQueue<>(new Comparator<int []>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return Integer.compare(o1[1],o2[1]);
                return Integer.compare(o1[0],o2[0]);
            }
        });
        
        Scanner scan = new Scanner(System.in);

        int num = scan.nextInt();
        int[] pick;
        int a, b, sum, dcount;

        for(int i = 0; i < num; i++){
            a = scan.nextInt();
            b = scan.nextInt();
            dayqueue.add(new int[]{a,b});
        }
        
        dcount =0;

        while(dayqueue.peek() != null) {
            pick = dayqueue.poll();
            payqueue.add(pick);
            while(dayqueue.peek()!= null && dayqueue.peek()[1] == pick[1]){
                if(payqueue.size() < dayqueue.peek()[1]) {
                    payqueue.add(dayqueue.poll());
                }
                else if(dcount != pick[0]-1 && dayqueue.peek()[0] > payqueue.peek()[0]){
                    payqueue.poll();
                    payqueue.add(dayqueue.poll());
                    dcount++;
                }
                else dayqueue.poll();
            }
            dcount =0;
        }

        sum =0;
        int x = payqueue.size();
        for(int n = 0; n < x ; n++){
            sum += payqueue.poll()[0];
        }

        System.out.println(sum);

    }
}