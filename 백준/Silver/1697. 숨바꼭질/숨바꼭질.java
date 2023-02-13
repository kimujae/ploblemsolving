import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int []map, dist;
    public static boolean[] visited;
    public static int N, K;
    public static Scanner scan = new Scanner(System.in);
    public static Queue<Integer> queue = new LinkedList<Integer>();
    public static int[] changeX = {-1, 1};



    public static void main(String[] args) {
        N = scan.nextInt();
        K = scan.nextInt();
        map = new int[100001];
        visited = new boolean[100001];
        dist = new int[100001];

        Bfs(N, K);
        System.out.println(dist[K]);
    }

    static void Bfs(int startX, int endY){
        visited[startX] = true;
        queue.add(startX);

        while(!queue.isEmpty()){
            int now = queue.poll();
            //System.out.println(now);
            for(int i =0; i < 3 ; i++){
                int nextX = 0;
                if(i != 2){
                    nextX = now + changeX[i];
                }
                else nextX = now*2;

                if(nextX < 0 || nextX > 100000)
                    continue;

                if(visited[nextX])
                    continue;

                queue.add(nextX);
                visited[nextX] = true;
                dist[nextX] = dist[now] + 1;
                if(map[nextX] == K) break;
            }
        }
    }
}
