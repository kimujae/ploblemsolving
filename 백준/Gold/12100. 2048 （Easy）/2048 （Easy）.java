import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static class Block{
        int num;

        public Block(int num) {
            this.num = num;

        }
        public int getNum() {
            return this.num;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static ArrayList<Deque<Block>> colqList = new ArrayList<>();
    private static ArrayList<Deque<Block>> rowqList = new ArrayList<>();
    private static Block[][] map;
    private static int answer = Integer.MIN_VALUE;
    private static int N;
    private static int[] changeX = {-1, 0, 1, 0};
    private static int[] changeY = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());


        map = new Block[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) continue;
                Block b = new Block(num);
                map[i][j] = b;
            }
        }


        dfs(0, map);
        System.out.print(answer);
    }

    public static void dfs(int depth, Block[][] m) {
        if(depth == 5) {
            int max = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(m[i][j] != null) {
                        max = Math.max(max, m[i][j].getNum());
                    }
                }
            }

            answer = Math.max(answer, max);
            return;
        }


        Block[][] nextMap;
        Queue<Block> q;
        for(int vec = 0; vec < 4; vec++) {
            //deepcopy
            nextMap = new Block[N][N];
            // 상하좌우 방향을 결정한다.
            if(vec == 0) {
                // 상 : colQ
                for(int i = 0; i < N; i++) {
                    q = new LinkedList<>();
                    for(int j = 0; j < N; j++) {
                        if(m[j][i] != null) q.add(m[j][i]);
                    }

                    Block prev = null;
                    int idx = 0;
                    for(Block b : q) {
                        if(prev == null) {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[idx++][i] = newB;
                            continue;
                        }

                        if(prev.getNum() == b.getNum()) {
                            prev.num *= 2;
                            prev = null;
                            continue;

                        }
                        else {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[idx++][i] = newB;
                        }
                    }
                }
            } else if(vec == 1) {
                // 하 : colQ
                for(int i = N-1; i >= 0; i--) {
                    q = new LinkedList<>();
                    for(int j = N-1; j >= 0; j--) {
                        if(m[j][i] != null) q.add(m[j][i]);
                    }

                    Block prev = null;
                    int idx = N-1;
                    for(Block b : q) {
                        if(prev == null) {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[idx--][i] = newB;
                            continue;
                        }

                        if(prev.getNum() == b.getNum()) {
                            prev.num *= 2;
                            prev = null;
                            continue;

                        }

                        else {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[idx--][i] = newB;
                        }
                    }
                }

            } else if(vec == 2) {
                // 좌 : rowQ
                for(int i = 0; i < N; i++) {
                    q = new LinkedList<>();
                    for(int j = 0; j < N; j++) {
                        if(m[i][j] != null) q.add(m[i][j]);
                    }

                    Block prev = null;
                    int idx = 0;
                    for(Block b : q) {
                        if(prev == null) {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[i][idx++] = newB;
                            continue;
                        }

                        if(prev.getNum() == b.getNum()) {
                            prev.num *= 2;
                            prev = null;
                            continue;

                        }
                        else {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[i][idx++] = newB;
                        }
                    }
                }

            } else {
                // 우 : rowQ
                for(int i = N-1; i >= 0; i--) {
                    q = new LinkedList<>();
                    for(int j = N-1; j >= 0; j--) {
                        if(m[i][j] != null) q.add(m[i][j]);
                    }

                    Block prev = null;
                    int idx = N-1;
                    for(Block b : q) {
                        if(prev == null) {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[i][idx--] = newB;
                            continue;
                        }

                        if(prev.getNum() == b.getNum()) {
                            prev.num *= 2;
                            prev = null;
                            continue;
                        }
                        else {
                            Block newB = new Block(b.num);
                            prev = newB;
                            nextMap[i][idx--] = newB;
                        }
                    }
                }
            }
            dfs(depth + 1, nextMap);
        }
    }
}
/*
4
2 0 2 0
2 0 0 0
2 0 0 0
0 0 0 0
 */