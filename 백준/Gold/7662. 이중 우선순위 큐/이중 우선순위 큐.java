import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Collections;
import java.io.*;
import java.util.HashMap;

public class Main {
    static PriorityQueue<Integer> highQ;
    static PriorityQueue<Integer> lowQ;
    static HashMap<Integer, Integer> map;
    static int T, k, integer;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static char inst;



    public static void main(String[] args)throws IOException{
        /*
        동일한 원소
         */
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            //테스트케이스 진행
            highQ = new PriorityQueue<>(Collections.reverseOrder());
            lowQ = new PriorityQueue<>();
            map = new HashMap<>();
            k = Integer.parseInt(br.readLine());
            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                inst = st.nextToken().charAt(0);
                integer = Integer.parseInt(st.nextToken());

                if (inst == 'I') insertQ(integer);
                else if (inst == 'D' && integer == -1) deleteLow();
                else if (inst == 'D' && integer == 1) deleteHigh();
            }
            getResult();
            bw.write('\n');
            bw.flush();
        }
    }

    static void insertQ(int integer){
        lowQ.add(integer);
        highQ.add(integer);
        if(map.get(integer) != null) map.put(integer, map.get(integer)+1);
        else map.put(integer, 1);
    }

    static void deleteLow(){
        if(lowQ.isEmpty()) return;
        int deleteInt = lowQ.poll();
        while(map.get(deleteInt) == 0){
            if(lowQ.isEmpty()) return;
            deleteInt = lowQ.poll();
        }
        map.put(deleteInt, map.get(deleteInt)-1);
    }


    static void deleteHigh(){
        if(highQ.isEmpty()) return;
        int deleteInt = highQ.poll();
        while(map.get(deleteInt) == 0){
            if(highQ.isEmpty()) return;
            deleteInt = highQ.poll();
        }
        map.put(deleteInt, map.get(deleteInt)-1);
    }

    static void getResult() throws IOException{
        int integer;
        if(lowQ.isEmpty() || highQ.isEmpty()) {
            bw.write("EMPTY");
            return;
        }
        else{
            while(map.get(lowQ.peek()) == 0){
                lowQ.poll();
                if(lowQ.isEmpty()) {
                    bw.write("EMPTY");
                    return;
                }
            }
            while(map.get(highQ.peek()) == 0){
                highQ.poll();
                if(highQ.isEmpty()) {
                    bw.write("EMPTY");
                    return;
                }
            }
            bw.write(String.valueOf(highQ.poll()));
            bw.write(" ");
            bw.write(String.valueOf(lowQ.poll()));
            //System.out.println(lowQ.size());
        }
    }
}
/*
1
9
I 36
I 37
I 38
D 1
D 1
I 39
I 40
D -1
D -1

1
7
I 1
I 1
I 1
I 1
I 1
I 1
D -1

1
7
I 5
I 3
I 7
I 6
D 1
D -1
D -1
 */