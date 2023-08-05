import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] dice;
    static int[][] map;
    static int[] coord;
    static int[] changeX = {0, 0, -1, 1};
    static int[] changeY = {1, -1, 0, 0};
    public static void main(String[] args)throws IOException {
        int x, y, K; // 지도 세로, 가로, 주사위 x, y, 명령개수

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        coord = new int[2];
        coord[0] = x;
        coord[1] = y;
        dice = new int[6];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K ; i++) {
            int inst = Integer.parseInt(st.nextToken());
            if(!checkValid(inst)) continue;

            moveVec(inst);
            moveDice(inst);
            copy(map[coord[0]][coord[1]]);

            System.out.println(dice[5]);
        }
    }

    static void moveDice(int vec) {
        int[] copyDice = new int[6];
        int idx = 0;
        for(int num : dice) {
            copyDice[idx] = num;
            idx++;
        }

        if(vec == 2) {
            dice[1] = copyDice[5];
            dice[2] = copyDice[1];
            dice[3] = copyDice[2];
            dice[5] = copyDice[3];
        } else if(vec == 1) {
            dice[1] = copyDice[2];
            dice[2] = copyDice[3];
            dice[3] = copyDice[5];
            dice[5] = copyDice[1];
        } else if(vec == 3) {
            dice[0] = copyDice[5];
            dice[2] = copyDice[0];
            dice[4] = copyDice[2];
            dice[5] = copyDice[4];
        } else {
            dice[0] = copyDice[2];
            dice[2] = copyDice[4];
            dice[4] = copyDice[5];
            dice[5] = copyDice[0];
        }
    }

    static void moveVec(int vec) {
        if(vec == 1) {
            coord[0] += changeX[vec-1];
            coord[1] += changeY[vec-1];
        } else if(vec == 2) {
            coord[0] += changeX[vec-1];
            coord[1] += changeY[vec-1];
        } else if(vec == 3) {
            coord[0] += changeX[vec-1];
            coord[1] += changeY[vec-1];
        } else {
            coord[0] += changeX[vec-1];
            coord[1] += changeY[vec-1];
        }
    }

    static void copy(int isZero) {
        if(isZero == 0) {
            map[coord[0]][coord[1]] = dice[2];
        } else {
            dice[2] = isZero;
            map[coord[0]][coord[1]] = 0;
        }
    }

    static boolean checkValid(int vec) {
        int nx = coord[0] + changeX[vec-1];
        int ny = coord[1] + changeY[vec-1];

        if(nx < 0 || ny < 0 || nx > N-1 || ny > M-1) return false;
        return true;
    }
}