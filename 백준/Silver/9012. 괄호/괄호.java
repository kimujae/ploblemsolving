import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        /* 스택 활용 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tc; i++) {
            String str = br.readLine();

            //
            if(str.charAt(0) == ')' || str.charAt(str.length() - 1) == '(') {
                sb.append("NO");
                sb.append('\n');
                continue;
            }

            //stack에 이식
            Stack<Character> stack = new Stack<>();
            for(int j = 0; j < str.length(); j++) {
                stack.push(str.charAt(j));
            }

            // 검증
            int right = 0;
            boolean isValid = true;
            while(!stack.isEmpty()) {
                char c = stack.pop();

                if(c == ')') right++;
                else right--;

                if(right < 0) isValid = false;
            }
            if(right != 0) isValid = false;


            if(isValid) {
                sb.append("YES");
                sb.append('\n');
            } else {
                sb.append("NO");
                sb.append('\n');
            }
        }
        System.out.print(sb.toString());
    }
}
