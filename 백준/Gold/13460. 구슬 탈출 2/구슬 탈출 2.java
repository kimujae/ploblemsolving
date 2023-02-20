import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static int[][] map;
    public static int N, M, REDX, REDY, BLUEX, BLUEY, min, comnum;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};
    public static int[] joyStick = {0,1,2,3};
    public static int[] joyStick1 = {1,2,3};
    public static int[] joyStick2 = {0,2,3};
    public static int[] joyStick3 = {0,1,3};
    public static int[] joyStick4 = {0,1,2};
    public static int[] selected;


    public static void main(String[] args) throws IOException {
        selected = new int[10];
        min = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int iInput = 0; iInput < N; iInput++) {
            String str = br.readLine();
            for (int jInput = 0; jInput < M; jInput++) {
                if (str.charAt(jInput) == '#') map[iInput][jInput] = 1;
                else if (str.charAt(jInput) == '.') map[iInput][jInput] = 0;
                else if (str.charAt(jInput) == 'O') map[iInput][jInput] = 9;
                else if (str.charAt(jInput) == 'R') {
                    map[iInput][jInput] = 2;
                    REDX = iInput;
                    REDY = jInput;
                } else if (str.charAt(jInput) == 'B') {
                    map[iInput][jInput] = 3;
                    BLUEX = iInput;
                    BLUEY = jInput;
                }
            }
        }//map정보완성
        br.close();

        combination(0, 5); // 방향키 조합 실행 3^10
        if(min == Integer.MAX_VALUE)bw.write(String.valueOf(-1));
        else bw.write(String.valueOf(min));

        bw.flush();

    }

    static int bfs(int redX, int redY, int blueX, int blueY, int[] joyStick) {
        boolean redGoal = false;
        boolean blueGoal = false;
        int count = 0;

        for (int direction : joyStick) {
            count++;
            while (true) { // 길이면 한번에 이동
                int rnextX = redX + changeX[direction];
                int rnextY = redY + changeY[direction];

                if (rnextX < 0 || rnextX > N - 1 || rnextY < 0 || rnextY > M - 1)
                    break;

                if (map[rnextX][rnextY] == 1)
                    break;

                if (map[rnextX][rnextY] == 9) {
                    redGoal = true;
                    map[redX][redY] = 0;
                    break;
                }

                if (map[rnextX][rnextY] == 3){
                    // 3 - way
                    break;
                }

                map[redX][redY] = 0;
                redX = rnextX;
                redY = rnextY;
                map[redX][redY] = 2;
            }


            while (true) {// 길이면 한번에 이동
                int bnextX = blueX + changeX[direction];
                int bnextY = blueY + changeY[direction];

                if (bnextX < 0 || bnextX > N - 1 || bnextY < 0 || bnextY > M - 1)
                    break;

                if (map[bnextX][bnextY] == 1)
                    break;

                if (map[bnextX][bnextY] == 9) {
                    blueGoal = true;
                    map[blueX][blueY] = 0;
                    break;
                }

                if (map[bnextX][bnextY] == 2) {
                    // 3 - way
                    break;
                }

                map[blueX][blueY] = 0;
                blueX = bnextX;
                blueY = bnextY;
                map[blueX][blueY] = 3;
            }

            while (true) { // 길이면 한번에 이동
                int rnextX = redX + changeX[direction];
                int rnextY = redY + changeY[direction];

                if (rnextX < 0 || rnextX > N - 1 || rnextY < 0 || rnextY > M - 1)
                    break;

                if (map[rnextX][rnextY] == 1)
                    break;

                if (map[rnextX][rnextY] == 9) {
                    redGoal = true;
                    map[redX][redY] = 0;
                    break;
                }

                if (map[rnextX][rnextY] == 3){
                    // 3 - way
                    break;
                }

                map[redX][redY] = 0;
                redX = rnextX;
                redY = rnextY;
                map[redX][redY] = 2;
            }


            while (true) {// 길이면 한번에 이동
                int bnextX = blueX + changeX[direction];
                int bnextY = blueY + changeY[direction];

                if (bnextX < 0 || bnextX > N - 1 || bnextY < 0 || bnextY > M - 1)
                    break;

                if (map[bnextX][bnextY] == 1)
                    break;

                if (map[bnextX][bnextY] == 9) {
                    blueGoal = true;
                    map[blueX][blueY] = 0;
                    break;
                }

                if (map[bnextX][bnextY] == 2) {
                    // 3 - way
                    break;
                }

                map[blueX][blueY] = 0;
                blueX = bnextX;
                blueY = bnextY;
                map[blueX][blueY] = 3;
            }

            if (redGoal || blueGoal) break;
        }


        map[redX][redY] = 0;
        map[blueX][blueY] = 0;
        if(blueGoal) return Integer.MAX_VALUE;
        else if(redGoal) return count;
        else return Integer.MAX_VALUE;
    }


    static void combination(int depth, int prev) {
        if (depth == 10) {
            //bfs 실행
            map[REDX][REDY] = 2;
            map[BLUEX][BLUEY] = 3;
            min = Math.min(min, bfs(REDX, REDY, BLUEX, BLUEY, selected));
            return; // return으로 스택을 반환해줘야 한다~ 핵심
        }

        if(prev ==5) {
            for (int i = 0; i < 4; i++) {
                prev = selected[depth] = joyStick[i];
                combination(depth + 1, prev);

            }
        }else{
            for (int j = 0; j < 3; j++) {
                if (prev == 0) {
                    int para = selected[depth] = joyStick1[j];
                    combination(depth + 1, para);

                }
                else if(prev == 1) {
                    int para  = selected[depth] = joyStick2[j];
                    combination(depth + 1, para);

                }
                else if(prev == 2) {
                    int para  = selected[depth] = joyStick3[j];
                    combination(depth + 1, para);
                }
                else if(prev == 3) {
                    int para  = selected[depth] = joyStick4[j];
                    combination(depth + 1, para);
                }
            }
        }

    }

}


