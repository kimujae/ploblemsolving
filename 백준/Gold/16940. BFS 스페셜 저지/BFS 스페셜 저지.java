import java.io.*;
import java.util.*;

public class Main{
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static int[][] map;//depth별 노드 개수를 저장하고 있는 배열
    static int[] dist;
    static boolean[] visited; //N개
    static int PREV_NODE = 0;
    static int LINKS = 1;
    static int N , now;
    static Deque<Integer> ansQ = new LinkedList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    
    public static void main(String[] args)throws IOException {
        setInit();
        setGraph();
        bfs();
        
        st = new StringTokenizer(br.readLine());
        now = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

        if(now != 1) output(0);// 시작정점이 1이 아니면 0 리턴
        else {
            ansQ.add(now);
            while (!ansQ.isEmpty()) {
                now = ansQ.poll();

                for (int iter = 0; iter < map[LINKS][now]; iter++) {
                    // now노드에 걸려있는 인접노드만큼 탐색(최정점노드부터 탐색)
                    int next = Integer.parseInt(st.nextToken());
                    if (dist[now] + 1 != dist[next] || map[PREV_NODE][next] != now) {
                        //인접노드가 아닌지 판단 : !(depth가 1 차이, 간선으로 링크된 노드)
                        output(0);
                        return;
                    }
                    ansQ.add(next);
                }
            }
            output(1);
        }
        return;
    }
    
    
    static void setInit() throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[2][N + 1]; //  상위 노드 , link노드 개수
        dist = new int[N + 1];
        visited = new boolean[N + 1];
    }
    
    static void output(int ans) throws IOException{
        bw.write(String.valueOf(ans));
        bw.flush();
    }
    
    static void setGraph() throws IOException{
        for(int input = 0; input <= N; input++){
            // graph에 입력받을 인접노드+1(인덱스 0은 활용x) 만큼 배열리스트 추가
            graph.add(new ArrayList<>());
        }

        for(int input = 0; input < N-1 ; input++){
            st = new StringTokenizer(br.readLine());

            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            graph.get(firstNode).add(secondNode);
            graph.get(secondNode).add(firstNode);
        }// 그래프 완성
    }
    
    static void bfs(){
        queue.add(1); // 최정점 노드 == 1
        visited[1] = true;
        
        while(!queue.isEmpty()){

            int node = queue.poll();
            for(int i = 0; i < graph.get(node).size(); i++){

                int nextNode = graph.get(node).get(i);

                if(visited[nextNode])
                    continue;

                queue.add(nextNode);
                visited[nextNode] = true; //인접노드 방문
                //연결 노드 수 증가 , 현재 노드를 상위노드로 할당
                map[LINKS][node]++;
                map[PREV_NODE][nextNode] = node;
                dist[nextNode] = dist[node]+1;
            }
        }
    }
}




