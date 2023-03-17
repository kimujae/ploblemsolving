import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.*;
import java.util.Comparator;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PriorityQueue<int[]> valueQ = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2){
            if(o1[1] == o2[1])return o1[0] - o2[0];
            return o2[1] - o1[1];
        }
    });

    static PriorityQueue<int[]> weightQ = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2){
            if(o1[0] == o2[0])return o1[1] - o2[1];
            return o1[0] - o2[0];
        }
    });
    static int N, K;
    static long ans;
    static int[] bag;

    public static void main(String[] args)throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bag = new int[K];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            weightQ.add(new int[]{weight, value});
        }// 보석 정보 입력 완료

        for(int i = 0; i < K; i++){
            bag[i] = Integer.parseInt(br.readLine());
        }//가방 정보 입력 완료

        Arrays.sort(bag);

        int[] gold = new int[2];
        for(int i =0; i < K; i++){
            if(!weightQ.isEmpty()) {
                gold = weightQ.peek();

                while (bag[i] >= gold[0]) {
                    valueQ.add(weightQ.poll());
                    if(weightQ.isEmpty()) break;
                    gold = weightQ.peek();
                }
            }
            if(!valueQ.isEmpty())ans += valueQ.poll()[1];
        }

        System.out.print(ans);
    }

}
