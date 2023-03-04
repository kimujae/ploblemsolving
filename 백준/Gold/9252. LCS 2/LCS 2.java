import java.io.*;
public class Main{
    static String str1, str2;
    static int[][] dp;
    static int ans;
    static char[] ansStr;
    static boolean[][] visited;
    static StringBuilder sb;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args)throws IOException{
        str1 = br.readLine();
        str2 = br.readLine();
        dp = new int[str1.length() + 1][str2.length() + 1];
        visited = new boolean[str1.length() + 1][str2.length() + 1];


        ans = dp(str1.length()+1 , str2.length()+1);
        bw.write(String.valueOf(ans));
        if(ans != 0) {
            ansStr = new char[ans];
            reverseDp(str1.length() , str2.length(), ans-1);
            bw.write('\n');
            bw.write(ansStr);
        }
        bw.flush();
    }

    /*
ABCDEF
BEFDEFACDFABZ
     */
    static int dp(int idx1, int idx2){
        for(int i = 1; i < idx1; i++){
            for(int j = 1; j < idx2; j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[str1.length()][str2.length()];
    }

    static void reverseDp(int idx1, int idx2, int find) {
        if(idx1 == 0 || idx2 == 0) return;

        if(dp[idx1 - 1][idx2] == dp[idx1][idx2]) reverseDp(idx1 - 1, idx2, find);
        else if(dp[idx1][idx2 -1] == dp[idx1][idx2]) reverseDp(idx1, idx2 - 1, find);
        else if(dp[idx1][idx2] == dp[idx1-1][idx2-1] + 1){
            ansStr[find] =  str1.charAt(idx1 - 1);
            find--;
            reverseDp(idx1 - 1, idx2 - 1, find);
        }

    }
}
