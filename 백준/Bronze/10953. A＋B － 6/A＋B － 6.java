import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int i =0; i < tc; i++) {
            String[] strs = br.readLine().split(",");
            int plus = 0;
            for(String str : strs) {
                plus += Integer.parseInt(str);
            }
            System.out.println(plus);
        }
    }
}
