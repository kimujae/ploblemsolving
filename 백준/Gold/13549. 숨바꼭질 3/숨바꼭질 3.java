import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main{
    public static int []map, dist;
    public static boolean[] visited;
    public static int N, K, count;
    public static Scanner scan = new Scanner(System.in);
    public static Deque<Integer> queue = new LinkedList<Integer>();
    public static int[] changeX = {-1, 1};



    public static void main(String[] args) {
        N = scan.nextInt();
        K = scan.nextInt();
        map = new int[100001];
        visited = new boolean[100001];
        dist = new int[100001];

        Bfs(N, K);
        System.out.println(dist[K] - count);
    }

    static void Bfs(int startX, int endY){
        visited[startX] = true;
        queue.add(startX);

        while(!queue.isEmpty()) {
            if(dist[endY] != 0) break;
                int now = queue.poll();
                //System.out.println(now);
                for (int i = 0; i < 3; i++) {
                    int nextX = 0;
                    if (i != 0) {
                        nextX = now + changeX[i-1];
                    } else nextX = now * 2;

                    if (nextX < 0 || nextX > 100000)
                        continue;

                    if (visited[nextX])
                        continue;



                    queue.add(nextX);
                    dist[nextX] = dist[now] + 1;
                    visited[nextX] = true;
                    if(i == 0) dist[nextX] = dist[now];
                }
            }
        }
}
