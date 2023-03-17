import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, ans;
    static int[] weight;
    static String[] words;

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        weight = new int[26];
        words = new String[N];

        for(int i = 0; i < N; i++){
            words[i] = br.readLine();
        }//단어 입력 완료

        for(int i = 0; i < N; i++){
            int len = words[i].length();
            for(int j = 0; j < len; j++){
                int idx = words[i].charAt(j) - 'A';
                weight[idx] += (int)Math.pow(10, len-j-1);
            }
        }
        Arrays.sort(weight);

        for(int i = 25; i >= 16; i--){
            ans += weight[i]*(i-16);
        }
        System.out.print(ans);
    }
}
