import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,K;
    static int[] words;
    static int set, ans;
    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new int[N];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            String word = str.substring(4, 4 + str.length() - 8);
            for(int j = 0; j < word.length(); j++){
                words[i] |= 1<< (word.charAt(j) -'a');
            }
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
        }
    }

    static void dfs(int depth, int num){
        if(depth == K-5){
            int read = 0;
            for(int i = 0; i < N; i++){
                if((set & words[i]) == words[i]) read++;
            }
            ans = Math.max(ans, read);
            return;
        }

        for(int i = num; i < 26; i++){
            // i =0 이 아니라 num이다.....
            if((set & 1<<i) != 0)
                continue;

            set |= 1<<i;
            dfs(depth+1, i+1);
            set &= ~(1<<i);
        }
    }
}
