import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m , start, end, road;
    static int[] time, reverseTrack, reach;
    static boolean[] visited;
    static Queue<int[]> city = new LinkedList<>();

    static Stack<int[]> track = new Stack<>();
    static Queue<Integer> trackQ = new LinkedList<>();
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static ArrayList<ArrayList<int[]>> reverseGraph = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        n= Integer.parseInt(br.readLine());
        m= Integer.parseInt(br.readLine());

        time = new int[n+1];
        reverseTrack = new int[n+1];
        reach = new int[n+1];
        visited= new boolean[n+1];
        Arrays.fill(time, 0);
        for(int i = 0; i < n+1; i++){
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());

        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(start).add(new int[]{dest, time});
            reach[dest]++;
            reverseGraph.get(dest).add(new int[]{start, time});
            //단방향 그래프
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());


        topology(start);
        visited= new boolean[n+1];
        bfs();
        System.out.println(time[end]);
        System.out.print(road);
    }

    static void topology(int start){
        time[start] = 0;
        city.add(new int[]{start});
        //reach[start] = 1;

        while(!city.isEmpty()){
            int[] curr = city.poll();
            int curCity = curr[0];


            for(int i = 0; i < graph.get(curCity).size(); i++){
                int[] next = graph.get(curCity).get(i);
                int nextCity = next[0];
                int nextCost = next[1];


                if(time[nextCity] < time[curCity] + nextCost) {
                    time[nextCity] = time[curCity] + nextCost;
                }

                reach[nextCity]--;
                if(reach[nextCity] == 0) city.add(new int[]{nextCity});
            }
        }
    }


    private static void bfs() {
        visited[end]=true;
        trackQ.add(end);

        while (!trackQ.isEmpty()){
            int curCity = trackQ.poll();

            for(int i = 0; i < reverseGraph.get(curCity).size(); i++) {
                int[] next = reverseGraph.get(curCity).get(i);
                int nextCity = next[0];
                int nextCost = next[1];


                if(time[curCity]- nextCost == time[nextCity]){
                    road++;
                    if(!visited[nextCity]){
                        visited[nextCity]=true;
                        trackQ.add(nextCity);
                    }
                }
            }
        }
    }
}