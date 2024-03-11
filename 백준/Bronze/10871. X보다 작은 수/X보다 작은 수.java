import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static BufferedReader br =
            new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int X = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        for(String s : input) {
            int num = Integer.parseInt(s);
            if(num < X) System.out.print(num+ " ");
        }

    }
}
