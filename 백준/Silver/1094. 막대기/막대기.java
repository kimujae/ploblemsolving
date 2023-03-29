import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int X, ans;
    public static void main(String[] args)throws IOException {
        X = Integer.parseInt(br.readLine());

        while(X != 0){
            int idx= (X & (-X));  //켜져있는 최하위 비트 계산
            X &= ~idx;
            ans ++;
        }
        System.out.print(ans);
    }
}
