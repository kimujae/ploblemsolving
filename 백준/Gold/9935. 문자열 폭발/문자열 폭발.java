import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main{

    public static class character{
        char ch;
        int idx;

        public character(char ch, int idx){
            this.ch = ch;
            this.idx = idx;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static Deque<character> stack = new LinkedList<>();
    static Stack<Character> tmp = new Stack<>();
    static String inputStr, bombStr;

    public static void main(String[] args) throws IOException {
        inputStr = br.readLine();
        bombStr = br.readLine();

        bomb();
        if (stack.isEmpty()) sb.append("FRULA");
        else {
            while (!stack.isEmpty()) {
                sb.append(stack.pollLast().ch);
            }
        }
        System.out.print(sb);
    }


    static void bomb() {
        boolean bomb = true;
        int idx = 0;
        char compare = ' ';
        for (int j = 0; j < inputStr.length(); j++) {
            stack.push(new character(inputStr.charAt(j), idx));
            if(inputStr.charAt(j) == bombStr.charAt(idx)) idx++;
            else if(inputStr.charAt(j) == bombStr.charAt(0)) idx=1;
            else idx = 0;

            if(idx == bombStr.length()) {
                boom();
                if(!stack.isEmpty()) idx = stack.peek().idx;
                else idx = 0;
            }
            else stack.peek().idx = idx;
        }
    }

    static void boom(){
        for(int i = 0; i < bombStr.length(); i++){
            stack.pop();
        }
    }
}
