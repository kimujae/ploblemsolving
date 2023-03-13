import java.io.*;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Integer> pq  = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num != 0) pq.add(num);
            else if(num == 0 && pq.isEmpty())System.out.println(0);
            else System.out.println(pq.poll());
        }
    }
}
