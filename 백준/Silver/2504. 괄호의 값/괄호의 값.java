import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<Character> stack =  new Stack<>(); // 올바른 괄호열 판단
    static int open = 1, close =1, result = 0;
    public static void main(String[] args) throws IOException, ScriptException {
        String str = br.readLine(); // 괄호열
        boolean correct = true;
        //처음부터 닫는괄호라면 incorrect
        for(int i = 0; i < str.length(); i++){
            stack.add(str.charAt(i));

            if(str.charAt(i) == '(') open *= 2;
            else if(str.charAt(i) == '[') open *= 3;
            else if(str.charAt(i) == ')') {
                close *= 2;
                if(!isCorrect()) {
                    correct= false;
                    break;
                }
                calculate(str, i);
            }
            else{
                close *= 3;
                if(!isCorrect()) {
                    correct= false;
                    break;
                }
                calculate(str, i);
            }
        }

        if(correct && stack.isEmpty()) System.out.println(result);
        else System.out.println(0);
    }

    static boolean isCorrect(){
        boolean result = false;
        char c = stack.pop();
        if(stack.isEmpty()) return false;
        if(c == ')' && stack.peek() == '(') result = true;
        else if(c == ']' && stack.peek() == '[') result = true;

        stack.pop();
        return result;
    }

    static void calculate(String str, int i){
        if(stack.isEmpty() || (i < str.length()-1 && (str.charAt(i+1) == '(' || str.charAt(i+1) == '['))) {
            result += open;
            if(stack.isEmpty()) open = 1;
            else open /= close;
            close = 1;
        }
    }
}
