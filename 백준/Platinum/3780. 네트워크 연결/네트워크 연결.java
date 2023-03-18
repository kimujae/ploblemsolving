import java.io.*;
import java.util.StringTokenizer;

public class Main{
    static int T, N;
    static int[][] parent;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        T = Integer.parseInt(br.readLine());


        for(int i = 0; i < T; i++){
            String str = "";
            N = Integer.parseInt(br.readLine());
            parent = new int[N+1][2]; //부모노드, 거리 저장
            makeSet();

            while(!str.equals("O")){
                st = new StringTokenizer(br.readLine());
                str = st.nextToken();

                if(str.equals("E")){
                    //탐색 실행
                    int node = Integer.parseInt(st.nextToken());
                    find(node);

                    bw.write(String.valueOf(parent[node][1]));
                    bw.write("\n");
                }
                else if(str.equals("I")){
                    int center = Integer.parseInt(st.nextToken());
                    int enterprise = Integer.parseInt(st.nextToken());
                    parent[center][0] = enterprise;
                    parent[center][1] = Math.abs(center -  enterprise) %1000;
                }
            }
        }
        bw.flush();
    }



    static int[] find(int x){
        if(x == parent[x][0]) return parent[x];
        int y = parent[x][0];
        int[] root = find(y);
        parent[x][1] += root[1];
        //parent[x][1] = (parent[x][1] + root[1]) % 1000; 이거 대체 왜 틀림?........
        parent[x][0] = root[0];
        return parent[x];
    }

    static void makeSet(){
        for(int i = 0; i < N+1; i++){
            parent[i][0] = i;
        }
    }
}
