import java.io.*;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static PriorityQueue<Integer> pq  = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args)throws IOException{
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num != 0) pq.add(num);
            else if(num == 0 && pq.isEmpty()){
                bw.write(String.valueOf(0));
                bw.write('\n');
            }
            else {
                bw.write(String.valueOf(pq.poll()));
                bw.write('\n');
            }
        }
        bw.flush();
    }
}
