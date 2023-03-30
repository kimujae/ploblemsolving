import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,K;
    static String[] words;
    static boolean[] visited;
    static int set, ans;
    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[26];
        words = new String[N];

        for(int i = 0; i < N; i++){
            String word = br.readLine();
            words[i] = word.substring(4, 4 + word.length() - 8);
        }//단어 입력 완료

        if(K >= 5) {
            InitSet("anta");
            InitSet("tica");
            dfs(0, 0);
        }
        else ans = 0;
        System.out.print(ans);
    }

    static void InitSet(String word){
        for(int i = 0; i < word.length(); i++){
            int num = word.charAt(i) - 'a';
            set |= 1<<num;
            visited[num] = true;
        }
    }

    static void dfs(int depth, int num){
        if(depth == K-5){
            int read = 0;
            for(int i = 0; i < N; i++){
                boolean r = true;
                for(int j = 0 ; j < words[i].length(); j++){
                    if((set & 1 << (words[i].charAt(j)-'a')) == 0) {
                        r  = false;
                        break;
                    }
                }
                if(r) read++;
            }
            ans = Math.max(ans, read);
            return;
        }

        for(int i = num; i < 26; i++){
            if((set & 1<<i) != 0)
                continue;

            set |= 1<<i;
            dfs(depth+1, i);
            set &= ~(1<<i);
        }
    }
}
