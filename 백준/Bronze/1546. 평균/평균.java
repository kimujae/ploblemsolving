import java.io.*;
import java.util.StringTokenizer;

public class Main  {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int section = Integer.parseInt(br.readLine());
        int[] grade = new int[section];
        int max = 0;
        int sum = 0;
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < section; i++){
            grade[i] = Integer.parseInt(st.nextToken());
            sum += grade[i];
            max = Math.max(max, grade[i]);
        }

        double average = ((double)((sum * 100) / max))/section;
        bw.write(String.valueOf(average));
        bw.flush();
    }
}
