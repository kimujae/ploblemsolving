import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    public boolean[] checkNode;
    public int ans;
    public int nodes;

    public int solution(int[] info, int[][] edges) {
        for(int i = 0; i < info.length; i++){
            // 노드의 개수 만큼 할당
            tree.add(new ArrayList<>());
        }// 트리 할당 완료

        for(int i = 0; i < edges.length; i++){
            int[] node = edges[i];
            int first = node[0];
            int second = node[1];

            tree.get(first).add(second);
        }
        
        nodes = info.length;
        dfs(info, 0,  0,  0, 0);
        return ans;
    }
/*
자식 노드가 n개라면 한 곳을 방문했다가 n-1개의 다른 노드들을 다시 dfs탐색을 해야한다.
*/
    public void dfs(int[] info, int nowNode, int sheep, int wolves, int searchNode){
        // 늑대에게 잡아먹히면 중지.
        if(info[nowNode] == 0) sheep++;
        else wolves++;

        if(wolves >= sheep) return;
        ans = Math.max(ans, sheep);

        for(int i = 0; i < tree.get(nowNode).size(); i++){
                int nextNode = tree.get(nowNode).get(i);// 인접노드 탐색
                searchNode |= 1 << nextNode; // 자손 노드 추가
            }


            for(int j  =0; j < nodes; j++){
                if((searchNode & 1<<j) != 0) dfs(info, j, sheep, wolves, searchNode & ~(1<<j));
            }

        }

    }

