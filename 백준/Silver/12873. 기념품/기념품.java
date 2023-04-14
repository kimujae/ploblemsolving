import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static Map<Integer, Boolean> party = new HashMap<>();
    static Queue<Integer> game = new LinkedList<>();
    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++){
            game.add(i);
        }


        int stage = 1;
        while(game.size()>1){
            int boj = (int)((((long)Math.pow(stage, 3))-1) % game.size());

            for(int i = 0; i < boj; i++){
                game.add(game.poll());
            }
            game.poll();
            stage++;
        }


        System.out.println(game.poll());
    }
}
