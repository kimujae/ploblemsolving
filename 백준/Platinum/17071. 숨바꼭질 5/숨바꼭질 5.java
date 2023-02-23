import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    //소요 시간 17: 11 ~

    public static boolean[][] visited;
    public static int N, K, depth;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static Queue<Integer> queue = new LinkedList<Integer>();
    public static int[] changeX = {-1, 1};

    /* 수빈은 2초 뒤에 진동하여 다시 나타난다.
       따라서, 홀수 time에 방문하는지, 짝수 time에 방문하는지 체크를 해둔다.
       체크를 time별로 마친 후 동생의 time-이동경로에 수빈이 도달 할 수 있는 지 파악한다.

     */
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수빈 위치
        K = Integer.parseInt(st.nextToken()); // 동생 위치
        visited = new boolean[2][500001];

        Bfs(N);
        bw.flush();
    }


    static void Bfs(int start) throws IOException{
        queue.add(start);
        visited[depth % 2][start] = true;

        while(!queue.isEmpty()) {
            int count =  0;
            int size = queue.size();
            K += depth;
            int ans = brotherMove(depth, K);
            if(ans == -1) {
                bw.write(String.valueOf(-1));
                return;
            } else if(ans != 1001){
                bw.write(String.valueOf(depth));
                return;
            }
            depth++;
            while (count < size) {
                int now = queue.poll();
                count++;

                for (int i = 0; i < 3; i++) {
                    int next = 0;
                    if (i != 2) {
                        next = now + changeX[i];
                    } else next = now * 2;


                    if (next < 0 || next > 500000)
                        continue;

                    if (visited[depth % 2][next])
                        continue;


                    queue.add(next);
                    visited[depth % 2][next] = true;
                }
            }
        }
    }
    static int brotherMove(int time, int brother){
        if(brother > 500000) return -1;
        else if(visited[time % 2][brother]) return time;
        else return 1001;
    }
}



