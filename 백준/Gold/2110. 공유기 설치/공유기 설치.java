import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, C, min, max;
    static int[] house;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());//집의 개수
        C = Integer.parseInt(st.nextToken());//공유기 개수
        min = Integer.MAX_VALUE;
        house = new int[N];

        for(int i = 0; i < N; i++){
            house[i] = Integer.parseInt(br.readLine());
        }//집 좌표 입력 완료

        Arrays.sort(house);

        int prev = house[0];
        int curr = 0;
        for(int i = 1; i < N; i++){
            curr = house[i];
            min = Math.min(min, curr -prev);
            prev = curr;
        }
        max = house[N-1] - house[0];

        System.out.println(binarySearch(min, max));
    }

    static int binarySearch(int min, int max){
        if(min > max)return max;
        int mid = (min + max)/2;


        int wifi = 1;
        int prev = house[0];
        for(int i = 1; i < N; i++){
            int curr = house[i];
            if(curr - prev >= mid){
                wifi++;
                prev = curr;
            }
        }

        if(wifi >= C)return binarySearch(mid+1, max);
        else return binarySearch(min, mid-1);
    }


}