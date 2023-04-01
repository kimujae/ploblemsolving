import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Integer> studentQ = new LinkedList<>();
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
       @Override
       public int compare(int[] o1, int[] o2){
           if(o2[3] != o1[3]) return o2[3]- o1[3];
           if(o2[2] != o1[2]) return o2[2]- o1[2];
           if(o2[1] != o1[1]) return o1[1]- o2[1];
           return o1[0] - o2[0];
        }
    });
    static int[][] map;
    static int[][] pref;
    static int[] changeX = {1, 0, -1, 0};
    static int[] changeY = {0, 1, 0, -1};
    static int N, ans;

    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        pref = new int[(N*N)+1][4];
        map = new int[N][N];

        for(int i = 0; i < N*N; i++){
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            studentQ.add(student);
            for(int j = 0; j < 4; j++){
                int prefer = Integer.parseInt(st.nextToken());
                pref[student][j] = prefer;
            }
        }// 학생 순서 및 선호 학생 입력 완료

        setLocation();
        computeScore();
        System.out.println(ans);
    }

    static void computeScore(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int nowStudent = map[i][j];
                int prefers = 0;
                for(int k = 0; k < 4; k++){
                    int nextX = i + changeX[k];
                    int nextY = j + changeY[k];

                    if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                        continue;

                    int nextStudent = map[nextX][nextY];
                    for(int x = 0; x < 4; x++){
                        if(pref[nowStudent][x] == nextStudent) {
                            prefers++;
                            break;
                        }
                    }
                }//탐색 완료
                if(prefers > 0) ans += (int)Math.pow(10, prefers-1);
            }
        }
    }

    static void setLocation(){
        while(!studentQ.isEmpty()) {
            int nowStudent = studentQ.poll();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] != 0) continue;

                    int nowX = i;
                    int nowY = j;
                    int prefers = 0;
                    int empty = 0;
                    for (int k = 0; k < 4; k++) {
                        int nextX = nowX + changeX[k];
                        int nextY = nowY + changeY[k];


                        if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                            continue;

                        int student = map[nextX][nextY];
                        if (student != 0) {
                            for(int x = 0; x < 4; x++){
                                if(pref[nowStudent][x] == student){
                                    prefers++;
                                    break;
                                }
                            }
                        } else empty++;
                    }// 인접칸 탐색 완료
                    pq.add(new int[]{nowY, nowX,empty, prefers});//열, 행, 비어있는 칸 수, 선호학생 수
                }
            }//자리 탐색 완료

            int[] location = pq.poll();
            pq.clear();
            int y = location[0];
            int x = location[1];

            map[x][y] = nowStudent; //자리 배치 완료
        }
    }
}
