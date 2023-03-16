import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] com;
    static boolean[] visited;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited= new boolean[N+1];
        com = new int[M];

        combination(1, 0);
    }


    static void combination(int num, int depth)throws IOException{
        if(depth == M){
            for(int number : com){
                bw.write(String.valueOf(number));
                bw.write(" ");
            }
            bw.write('\n');
            bw.flush();
            return;
        }

        for(int i = num; i <= N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            com[depth] = i;
            combination(i, depth+1);
            visited[i] = false;
        }
    }
}
