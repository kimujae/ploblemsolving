import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        TreeSet<String> pq = new TreeSet<String>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if(o1.equals(o2)) return 0;
                if(o1.length() == o2.length()) {
                    int idx = 0;
                    while(o1.charAt(idx) == o2.charAt(idx)) {
                        idx++;
                    }
                    return o1.charAt(idx) - o2.charAt(idx);
                }
                else return o1.length() - o2.length();
            }
        });


        for(int i = 0; i < n; i++) {
            pq.add(br.readLine());
        }

        for(String s : pq) {
            System.out.println(s);
        }
    }
}
