import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M;
    private static boolean[][] v;
    private static int[] cx = {-1 , 0, 1, 0};
    private static int[] cy = {0, -1, 0, 1};
    private static Block[][] map; // N*N 격자
    private static int score;
    private static PriorityQueue<Block> cPq = new PriorityQueue<>(new Comparator<Block>() {
        @Override
        public int compare(Block o1, Block o2) {
            if(o1.x == o2.x) return o1.y - o2.y;
            return o1.x - o2.x;
        }
    });
    private static class Block{
        int color; // -1 검정 0무지개 i~M 일반블록
        int x;
        int y;

        public Block(int color, int x, int y) {
            this.color = color;
            this.x = x;
            this.y = y;
        }
    }

    private static class BlockGroup implements Comparable<BlockGroup>{
        int size;
        int rainbows;
        Block criteria; // 어차피 이동가능한데?
        int color;

        public BlockGroup(int size, int rainbows, Block c, int color) {
            this.size = size;
            this.rainbows = rainbows;
            this.color = color;
            this.criteria  = c;
        }

        @Override
        public int compareTo(BlockGroup o) {
            if(o.size != this.size) return o.size - this.size;
            if(o.rainbows != this.rainbows) return o.rainbows - this.rainbows;
            if(o.criteria.x != this.criteria.x) return o.criteria.x - this.criteria.x;
            return o.criteria.y - this.criteria.y;
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Block[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int color = Integer.parseInt(st.nextToken());
                Block b = new Block(color, i, j);
                map[i][j] = b;
            }
        }

        while(true) {
            v = new boolean[N][N];
            cPq.clear();
            ArrayList<BlockGroup> groups = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(v[i][j] || map[i][j] == null
                            || map[i][j].color == -1 || map[i][j].color == 0) continue;
                    // 방문, 없어짐, 검정블록, 무지개블록 skip


                    BlockGroup bg = findBlockGroup(map[i][j]);
                    if(bg == null) continue;
                    groups.add(bg);
                }
            }
            //printMap();



            if(groups.size() == 0) break;

            BlockGroup bg = findBiggestGroup(groups);

            v = new boolean[N][N];
            int removeCount = removeGroup(bg.criteria, bg.size);
            plusScore(removeCount);
            gyro();

            rotate();
            gyro();

        }


        System.out.println(score);
    }

    public static BlockGroup findBiggestGroup(ArrayList<BlockGroup> groups) {
        Collections.sort(groups);
        return groups.get(0);
    }

    public static BlockGroup findBlockGroup(Block start) {
        //블록그룹을 찾는다.
        boolean[][] localV = new boolean[N][N];

        cPq.clear();
        cPq.add(start);
        Queue<Block> q = new LinkedList<>();
        q.add(start);
        int color = start.color; // 그룹의 컬러, 무지개블록은 얼마든 상관 없음
        v[start.x][start.y] = true;

        int size = 0;
        int rainbows = 0;
        Block cr = null;
        BlockGroup bg = new BlockGroup(1, 0, null, color);

        while(!q.isEmpty()) {
            size++;
            Block now = q.poll();
            int x = now.x;
            int y = now.y;
            //System.out.print(x + " " + y + " " + color);

            for(int i = 0; i < 4; i++) {
                int nx = x + cx[i];
                int ny = y + cy[i];

                if(nx > N-1 || ny > N-1 || nx < 0 || ny < 0
                         || map[nx][ny] == null || (map[nx][ny].color == color && v[nx][ny]) || map[nx][ny].color == -1
                || (map[nx][ny].color == 0 && localV[nx][ny]))
                    continue;


                if(map[nx][ny].color == color || map[nx][ny].color == 0) {

                    q.add(map[nx][ny]);

                    if (map[nx][ny].color > 0) {
                        cPq.add(map[nx][ny]);
                        v[nx][ny] = true;

                    }
                    else rainbows++;
                    localV[nx][ny] = true;
                }
            }
        }

        if(size == 1) return null;
        cr = cPq.poll();
        bg.criteria = cr;
        bg.size = size;
        bg.rainbows = rainbows;

        //System.out.println(size);
        return bg;
    }


    public static int removeGroup(Block criteria, int size) {
        Queue<Block> q = new LinkedList<>();
        q.add(criteria);
        int color = criteria.color;
        boolean[][] localV = new boolean[N][N];

        while(!q.isEmpty()) {
            Block now = q.poll();
            int x = now.x;
            int y = now.y;
            map[x][y] = null;

            for(int i = 0; i < 4; i++) {
                int nx = x + cx[i];
                int ny = y + cy[i];

                if(nx > N-1 || ny > N-1 || nx < 0 || ny < 0
                        || map[nx][ny] == null || (map[nx][ny].color == color && v[nx][ny]) || map[nx][ny].color == -1
                        || (map[nx][ny].color == 0 && localV[nx][ny]))
                    continue;


                if(map[nx][ny].color > 0 && map[nx][ny].color != color) continue;

                if(map[nx][ny].color == color) v[nx][ny] = true;
                localV[nx][ny] = true;
                q.add(map[nx][ny]);
            }
        }

        return size;
    }

    public static void plusScore(int removeCount) {
        score = score + (removeCount * removeCount);
    }

    public static void gyro() {
        // 행에 1을 더하며 진행
        for(int x = N-2; x >= 0 ; x--) {
            for(int y = 0; y < N; y++) {
                if(map[x][y] == null || map[x][y].color == -1) continue;

                int cx = x; // 현재 위치
                int cy = y; // 현재 위치
                Block b = map[cx][cy];

                while(true) {
                    if(cx + 1 > N-1 || map[cx + 1][cy] != null ||
                            (map[cx+1][cy] != null && map[cx + 1][cy].color == -1))
                        break;

                    cx += 1;
                }

                if(x== cx && y == cy) continue;

                map[x][y].x = cx;
                map[x][y].y = cy;
                map[cx][cy] = b;
                map[x][y] = null;
            }
        }
    }

    public static Block[][] rotate() {
        Block[][] nMap = new Block[N][N];

        for(int y = N-1, i = 0; y >= 0; y--, i++) {
            for(int x = 0, j = 0; x < N; x++, j++) {
                if(map[x][y] == null) continue;

                nMap[i][j] = map[x][y];
                map[x][y].x = i;
                map[x][y].y = j;
            }
        }

        map = null;
        return map = nMap;
    }


    public static void printMap() {
        for(int i = 0; i < N; i++) {
            for(int j = 0 ; j < N; j++) {
                if(map[i][j] != null) System.out.print(map[i][j].color + " ");
                else System.out.print(-3 + " ");
            }
            System.out.println();
        }
        System.out.println();

    }
}
