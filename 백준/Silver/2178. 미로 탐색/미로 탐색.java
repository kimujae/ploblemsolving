import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static Queue<Integer[]> queue = new LinkedList<Integer[]>();
    public static int N, M;// N*M배열 변수
    public static Scanner scan = new Scanner(System.in);
    public static LinkedList<LinkedList<Integer[]>> graph
            = new LinkedList<LinkedList<Integer[]>>();
    public static boolean[][] visited;
    public static int[][] miro, answer;


    public static void main(String[] args) {
        N = scan.nextInt();
        M = scan.nextInt();
        miro = new int[N][M];
        visited = new boolean[N][M];
        answer = new int[N][M];
        answer[0][0] = 1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                graph.add(new LinkedList<Integer[]>());
            }
        }
        // 0 : 48 , 1 : 49
        for(int Ninput = 0; Ninput < N; Ninput++){
            String arr = scan.next();
            for(int Minput = 0; Minput < M; Minput++){
                int a = (int)(arr.charAt(Minput));
                miro[Ninput][Minput] = a;
            }
        }

        for(int Ninput = 0; Ninput < N; Ninput++){
            for(int Minput = 0; Minput < M; Minput++){
                if(miro[Ninput][Minput] == 49){
                    if(Ninput-1 >= 0 && miro[Ninput-1][Minput] == 49)graph.get((Ninput) * M + Minput).add(new Integer[]{Ninput-1, Minput});
                    if(Ninput+1 < N && miro[Ninput+1][Minput] == 49)graph.get((Ninput) * M + Minput).add(new Integer[]{Ninput+1, Minput});
                    if(Minput-1 >= 0 && miro[Ninput][Minput-1] == 49)graph.get((Ninput) * M + Minput).add(new Integer[]{Ninput, Minput-1});
                    if(Minput+1 < M && miro[Ninput][Minput+1] == 49)graph.get((Ninput) * M + Minput).add(new Integer[]{Ninput, Minput+1});
                }
            }
        }
/*
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                int size = graph.get(i*M + j).size();
                int count = 0;
                while(count <size) {
                    System.out.print(graph.get(i * M + j).get(count)[0] + " "+ graph.get(i * M + j).get(count)[1]+" ");
                    count ++;
                }
                System.out.println();
            }
        }
        */

        bfs(new Integer[]{0,0});
        System.out.println(answer[N-1][M-1]);
    }

    public static void bfs(Integer[] node) {
        int graphIndex = (M * node[0]) + node[1];
        int nowX,nextX,nowY,nextY;

        nowX = nextX =node[0];
        nowY = nextY =node[1];
        visited[nowX][nowY] = true;

        queue.add(new Integer[]{nowX, nowY});
        int size = 0;
        while (!queue.isEmpty()) {
            nowX = nextX;
            nowY = nextY;
            while (size < graph.get(graphIndex).size()) {
                if(!visited[graph.get(graphIndex).get(size)[0]][graph.get(graphIndex).get(size)[1]]){
                    queue.add(graph.get(graphIndex).get(size));
                    visited[graph.get(graphIndex).get(size)[0]][graph.get(graphIndex).get(size)[1]] = true;
                    answer[graph.get(graphIndex).get(size)[0]][graph.get(graphIndex).get(size)[1]] = answer[nowX][nowY] + 1;
                }
                size++;
            }
            queue.poll();
            if(queue.isEmpty()) break;
            node = queue.peek();
            nextX = node[0];
            nextY = node[1];
            graphIndex = (M * nextX) + nextY;
            size = 0;

        }
    }

}
