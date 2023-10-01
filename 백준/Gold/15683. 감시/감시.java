import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    private static int N, M;
    private static StringTokenizer st;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<ArrayList<int[]>> vectorMap = new ArrayList<>();

    private static int[] x = {-1, 0, 1, 0};
    private static int[] y = {0, 1, 0, -1};
    private static ArrayList<Cctv> cctvList = new ArrayList<>();
    private static int ans = Integer.MAX_VALUE;

    private static class Cctv{
        int cctvNum;
        int[] coord;
        int vector;


        public Cctv(int cctvNum, int vector, int[] coord) {
            this.cctvNum = cctvNum;
            this.vector = vector;
            this.coord = coord;
        }

        public void rotateVec() {
            int vectorSize = vectorMap.get(this.cctvNum).size();
            this.vector+=1;
            vector = vector % vectorSize;
        }

        public int[][] doWatch(int[][] m, int seq) {
            int nowX = coord[0];
            int nowY = coord[1];
            int[] v = vectorMap.get(cctvNum).get(seq);
            int nx = nowX;
            int ny = nowY;

            for(int vec: v) {
                nx = nowX;
                ny = nowY;
                while(true) {
                    nx += x[vec];
                    ny += y[vec];
                    if (nx > N - 1 || nx < 0 || ny > M - 1 || ny < 0) break;
                    if (m[nx][ny] == 6) break;

                    if (m[nx][ny] == 0) m[nx][ny] = 7;
                }
            }

            return m;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        initVectorMap();
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6) {
                    cctvList.add(new Cctv(map[i][j], 0, new int[]{i, j}));
                }
            }
        }
        
        combination(cctvList.size(), 0, map);
        System.out.print(ans);
    }

    static void combination(int cctvCount, int depth, int[][] m) {
        if(depth == cctvCount) {
            int watch = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(m[i][j] == 0) watch++;
                }
            }
            ans = Math.min(ans, watch);
            return;
        }

        int[][] copyMap = new int[N][M];
        int size = vectorMap.get(cctvList.get(depth).cctvNum).size();
        Cctv cctv = cctvList.get(depth);
        
        for(int j = 0; j < size; j++) {
            for(int k = 0; k < N; k++) {
                    copyMap[k] = m[k].clone();
            }
            combination(cctvCount, depth+1, cctv.doWatch(copyMap, j));
            cctv.rotateVec();
        }
    }


    static void initVectorMap() {
        for(int i = 0; i <= 5; i++) {
            vectorMap.add(new ArrayList<>());
        }

        vectorMap.get(1).add(new int[]{0});
        vectorMap.get(1).add(new int[]{1});
        vectorMap.get(1).add(new int[]{2});
        vectorMap.get(1).add(new int[]{3});

        vectorMap.get(2).add(new int[]{0, 2});
        vectorMap.get(2).add(new int[]{1, 3});

        vectorMap.get(3).add(new int[]{0, 1});
        vectorMap.get(3).add(new int[]{1, 2});
        vectorMap.get(3).add(new int[]{2, 3});
        vectorMap.get(3).add(new int[]{3, 0});

        vectorMap.get(4).add(new int[]{0, 1, 2});
        vectorMap.get(4).add(new int[]{1, 2, 3});
        vectorMap.get(4).add(new int[]{2, 3, 0});
        vectorMap.get(4).add(new int[]{3, 0, 1});

        vectorMap.get(5).add(new int[]{0, 1, 2, 3});
    }
}
