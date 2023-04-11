import java.util.*;
class Solution {
    class Trie{
        TrieNode root;
        
        Trie(){
            this.root = new TrieNode();
        }
        
        boolean insert(String word){
            TrieNode thisNode = this.root;
            
            for(int i = 0; i < word.length(); i++){
                thisNode = thisNode.getChild().computeIfAbsent(word.charAt(i), v -> new TrieNode());
                if(thisNode.getLast()) return false;
            }
            thisNode.isLast = true;
            return true;
        }
        
        
        
    }
    
    class TrieNode{
        boolean isLast;
        Map<Character , TrieNode> child = new HashMap<>();
        
        boolean getLast(){
            return this.isLast;
        }
        
        Map<Character, TrieNode> getChild(){
            return this.child;
        }
    }
    
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, (String s1, String s2) -> s1.length() - s2.length());
        Trie trie = new Trie();
        
        boolean check = true;
        for(int i = 0; i < phone_book.length; i++){
            if(!trie.insert(phone_book[i])) {
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}