import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    public static Integer[][] map; // map을 저장 할 2차원 배열
    public static boolean[][] visited; // 방문처리 할 배열 선언(노드개수만큼 인덱스를 갖는 배열)
    public static int[][][] dist; // 각 노드의 최단거리와 벽뚫기 여부저장, 노드 인덱스는 사상값으로 매치
    public static Scanner scan = new Scanner(System.in); // 입력 scan 생성
    public static Queue<Integer[]> queue = new LinkedList<Integer[]>(); // bfs함수에서 활용할 queue생성
    public static int N, K;
    public static int[] changeX = new int[]{0, -1, 0, 1};
    public static int[] changeY = new int[]{-1, 0, 1, 0};
    public static int[] checkWallX = new int[]{0, -1, 0, 1};
    public static int[] checkWallY = new int[]{-1, 0, 1, 0};

    public static void main(String[] args) {
        N = scan.nextInt();
        K = scan.nextInt();
        dist = new int[N][K][2];
        dist[N-1][K-1][0] = 1;
        visited = new boolean[N][K];
        map = new Integer[N][K];

        for(int Ninput = 0; Ninput < N; Ninput++){
            String str = scan.next();
            for(int Kinput = 0; Kinput < K; Kinput++){
                if(str.charAt(Kinput) == 49) map[Ninput][Kinput] = 1;
                else map[Ninput][Kinput] = 0;
            }
        }



        bfs(N-1,K-1);
        int end = dist[0][0][0];

        dist = new int[N][K][2];
        dist[0][0][0] =1;
        dist[N-1][K-1][0] = 0;
        visited = new boolean[N][K];

        bfs(0,0);
        int start = dist[N-1][K-1][0];
        //System.out.println(Math.min(1,1));

        if(end == 0 && start == 0) System.out.println(-1);
        else if(end == 0 || start == 0) System.out.println(Math.max(end, start));
        else System.out.println(Math.min(end, start));



    }

    public static void bfs(int nowX, int nowY){
        //1 . node를 방문처리한다.
        //2.  인접노드를 큐에 넣는다.
        //3.  2번 작업이 끝나면 poll()을 한다.
        //4.  peek()이 node가 된다.
        visited[nowX][nowY] = true;
        queue.add(new Integer[]{nowX, nowY});
        Integer[] peek = new Integer[2];

        while(!queue.isEmpty()){
            peek = queue.poll();
            nowX = peek[0];
            nowY = peek[1];
            //System.out.println(dist[nowX][nowY][1]);
            //System.out.println(nowX + " " + nowY);

            

            for(int i = 0; i < 4; i++) {
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];


                if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > K - 1)
                    // map의 범위를 넘어서는 경우 pass
                    continue;


                if  (visited[nextX][nextY] == true)
                    //또한 방문했던 경우 pass
                    continue;
/*
                if(dist[nowX][nowY][1] == 0 && map[nextX][nextY] == 1) {
                    for(int j = 0; j < 4; j++) {
                        int checkX = nextX + checkWallX[j];
                        int checkY = nextY + checkWallY[j];


                        if (checkX < 0 || checkX > N - 1 || checkY < 0 || checkY > K - 1)
                            continue;

                        if(visited[checkX][checkY]== true)
                            continue;

                        if (map[checkX][checkY] == 1) {
                            //벽 너머 또 벽이 있는 경우 pass
                            continue;
                        }//if문 종료


                        queue.add(new Integer[]{checkX,checkY});
                        //visited[nextX][nextY] = true;
                        visited[checkX][checkY] = true;
                        dist[checkX][checkY][0] = dist[nowX][nowY][0] + 2;
                        dist[checkX][checkY][1] = 1;
                    }//for문 종료
                }//if문 종료

*/


                    // 길인 경우
                    if(map[nextX][nextY] == 0) {
                        queue.add(new Integer[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        dist[nextX][nextY][0] = dist[nowX][nowY][0] + 1;
                        dist[nextX][nextY][1] = dist[nowX][nowY][1];
                    }
                    else if(map[nextX][nextY] == 1 && dist[nowX][nowY][1]==0){
                        queue.add(new Integer[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        dist[nextX][nextY][0] = dist[nowX][nowY][0] + 1;
                        dist[nextX][nextY][1] = 1;
                    }
                }
            }
        }
}


