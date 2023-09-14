class Solution {
    private int min = Integer.MAX_VALUE;
    public int solution(int N, int number) {
        int ans = 0;
        for(int i = 0; i < 4; i++){
            dfs(0, number, 0, N, i);
        }
        if(min != Integer.MAX_VALUE) return min;
        return -1;
    }
    
    
    public void dfs(int depth, int target, int num, int N, int cal) {
        if(num == target) min = Math.min(min, depth);
        if(depth >= 8) return;
        
        int concat = 1;
        for(int i = 1; i <= 8 - depth; i++) {
            String s = "";
            for(int j = 0; j < i; j++) {
                s += N;
            }
            concat = Integer.valueOf(s);
            int n = 0;
            if(cal == 0) n = num + concat;
            else if(cal == 1) n = Math.abs(num - concat);
            else if(cal == 2 && num != 0) n = num * concat;
            else if(cal == 3 && num != 0) {
                if(concat > num) n = concat / num;
                else n = num/ concat;
            }

            dfs(depth + i, target, n, N, 0);
            dfs(depth + i, target, n, N, 1);
            dfs(depth + i, target, n, N, 2);
            dfs(depth + i, target, n, N, 3);
        }
    }
}