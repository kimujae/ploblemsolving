import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		int T = Integer.parseInt(input.readLine());
		
		for (int i=0; i<T; i++) {
			int k = Integer.parseInt(input.readLine());
			TreeMap<Integer, Integer> Q = new TreeMap<Integer, Integer>();
			
			for (int j=0; j<k; j++) {
				st = new StringTokenizer(input.readLine());
				String command = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if ("I".equals(command)) {
					Q.merge(num, 1, Integer::sum);
				} else {
					if (Q.size() != 0) {
						int tmp = num == 1 ? Q.lastKey() : Q.firstKey();
						
						if (Q.get(tmp) == 1) {
							Q.remove(tmp);
						} else {
							Q.merge(tmp, -1, Integer::sum);
						}
					}
				}
				
			}
			
			if (Q.size() == 0) {
				bw.write("EMPTY");
				bw.write('\n');
			} else {
				bw.write(String.valueOf(Q.lastKey()));
				bw.write(" ");
				bw.write(String.valueOf(Q.firstKey()));
				bw.write('\n');
			}
			bw.flush();
		}
	}
}