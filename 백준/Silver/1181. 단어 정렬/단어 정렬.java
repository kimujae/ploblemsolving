import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        TreeSet<String> pq = new TreeSet<String>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                else return o1.length() - o2.length();
            }
        });


        for(int i = 0; i < n; i++) {
            pq.add(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        for(String s : pq) {
            sb.append(s);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
