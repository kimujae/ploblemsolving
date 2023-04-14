import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static Map<Integer, int[]> map = new HashMap<>();
    static int[][] target;
    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        target =new int[K][3];

        for(int input = 0 ; input < K; input++){
            st= new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            //타겟 넘버와, RC를 저장 <- 순회 목적
            target[input][0] = X;
            target[input][1] = R-1;
            target[input][2] = C-1;

            if(map.get(X) == null) {
                if(X%N == 0) map.put(X, new int[]{(X-1)/N, N-1});
                else map.put(X, new int[]{(X-1)/N, (X-1)%N});
            }
        }//타겟과, 좌표 입력 완료

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < K ; i++){
            sb.append(rotate(i)).append('\n');
        }
        System.out.print(sb);
    }


    static int rotate(int idx){
        int X = target[idx][0];
        int tr = target[idx][1];
        int tc = target[idx][2];


        int[] now = map.get(X);
        int nowR = now[0];
        int nowC = now[1];


        int rotateR = 0;
        if(nowC <= tc) rotateR = tc -  nowC;
        else if(nowC > tc){
            rotateR = tc+N - nowC;
        }


        int rotateC = 0;
        if(nowR <= tr) rotateC = tr -  nowR;
        else {
            rotateC = tr+N - nowR;
        }
        // 회전 횟수 계산 완료

        map.put(X, new int[]{tr, tc});

        for(int nextX : map.keySet()){
            if(nextX == X) continue;

            int[] next = map.get(nextX);
            int nextR = next[0];
            int nextC = next[1];

            if(nextR == nowR) nextC = (nextC + rotateR) % N;
            if(nextC == tc) nextR = (nextR + rotateC) % N;

            
            map.put(nextX, new int[]{nextR, nextC});
        }
        

        return rotateC + rotateR;
    }
}
