import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n, k, m, result, count;
        result = 0;
        n = scan.nextInt(); // 멀티탭 구멍 개수
        k = scan.nextInt(); // 멀티탭 사용횟수


        int[] use_pattern = new int[k]; //사용패턴
        boolean[] use = new boolean[k]; // 각 전기용품이 멀티탭에 꽂혀있는지 여부
        int[] multitap = new int[n]; // 멀티탭에 꽂혀있는 전기용품
        boolean scheduled = false;


        for(int x = 0; x < k; x++){
            use_pattern[x] = scan.nextInt();
        }


        count = 0;
        for(int y = 0; y < k ; y++) {
            m =0;
            int g = y;
            if (!scheduled) {
                if(count > 0 && multitap[count-1] == use_pattern[y]) {}
                else{
                    use[use_pattern[y] - 1] = true;
                    multitap[count] = use_pattern[y];
                    count++;
                    if (count == n) scheduled = true;
                    }
                }


            else if (use[use_pattern[y] -1] == true){
            }

            else if(use[use_pattern[y] - 1 ] == false){
                int max, change, distance;
                max = 0;
                change = 0;
                distance = 0;
                int i;
                for (i = 0; i < n; i++) {
                    for(int x = y; x < k ; x++) {
                        if(use_pattern[x] == multitap[i]){
                            break;
                        }
                        else if(max <= distance){
                            change = i;
                            max = distance;
                            distance++;
                        }
                        else {
                            distance++;
                        }
                    }
                    distance = 0;
                }
                use[multitap[change] - 1] = false;
                result++;
                multitap[change] = use_pattern[y];
                use[multitap[change] - 1] = true;

            }
        }
        System.out.println(result);
    }
}
