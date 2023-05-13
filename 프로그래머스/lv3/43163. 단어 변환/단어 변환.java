class Solution {
    private int d = Integer.MAX_VALUE;
    private boolean[] word;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        word = new boolean[words.length];
        dfs(begin, target, words, 0);
        
        if(d== Integer.MAX_VALUE) return 0;
        return d;
    }
    
    
    void dfs(String begin, String target, String[] words, int dist){
        if(begin.equals(target)) d = Math.min(d, dist);
        
        for(int i = 0; i < begin.length(); i++){
           a:for(int j = 0; j < words.length; j++){    
                for(int k = 0; k < words[j].length(); k++){
                    if(k != i && begin.charAt(k) != words[j].charAt(k))
                        continue a; 
                }
                 
                if(word[j])
                    continue;
                
                word[j] = true;
                dfs(words[j], target, words, dist+1);
                word[j] = false;
            }
        }
    }
}