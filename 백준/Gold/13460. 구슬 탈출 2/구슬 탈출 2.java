import java.io.*;
import java.util.StringTokenizer;

public class Main{
    public static int[][] map;
    public static int N, M, REDX, REDY, BLUEX, BLUEY, min;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static int[] changeX = {-1, 0, 1, 0};
    public static int[] changeY = {0, -1, 0, 1};
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

        bfs(1, REDX, REDY, BLUEX, BLUEY, 5);// 방향키 조합 실행 4 * 3^9 - 막힌길case
        if(min == Integer.MAX_VALUE)bw.write(String.valueOf(-1));
        else bw.write(String.valueOf(min));

        bw.flush();

    }

    static void bfs(int depth, int rx, int ry, int bx, int by, int prev) {
        if(depth == 11) return;

        for (int i = 0 ; i < 4; i++) {
            int redX = rx;
            int redY = ry;
            int blueX = bx;
            int blueY = by;
            if(prev == i) continue;
            if(prev == 0 && i == 2) continue;
            if(prev == 1 && i == 3) continue;
            if(prev == 2 && i == 0) continue;
            if(prev == 3 && i == 1) continue;
            if(map[redX + changeX[i]][redY + changeY[i]] == 1 && map[blueX + changeX[i]][blueY + changeY[i]] == 1) continue;

            int rmov = 0;
            int bmov = 0;

            while (map[redX + changeX[i]][redY + changeY[i]] != 1) { // 길이면 한번에 이동
                redX += changeX[i];
                redY += changeY[i];
                rmov++;
                if(map[redX][redY] == 9) break;
            }

            while (map[blueX + changeX[i]][blueY + changeY[i]] != 1) { // 길이면 한번에 이동
                blueX += changeX[i];
                blueY += changeY[i];
                bmov++;
                if(map[blueX][blueY] == 9) break;
            }

            if (map[blueX][blueY] == 9) { //실패
                continue; //탐색 지속
            }

            if(map[redX][redY] == 9) { //성공
                min = Math.min(depth, min);
                break; //탐색 끝
            }

            if(redX == blueX && redY == blueY){// 공이 같은 위치에 있다면, 많이 이동한 공을 한 칸 뒤로 보낸다
                if(bmov < rmov){
                    redX = redX - changeX[i];
                    redY = redY - changeY[i];
                }
                else if(bmov > rmov){
                    blueX = blueX - changeX[i];
                    blueY = blueY - changeY[i];
                }
            }

            bfs(depth+1, redX, redY, blueX, blueY, i);
        }
    }
}


