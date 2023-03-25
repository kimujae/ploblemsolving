import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int t, n;

    static class TrieNode{
        boolean isLast;
        Map<Character, TrieNode> child = new HashMap<>();

        Map<Character, TrieNode> getChild(){
            return this.child;
        }

        boolean isLast(){
            return this.isLast;
        }

        void setLast(boolean isLast){
            this.isLast = isLast;
        }
    }

    static class Trie{
        TrieNode root;

        Trie(){
            this.root = new TrieNode();
        }

        boolean insert(String word){
            TrieNode thisNode = this.root;

            for(int i = 0; i < word.length(); i++){
                thisNode =  thisNode.getChild().computeIfAbsent(word.charAt(i), v -> new TrieNode());
                if(thisNode.isLast()){
                    return false;
                }
            }
            thisNode.setLast(true);
            return true;
        }
    }

    
    public static void main(String[] args)throws IOException {
        t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            n = Integer.parseInt(br.readLine());
            String[] words = new String[n];
            for(int i = 0; i < n; i++){
                words[i] = br.readLine();
            }
            Arrays.sort(words, (String s1, String s2) -> s1.length() - s2.length());

            Trie trie = new Trie();
            boolean isConsistent = true;
            for(int i = 0; i < n; i++){
                if(!trie.insert(words[i])) {
                    isConsistent = false;
                    break;
                }
            }

            if(isConsistent) bw.write("YES");
            else bw.write("NO");
            bw.write('\n');
        }
        bw.flush();
    }
}
