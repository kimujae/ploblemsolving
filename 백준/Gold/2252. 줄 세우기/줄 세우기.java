import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static Queue<Integer> Q = new LinkedList<>();
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> student = new ArrayList<>();
    public static void main(String[] args)throws IOException{
        //단방향(사이클 존재 x) && 순서가 정해져있을때 && cost를 노드 순서로 치환 가능
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];

        for(int i = 0; i < N+1; i++){
            student.add(new ArrayList<>());
        }

        for(int i = 0; i <M; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            indegree[second]++;
            student.get(first).add(second);
        }

        for(int i = 1; i < N+1; i++){
            if(indegree[i] == 0) Q.add(i);
        }
        topologySort();
        System.out.print(sb);
    }

    static void topologySort(){
        while(!Q.isEmpty()){
            int now = Q.poll();
            sb.append(now).append(" ");
            for(int i = 0; i < student.get(now).size(); i++){
                int next = student.get(now).get(i);

                if(--indegree[next]==0) Q.add(next);
            }
        }
    }
}
