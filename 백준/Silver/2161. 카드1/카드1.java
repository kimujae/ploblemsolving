import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static Queue<Integer> q = new LinkedList();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine());

        for(int i = 1; i <= N; i++) {
            q.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            int trash = q.poll();
            sb.append(trash);
            sb.append(" ");
            if(q.isEmpty()) break;
            q.add(q.poll());
        }
        System.out.print(sb.toString());
    }
}