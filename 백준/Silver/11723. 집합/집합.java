import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int M;
    static long set;

    public static void main(String[] args)throws IOException {
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        initSet();
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String inst = st.nextToken();
            if(inst.equals("add")) add(Integer.parseInt(st.nextToken()));
            else if(inst.equals("remove"))remove(Integer.parseInt(st.nextToken()));
            else if(inst.equals("check"))sb.append(check(Integer.parseInt(st.nextToken()))).append('\n');
            else if(inst.equals("toggle"))toggle(Integer.parseInt(st.nextToken()));
            else if(inst.equals("all"))all();
            else empty();// empty명령어
        }
        System.out.print(sb);
    }

    static void initSet(){
        set = 1 << 4;
    }
    static void add(int n){
        int num = 1<<(n-1);
        set |= num;
    }

    static void remove(int n){
        int num = ~(1<<(n-1));
        set &= num;
    }

    static int check(int n){
        int num = 1 <<(n-1);

        if((set&num) != 0) return 1;
        else return 0;
    }

    static void toggle(int n){
        int num = 1<<(n-1);
        set ^= num;
    }

    static void all(){
        initSet();
        set = ~set;
    }

    static void empty(){
        initSet();
    }
}
