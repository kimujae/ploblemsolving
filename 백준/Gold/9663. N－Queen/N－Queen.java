import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] colCheck, mindiagCheck, pldiagCheck;
    static int N, ans;

    public static void main(String[] args)throws IOException {
        N = Integer.parseInt(br.readLine());
        colCheck = new boolean[N];
        mindiagCheck = new boolean[2*N];
        pldiagCheck = new boolean[2*N];

        combination(0);
        System.out.print(ans);
    }

    static void combination(int row){
        if(row == N) {
            ans ++;
            return;
        }

        for(int col = 0; col < N; col++){
            //j는 열에 놓을 수 있는 경우의 수
            int mindiag = row - col + N-1;
            int pldiag = row + col;
            if(colCheck[col] || mindiagCheck[mindiag] || pldiagCheck[pldiag])
                continue;

            addSet(col, mindiag, pldiag);
            combination(row+1);
            removeSet(col, mindiag, pldiag);
        }
    }

    static void addSet(int col, int mindiag, int pldiag){
        colCheck[col] =true;
        mindiagCheck[mindiag] = true;
        pldiagCheck[pldiag] = true;
    }

    static void removeSet(int col, int mindiag, int pldiag){
        colCheck[col] = false;
        mindiagCheck[mindiag] = false;
        pldiagCheck[pldiag] = false;
    }
}