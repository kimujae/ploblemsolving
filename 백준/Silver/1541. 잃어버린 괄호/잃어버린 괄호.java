import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        String[] numbers = str.split("-");
        int ans = 0;
        int idx = 0;
        
        
        for(String s : numbers) {
            String[] pluses = s.split("\\+");
            
            int sub = 0;
            for(String plus : pluses) {
                sub += Integer.parseInt(plus);
            }
            if(idx++ == 0) ans += sub;
            else ans -= sub;
        }

        System.out.print(ans);
    }
}
