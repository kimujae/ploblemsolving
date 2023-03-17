import java.util.StringTokenizer;
import java.io.*;
import java.util.Arrays;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int L, C, vowels, consonants; //모음, 자음
    static boolean[] visited;
    static char[] ans;
    static char[] characters;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ans = new char[L];
        characters = new char[C];
        visited = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            characters[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(characters);


        combination(0, 0);
        System.out.print(sb);
    }

    static void combination(int num, int depth){
        if(depth == L){
            if(vowels>=1 && consonants >=2) {
                for (char ch : ans) {
                    sb.append(ch);
                }
                sb.append('\n');
            }
            return;
        }

        for(int i = num; i < C; i++){
            if(visited[i])
                continue;

            visited[i] = true;
            if(isVowel(i))vowels++;
            else consonants++;

            ans[depth] = characters[i];
            combination(i, depth+1);

            visited[i] = false;
            if(isVowel(i))vowels--;
            else consonants--;
            }

        }

        
    static boolean isVowel(int idx){
        if(characters[idx] == 'a'||characters[idx] == 'e'||characters[idx] == 'i'||characters[idx] == 'o'||characters[idx] == 'u')
            return true;
        else return false;
    }
}
