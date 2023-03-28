import java.util.*;
class Solution {
    public Trie trie;
    public ArrayList<Integer> ans = new ArrayList<>();
    public int index = 1;
    
    public int[] solution(String msg) {
        
        trie = new Trie();
        initMap();
        trie.insert(msg);
        int[] answer = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    public void initMap(){
        for(int i = 0; i < 26; i++){
            char ch = (char)(i+'A');
            trie.init(ch);
        }
    }// 사전 초기화
    
    class Trie{
        TrieNode root;
        Trie(){
            this.root = new TrieNode(0);
        }
        
        void insert(String word){
            TrieNode thisNode = this.root;
            
            for(int i = 0; i < word.length(); i++){
                char present = word.charAt(i);
                
                // 현재 입력과 일치하는 가장 긴 문자열을 검색
                int num = index;
                if(thisNode.getChild().get(present) == null){
                    //w+c단어를 사전에 등록하는 경우
                    ans.add(thisNode.getIdx());
                    thisNode.setLast(false);
                }
            
                thisNode = thisNode.getChild().
                    computeIfAbsent(present, v -> new TrieNode(index++));
                
                
                if(num != index) {
                        //w+c단어를 사전에 등록했으면,
                        //탐색 문자를 현재 입력 문자로 변경
                        thisNode.setLast(true);
                        thisNode= this.root.getChild().get(present);
                    }
                }
            ans.add(thisNode.getIdx());
            }
            
    
        void init(char alphabet){
                TrieNode thisNode = this.root;

                for(int i = 0; i < 1; i++){
                    thisNode = thisNode.getChild().computeIfAbsent(
                        alphabet, v -> new TrieNode(index++));
                }
                thisNode.setLast(true);
            }
        }// Trie class 명세 완료
    
    class TrieNode{
        boolean isLast;
        int idx;
        Map<Character, TrieNode> child = new HashMap<>();
        
        TrieNode(int idx){
            this.idx = idx;
        }
        
        int getIdx(){
            return this.idx;
        }
    
        Map<Character, TrieNode> getChild(){
            return this.child;
        }
        
        boolean isLast(){
            return isLast;
        }
        
        void setLast(boolean isLast){
            this.isLast = isLast;
        }
    }// TrieNode class 명세완료
}