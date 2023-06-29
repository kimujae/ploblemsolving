import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] g1 = new int[8], g2= new int[8], g3= new int[8], g4= new int[8];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                if (i == 0) g1[j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                else if (i == 1) g2[j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                else if (i == 2) g3[j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                else g4[j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        
        int r = Integer.parseInt(br.readLine());// 회전 횟수
        int num = 0, vec = 0;
        int r1 = 0, r2 = 0, r3 = 0, r4 = 0; // 각 바퀴의 회전 변수
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            vec = Integer.parseInt(st.nextToken());

            // 회전 및 인접 바퀴 체크 -> 나머지 세개의 바퀴 모두 체크를 해야한다.
            /*
                1. 바퀴 회전
                2. 인접 바퀴 회전
                3. 회전 여부에 따라 인접바퀴 회전
             */
            int now = 0;
            vec = -vec;
            if(num == 1) {
                now = (2 + r1 + 800) % 8;
                if(g1[now] != g2[(-2 + r2 + 800)%8]){
                    if(g2[(2 + r2 + 800)%8] != g3[(-2 + r3 + 800)%8]){
                        if(g3[(2+r3+800)%8] != g4[(-2 + r4 + 800)%8]){
                            r4 += -vec;
                        }
                        r3 += vec;
                    }
                    r2 += -vec;
                }
                r1 += vec;
            }
            else if(num == 2) {
                now = (2 + r2 + 800)% 8;
                if(g2[now] != g3[(-2 + r3 + 800)%8]){
                    if(g3[(2 + r3 + 800)%8] != g4[(-2 + r4 + 800)%8]){
                        r4 += vec;
                    }
                    r3 += -vec;
                }
                if(g1[(2+r1+800)%8] != g2[(-2+r2+800)%8]) r1 += -vec;
                r2 += vec;
            }
            else if(num == 3) {
                now = (2 + r3 + 800) % 8;
                if(g3[now] != g4[(-2 + r4 + 800)%8]){
                    r4 += -vec;
                }
                if(g2[(2+r2+800)%8] != g3[(-2+r3+800)%8]) {
                    if(g1[(2+r1+800)%8] != g2[(-2 + r2 + 800)%8]) {
                        r1 += vec;
                    }
                    r2 += -vec;
                }
                r3 += vec;
            }
            else {
                now = (-2 + r4 +800) % 8;
                if(g4[now] != g3[(2 + r3 + 800)%8]){
                    if(g2[(2 + r2 + 800)%8] != g3[(-2 + r3 + 800)%8]){
                        if(g1[(2 + r1+800)%8] != g2[(-2 + r2 + 800)%8]){
                            r1 += -vec;
                        }
                        r2 += vec;
                    }
                    r3 += -vec;
                }
                r4 += vec;
            }
        }

        int answer = 0;
        if(g1[(r1+800)%8] == 1) answer += 1;
        if(g2[(r2+800)%8] == 1) answer += 2;
        if(g3[(r3+800)%8] == 1) answer += 4;
        if(g4[(r4+800)%8] == 1) answer += 8;
        System.out.println(answer);
    }
}
