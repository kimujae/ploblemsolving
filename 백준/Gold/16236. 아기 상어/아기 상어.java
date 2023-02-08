import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] times; //걸린 시간
   
    public static int[] changeY = new int[]{0,-1,0,1};//2좌,4우 우선
    public static int[] changeX = new int[]{-1,0,1,0};//1상,3하 우선
    public static Scanner scan = new Scanner(System.in);
    public static int N, self, eat, time, startX, startY, minX, minY, minDist, nextX, nextY;
    public static Queue<int[]> queue = new LinkedList<int[]>();
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
        minX = minY = N;
        minDist = N*N;
        visited[startX][startY] = true;
        map[startX][startY] = 0;
        queue.add(new int[]{startX,startY});

        while(!queue.isEmpty()){
            int[] node = queue.poll();
            startX = node[0];
            startY = node[1];
            //System.out.println(startX + " " + startY);
            for(int i = 0; i < 4; i++){
                nextX = startX + changeX[i];
                nextY = startY + changeY[i];

                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > N-1)
                    //map을 벗어나면 pass
                    continue;

                if(visited[nextX][nextY])
                    continue;

                if(map[nextX][nextY] > self)
                    //자신무게보다 큰 상태에 대해서 pass
                    continue;

                //먹을 수 있는 먹이가 있는 상황인지 체크추가
                if(map[nextX][nextY] > 0 && map[nextX][nextY] < self){
                    //먹을 수 있는 상태
                    isEatable = true;
                    times[nextX][nextY] = times[startX][startY]+1;
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;

                    if(times[nextX][nextY] < minDist){
                        minDist = times[nextX][nextY];
                        minX = nextX;
                        minY = nextY;
                    }
                    else if(times[nextX][nextY] == minDist){
                        if(minX > nextX) {
                            minX = nextX;
                            minY = nextY;
                        }
                        if(minX == nextX && minY > nextY){
                            minY = nextY;
                        }
                    }
                }

                else{
                    //길
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX,nextY});
                    times[nextX][nextY] = times[startX][startY] + 1;
                }
            }

            if(queue.isEmpty() && isEatable) {
                eat++;
                if (eat >= self) {
                    self++;
                    eat = 0;
                }
                time += minDist;
                times = new int[N][N];
                visited = new boolean[N][N];
                queue = new LinkedList<int[]>();
                queue.add(new int[]{minX, minY});
                isEatable = false;
                map[minX][minY] = 0;
                minX = minY = N-1;
                minDist = N*N;
            }

        }

    }
}
