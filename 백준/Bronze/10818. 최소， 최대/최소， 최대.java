import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N ;i++) {
            int num = Integer.parseInt(input[i]);
            
            if(num > max) max = num;
            if(num < min) min = num;
        }
        
        
        System.out.println(min + " " + max);
    }
}
