import java.io.*;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, sum, max;
    static int[] lec;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lec = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            sum += lec[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, lec[i]);
        }//강의 시간 입력 완료
        System.out.println(binarySearch(max, sum)+1);
    }
    
    
    static int binarySearch(int min, int max){
        if(min > max) return max;
        int mid = (min + max) /2;

        int blueray = 0, pre_sum = 0, curr = 0;      
        for(int i = 0; i < N; i++){
            pre_sum += curr = lec[i];

            if(pre_sum > mid){
                blueray++;
                pre_sum = curr;
            }
            if(i == N-1) blueray++;
        }
        
        if(blueray <= M) return binarySearch(min, mid-1);
        else return binarySearch(mid+1, max);
    }
}