import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dp;
    static int max;
    public static void main(String[] args) throws IOException {
        String str1 = br.readLine();
        String str2 = br.readLine();
        
        System.out.print(lcs(str1,str2)); 
    }

    static int lcs(String str1, String str2) {
        dp = new int[str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            int comm = 0;
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    if (comm < dp[j]) max = Math.max(max, comm = dp[j]);
                    else max = Math.max(max, dp[j] = comm + 1);
                } else max = Math.max(max, comm = Math.max(comm, dp[j]));
            }
        }
        return max;
    }
}

