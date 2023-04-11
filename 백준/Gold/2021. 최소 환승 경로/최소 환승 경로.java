import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, L;

    static ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> station = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    static boolean[] lineV, stationV;

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        lineV =new boolean[L+1];
        stationV= new boolean[N+1];

        for(int i = 0; i < N+1; i++){
            station.add(new ArrayList<>());
        }

        for(int i = 0; i < L+1; i++){
            lines.add(new ArrayList<>());
        }


        int line = 100001;
        for(int i = 0; i < L ; i++){
            st = new StringTokenizer(br.readLine());
            while(true) {
                int stat = Integer.parseInt(st.nextToken());
                if(stat == -1) break;
                lines.get(i).add(stat);
                station.get(stat).add(i);
            }
            line++;
        }// 역 배열 입력 완료



        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int ans = bfs(start, end);
        System.out.print(ans);
    }


    static int bfs(int start, int end){
        q.add(new int[]{start, -1});

        while(!q.isEmpty()){
            int[] node = q.poll();
            int now = node[0];
            int nowDist = node[1];


            if (now == end) {
                if (nowDist == -1) return 0;
                return nowDist;
            }

            for (int i = 0; i < station.get(now).size(); i++) {
                int line = station.get(now).get(i);

                if (lineV[line])
                    continue;

                lineV[line] = true;
                for (int j = 0; j < lines.get(line).size(); j++) {
                    int next = lines.get(line).get(j);
                    int dist = nowDist;

                    if(stationV[next])
                        continue;

                    q.add(new int[]{next, dist+1});
                    stationV[next]= true;
                }
            }
        }
        return -1;
    }
}
