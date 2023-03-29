import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int ans;
    static class Trie{
        TrieNode root;

        Trie(){
            this.root = new TrieNode();
        }

        void insert(String word){
            TrieNode thisNode = this.root;
            for(int i = 0; i < word.length(); i++){
                if(thisNode.getChild().get(word.charAt(i)) != null) thisNode = thisNode.getChild().get(word.charAt(i));
                else{
                    ans = 0;
                    break;
                }
                if(thisNode.getLast()) {
                    thisNode = this.root;
                    ans = 1;
                }
                else ans = 0;
            }
        }

        void setInit(String word){
            TrieNode thisNode = this.root;
            for(int i = 0; i < word.length(); i++){
                thisNode = thisNode.getChild().computeIfAbsent(word.charAt(i), v -> new TrieNode());
            }
            thisNode.setLast(true);
        }
    }

    static class TrieNode{
        Map<Character, TrieNode> child =  new HashMap<>();
        boolean isLast;

        void setLast(boolean isLast){
            this.isLast = isLast;
        }

        boolean getLast(){
            return this.isLast;
        }

        Map<Character, TrieNode> getChild(){
            return this.child;
        }
    }

    public static void main(String[] args)throws IOException {
        Trie trie = new Trie();
        trie.setInit("pi");
        trie.setInit("ka");
        trie.setInit("chu");

        String str = br.readLine();
        trie.insert(str);

        if(ans == 1) System.out.print("YES");
        else System.out.print("NO");


    }
}
