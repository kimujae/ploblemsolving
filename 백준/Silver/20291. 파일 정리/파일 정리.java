import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeSet;

public class Main {
    private static TreeSet<String> dict = new TreeSet<>();
    private static HashMap<String, Integer> fileCount = new HashMap<>();
    private static BufferedReader br =
            new BufferedReader(new InputStreamReader(
                    System.in
            ));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split("\\.");

            fileCount.putIfAbsent(tokens[1], 0);
            fileCount.put(tokens[1], fileCount.get(tokens[1]) + 1);
            dict.add(tokens[1]);
        }

        StringBuilder sb = new StringBuilder();
        for (String file : dict) {
            sb.append(file);
            sb.append(" ");
            sb.append(fileCount.get(file));
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
