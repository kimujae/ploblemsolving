import java.util.*;

public class Main {
    public static boolean[] visited;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    public static Scanner scan = new Scanner(System.in);
    public static Queue<Integer> queue = new LinkedList<Integer>();

    public static void main(String[] args) {
        int N, M, V;

        N = scan.nextInt(); // 노드 개수
        M = scan.nextInt(); // 입력받을 그래프 원소 개수
        V = scan.nextInt(); // 시작노드 번호

        visited = new boolean[N+1];

        for(int input = 0; input <= N; input++){
            // graph에 입력받을 인접노드+1(인덱스 0은 활용x) 만큼 배열리스트 추가
            graph.add(new ArrayList<>());
        }

        for(int input = 0; input <M; input++){
            int firstNode = scan.nextInt();
            int secondNode = scan.nextInt();
            graph.get(firstNode).add(secondNode);
            graph.get(secondNode).add(firstNode);
        }

        for(int input = 0; input <= N; input++) {
            Collections.sort(graph.get(input));
        }// 주의) 정렬도 실행해주어야 한다.


        dfs(V);
        System.out.println("");
        for(int i = 0; i < visited.length; i ++){
            visited[i] = false;
        }
        bfs(V);
    }

    public static void dfs(int node){
        visited[node] = true; // 최상단 노드 방문처리
        System.out.print(node + " ");
        for(int recur = 0; recur < graph.get(node).size(); recur++){
            int neighbor = graph.get(node).get(recur);
            if(!visited[neighbor]) dfs(neighbor);
        }
    }

    public static void bfs(int node){
        visited[node] = true;
        queue.add(node);

        while(!queue.isEmpty()){
            for(int element = 0; element < graph.get(node).size(); element++){
                int neighbor = graph.get(node).get(element);
                if(!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
            queue.poll();
            System.out.print(node +" ");
            if(queue.isEmpty())break;
            node = queue.peek();
        }
    }
}
