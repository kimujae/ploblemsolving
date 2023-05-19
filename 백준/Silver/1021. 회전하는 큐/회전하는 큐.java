import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> q;
    static StringTokenizer st;
    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        q = new ArrayList<>();


        for(int i = 0; i < N; i++){
            q.add(i+1);
        }



        /*
        우측 거리 : 제거 위치 - 원점
        좌측 거리 : 원소의 개수 - 우측거리
         */
        // 현재 위치에서 좌, 우로 이동해서 도달 할 수 있는 최소값 -> 좌 우 중 최단경로를 찾으면 되는거 같은데.
        int count = N; // 현재 큐 원소의 개수
        int pos = 0;
        int answer = 0;


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());
            int idx = q.indexOf(target);

            int min = Math.min(Math.abs(idx - pos), q.size() - Math.abs(idx - pos));
            answer += min;


            q.remove(Integer.valueOf(target));
            if(q.isEmpty()) break;
            pos = (idx) % q.size();
        }

        System.out.println(answer);
    }
}
