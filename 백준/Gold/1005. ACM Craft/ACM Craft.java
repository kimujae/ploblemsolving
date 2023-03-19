import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, N, K, building;//테스트케이스 , 건물개수, 규칙 개수
    static ArrayList<ArrayList<Integer>> graph ;
    static Queue<Integer> Q;
    static int[] time, result;
    static int[] reach;

    public static void main(String[] args)throws IOException{
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            //시간배열, 그래프 초기화
            time = new int[N+1];
            result = new int[N+1];
            reach = new int[N+1];

            graph = new ArrayList<>(); 
            // tc많을 때 제발 전역변수 초기화 잘 하자.......................
            for(int j = 0; j < N+1; j++){
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                time[j] = Integer.parseInt(st.nextToken());
            }//소요 시간 입력 완료

            for(int j = 0; j < K; j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int dest = Integer.parseInt(st.nextToken());
                reach[dest]++;
                graph.get(start).add(dest);
            }//그래프 입력 완료

            building = Integer.parseInt(br.readLine());
            Q = new LinkedList<>();
            for(int j = 1; j <N+1; j++){
                if(reach[j] == 0) {
                    Q.add(j);
                    result[j] = time[j];
                }
            }
            topology();
            sb.append(result[building]).append('\n');
        }
        System.out.print(sb);
    }

    static void topology(){
        while(!Q.isEmpty()){
            int curr = Q.poll();
            if(curr == building) break;

            for(int i = 0; i < graph.get(curr).size(); i++){
                int next = graph.get(curr).get(i);

                if(time[next] + result[curr] > result[next]){
                    result[next] = time[next] + result[curr];
                }

                if(--reach[next] == 0) Q.add(next);
            }
        }
    }
}