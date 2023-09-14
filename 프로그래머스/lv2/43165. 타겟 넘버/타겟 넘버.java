class Solution {
    private int answer;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    public void dfs(int depth, int num, int[] numbers, int target) {
        if(depth == numbers.length) {
             if(num == target) answer += 1;
             return;
        }
        dfs(depth + 1, num - numbers[depth], numbers, target);
        dfs(depth + 1, num + numbers[depth], numbers, target);
    }
}