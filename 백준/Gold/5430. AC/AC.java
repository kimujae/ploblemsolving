import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;


public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        a : for(int  i = 0; i < tc; i++) {

            Deque<Integer> dq = new LinkedList<>();
            String inst = br.readLine();
            int len = Integer.parseInt(br.readLine());
            String str = br.readLine();
            String[] arr = str.substring(1,str.length()-1).split(",");
            for(int k = 0; k< arr.length; k++) {
                if(arr[k].length() == 0) break;
                dq.add(Integer.parseInt(arr[k]));
            }

            boolean isFront = true;
            for(int k = 0; k < inst.length(); k++) {
                char c = inst.charAt(k);
                if(c == 'R') isFront = !isFront;
                else {
                    if(dq.isEmpty()){
                        sb.append("error");
                        sb.append('\n');
                        continue a;
                    }

                    if(isFront) {
                        dq.pollFirst();
                    }else {
                        dq.pollLast();
                    }
                }
            }



            sb.append('[');
            if(isFront) {
                while(dq.size() > 1) {
                    int num = dq.pollFirst();
                    sb.append(num);
                    sb.append(',');
                }
                if(!dq.isEmpty()) sb.append(dq.pollFirst());
            } else {
                while(dq.size() > 1) {
                    int num = dq.pollLast();
                    sb.append(num);
                    sb.append(',');
                }
                if(!dq.isEmpty()) sb.append(dq.pollLast());
            }

            sb.append(']');
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
