import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static PriorityQueue<int[]> timeQ = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if(o1[0] == o2[0]) return o2[1] - o1[1];
            else return o1[0] - o2[0];
        }
    });
    public static PriorityQueue<Integer> room = new PriorityQueue<Integer>();
    public static Scanner scan = new Scanner(System.in);
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());


        for(int input =0; input < N; input++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            timeQ.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }// 시간 큐 완성


        //System.out.println(timeQ.peek()[0]);
        //while(!startQ.isEmpty())System.out.println(startQ.poll());

        room.add(timeQ.poll()[1]);
        int max =0;
        while(!timeQ.isEmpty()){
            max = Math.max(max, room.size());

            if(room.peek() > timeQ.peek()[0]) room.add(timeQ.poll()[1]);
            else {
                room.poll();
                room.add(timeQ.poll()[1]);
            }
        }

        System.out.println(room.size());
    }
}
