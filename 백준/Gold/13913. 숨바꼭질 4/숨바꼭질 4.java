import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main{
        public static int []map;
        public static int[][] dist;
        public static boolean[] visited;
        public static int N, K;
        public static Scanner scan = new Scanner(System.in);
        public static Queue<Integer> queue = new LinkedList<Integer>();
        public static Stack<Integer> road = new Stack<Integer>();
        public static int[] changeX = {-1, 1};



        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[100001];
            visited = new boolean[100001];
            dist = new int[100001][2];

            Bfs(N, K);
            bw.append(dist[K][0]+"\n");
            int num = K;

            while(num != -1){
                road.add(num);
                num = dist[num][1];
            }
            while(!road.isEmpty()) bw.append(road.pop() +" ");

            // 출력
            bw.flush();
            bw.close();
            br.close();

        }

        static void Bfs(int startX, int endX){
            visited[startX] = true;
            queue.add(startX);
            dist[startX][1] = -1;
            while(!queue.isEmpty()){
                int now = queue.poll();
                if(now == endX) break;
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
                    dist[nextX][0] = dist[now][0] + 1;
                    dist[nextX][1] = now;
                    visited[nextX] = true;
                }
            }
        }
}

