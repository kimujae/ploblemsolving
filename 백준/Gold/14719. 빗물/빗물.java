import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int H, W;
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        /*
            그럼 얼마나 고일지 판단하는 방법은?
            **********각 블록마다 부딪히는 블록이 있는지 판단하면 되는 문제 아닌가?

         */

        st = new StringTokenizer(br.readLine());
        int[] map = new int[W];
        for(int i = 0; i < W; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }


        int ans = 0;
        int water = 0;
        boolean pos = false;

        for(int i = 1; i <= H; i++){
            pos = false;
            water = 0;
            for(int j = 0; j < W; j++){
                /*
                    !pos && i보다 map[j]가 크다 -> pos toggle on
                    pos && i 보다 map[j]가 작다 -> water++
                    pos && i보다 map[j]가 크다 -> ans += water, water = 0
                 */
                if(!pos && i <= map[j]) pos = true;
                else if(pos && i > map[j]) water++;
                else if(pos && i <= map[j]){
                    ans += water;
                    //System.out.println(water + " "+ i);
                    water = 0;
                }
            }
        }
        System.out.println(ans);
    }
}