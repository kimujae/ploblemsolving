class Solution {
    private boolean[] visit;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visit = new boolean[computers.length];
        for(int i = 0; i < computers.length; i++) {
            if(visit[i]) continue;
            
            dfs(computers, i);
            answer++;
        }
        return answer;
    }
    
    public void dfs(int[][] computers, int seq) {
        visit[seq] = true;
        for(int i = 0; i < computers[seq].length; i++) {
            if(visit[i] || computers[seq][i] == 0) continue;
            
            dfs(computers, i);
        }
    }
}