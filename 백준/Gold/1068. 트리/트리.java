import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, target;
    static int erase;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());

        for(int i=0; i < N+1; i++){
            tree.add(new ArrayList<>());
        }

        int root= 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) root= i;
            else tree.get(parent).add(i);
        }//트리 입력 완료

        target = Integer.parseInt(br.readLine());

        System.out.println(dfs(root, target));
    }

    static int dfs(int node , int target){
        if(target == node) return 0;
        if(tree.get(node).size() == 0) return 1;

        int leaf = 0;
        for(int i = 0; i < tree.get(node).size(); i++){
            int next = tree.get(node).get(i);
            leaf += dfs(next, target);
        }
        if(leaf == 0 && tree.get(node).size() == 1) return 1;
        return leaf;
    }
}
