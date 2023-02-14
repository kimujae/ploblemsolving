import java.util.*;

public class Main{
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] times; //걸린 시간

    public static int[] changeX = new int[]{-1,0,0,1};//1상,3하 우선
    public static int[] changeY = new int[]{0,-1,1,0};//2좌,4우 우선

    public static Scanner scan = new Scanner(System.in);
    public static int N, self, eat, time, startX, startY;
    public static Queue<int[]> queue = new LinkedList<int[]>();
    public static PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0] ;
        }
    });

    public static boolean isEatable;

    public static void main(String[] args) {
        N = scan.nextInt();
        visited = new boolean[N][N];
        map = new int[N][N];
        times = new int[N][N];
        time = 0;


        for(int iInput = 0; iInput < N; iInput++){
            for(int jInput = 0; jInput < N; jInput++){
                int num = scan.nextInt();
                map[iInput][jInput] = num;
                if(num==9){
                    startX = iInput;
                    startY = jInput;
                }
            }
        }

        bfs(startX,startY);
        System.out.println(time);
    }

    public static void bfs(int startX, int startY){
        self = 2;//아기상어의 시작 무게
        eat = 0;//먹은 횟수
        visited[startX][startY] = true;
        map[startX][startY] = 0;
        queue.add(new int[]{startX,startY});
        int temp = time;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            while (count < size) {
                int[] node = queue.poll();
                count++;
                startX = node[0];
                startY = node[1];
                
                for (int i = 0; i < 4; i++) {
                    int nextX = startX + changeX[i];
                    int nextY = startY + changeY[i];

                    if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                        //map을 벗어나면 pass
                        continue;

                    if (visited[nextX][nextY])
                        continue;

                    if (map[nextX][nextY] > self)
                        //자신무게보다 큰 상태에 대해서 pass
                        continue;

                    if (map[nextX][nextY] > 0 && map[nextX][nextY] < self) {
                        priorityQueue.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        times[nextX][nextY] = times[startX][startY] + 1;
                        continue;
                    }

                    else {
                        //길
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                        times[nextX][nextY] = times[startX][startY] + 1;
                    }
                }
            }


            if(!priorityQueue.isEmpty()){
                //먹이를 먹자
                visited = new boolean[N][N];
                queue.clear();
                map[priorityQueue.peek()[0]][priorityQueue.peek()[1]] = 0;
                time += times[priorityQueue.peek()[0]][priorityQueue.peek()[1]];
                queue.add(priorityQueue.poll());
                priorityQueue.clear();
                times = new int[N][N];
                eat++;
                if (eat == self) {
                    self++;
                    eat = 0;
                }
            }// 먹이 먹기 끝
            
        }
    }
}
