import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static int []map, dist, branch;
    public static boolean[] visited;
    public static int N, K;
    public static Scanner scan = new Scanner(System.in);
    public static Queue<Integer> queue = new LinkedList<Integer>();
    public static int[] changeX = {-1, 1};



    public static void main(String[] args) {
        N = scan.nextInt();
        K = scan.nextInt();
        map = new int[100001];//인덱스설정 주의....
        visited = new boolean[100001];//인덱스설정 주의....
        dist = new int[100001];//인덱스설정 주의....
        branch = new int[100001];
        
        Bfs(N, K);
        System.out.println(dist[K]);
        System.out.println(branch[K]);
    }

    static void Bfs(int startX, int endY){
        visited[startX] = true;
        queue.add(startX);
        branch[startX]=1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            int time = 0;
            while (time < size) {
                int now = queue.poll();
                time++;
                
                for (int i = 0; i < 3; i++) {
                    int nextX = 0;
                    if (i != 2) {
                        nextX = now + changeX[i];
                    } else nextX = now * 2;


                    if (nextX < 0 || nextX > 100000)
                        continue;

                    if(visited[nextX] && dist[nextX] - 1 == dist[now]){
                        //dist[now]는 depth가 모두 동일하다. -> while(time < size)블록 내이므로.
                        branch[nextX] += branch[now];
                        continue;
                    }

                    if (visited[nextX]) {
                        continue;
                    }

                    queue.add(nextX);
                    visited[nextX] = true;
                    dist[nextX] = dist[now] + 1;
                    branch[nextX] = branch[now];
                }
            }
        }
    }
}
