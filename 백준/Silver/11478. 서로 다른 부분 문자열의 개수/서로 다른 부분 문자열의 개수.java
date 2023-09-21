import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        HashSet<String> set = new HashSet<>();
        for(int len = 0; len < str.length(); len++) {
            for(int i = 0; i < str.length() - len; i++) {
                set.add(str.substring(i, i+len+1));
            }
        }

        System.out.print(set.size());
    }
}
