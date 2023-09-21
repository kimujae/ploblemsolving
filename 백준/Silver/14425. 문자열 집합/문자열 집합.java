import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            set.add(s);
        }

        int ans = 0;
        for(int i = 0; i < m; i++) {
            if(set.contains(br.readLine())) ans++;
        }

        System.out.print(ans);
    }
}
