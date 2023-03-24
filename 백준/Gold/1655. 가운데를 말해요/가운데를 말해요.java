import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, mid;
    static PriorityQueue<Integer> minQ = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> maxQ = new PriorityQueue<>();


    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        mid = Integer.parseInt(br.readLine()); //첫번째 입력 숫자 mid로 설정
        sb.append(mid).append('\n');

        for(int i = 0; i < N-1; i++){
            int num = Integer.parseInt(br.readLine());
            if(num > mid){
                if(minQ.size() - maxQ.size() == 1 || minQ.size() - maxQ.size() == 0) maxQ.add(num);
                else{
                    minQ.add(mid);
                    maxQ.add(num);
                    mid = maxQ.poll();
                }
            }
            else if(num < mid){
                if(maxQ.size() - minQ.size() == 1) minQ.add(num);
                else{
                    maxQ.add(mid);
                    minQ.add(num);
                    mid = minQ.poll();
                }
            }
            else{
                if(minQ.size() >= maxQ.size()) maxQ.add(num);
                else minQ.add(num);
            }
            sb.append(mid).append('\n');
        }

        System.out.print(sb);
    }
}
/*
4
2
3
4
1
 */