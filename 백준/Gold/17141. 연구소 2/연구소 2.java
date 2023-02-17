import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{

    public static int[][]map;
    public static boolean[][] visited;
    public static boolean[] virusVisited;
    public static int N, M , count, min, max;
    public static Scanner scan = new Scanner(System.in);
    public static Queue<Virus> queue = new LinkedList<>();
    public static Queue<Virus> virusQueue = new LinkedList<>();
    public static int[][] virusXY;
    public static int[][] virus;
    public static int[][] result;
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};

    public static void main(String[] args) {
        N = scan.nextInt();
        M = scan.nextInt();
        map = new int[N][N];
        min = Integer.MAX_VALUE;
        virus = new int[M][2];
        result = new int[M][2];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int input = scan.nextInt();
                if (input == 2) {
                    map[i][j] = 0;
                    count++;
                    queue.add(new Virus(i, j, 0)); //바이러스
                }
                else map[i][j] = input;
            }
        }// map정보 완성

        virusXY = new int[count][2];
        virusVisited = new boolean[count];
        for(int i = 0; i < count; i++){
            Virus virus = queue.poll();
            virusXY[i][0] = virus.x;
            virusXY[i][1] = virus.y;
        }
        
        combination(0, 0);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }



    static class Virus{
        int x, y , area;

        public Virus(int x, int y, int area){
            super(); // 이거 왜 해주는거임?
            this.x = x;
            this.y = y;
            this.area = area;
        }
    }


    static int Bfs(){
        while(!virusQueue.isEmpty()) {
            int count = 0;
            int size = virusQueue.size();
            while (count < size) {
                Virus virus = virusQueue.poll();
                visited[virus.x][virus.y] = true;
                max = Math.max(max, virus.area);
                count++;
                int nowX = virus.x;
                int nowY = virus.y;
    
                for (int i = 0; i < 4; i++) {
                    int nextX = nowX + changeX[i];
                    int nextY = nowY + changeY[i];
    
                    if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                        continue;
    
                    if (visited[nextX][nextY])
                        continue;
    
                    if (map[nextX][nextY] == 1 || map[nextX][nextY] == 2)
                        continue;
    
                    virusQueue.add(new Virus(nextX, nextY, virus.area + 1));
                    visited[nextX][nextY] = true;
                }
            }
        }
        return max;
    }


    static void combination(int depth, int start){
        if(depth == M) {
            for (int[] num : result) {
                virusQueue.add(new Virus(num[0],num[1], 0));
                map[num[0]][num[1]] =2;
            }
            visited = new boolean[N][N];
            max = 0;
            Bfs();
            getAnswer();
            for (int[] num : result) {
                map[num[0]][num[1]] = 0;
            }
            return; // return으로 스택을 반환해줘야 한다~ 핵심
        }

        for(int i = start; i < count; i++){
            if(!virusVisited[i]){ //방문한 적 없으면
                virusVisited[i] = true;
                result[depth] = virusXY[i]; // 조합 선택
                combination(depth + 1, i);
                virusVisited[i] = false; // 이는 부모 depth에서 다음 인덱스 숫자를 반복 선택하기 위함.
            }
        }
    }

    static void getAnswer(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] !=1 && !visited[i][j]) return; // 도달 불가
            }
        }
        // 정답 존재
        min = Math.min(min, max);
    }

}