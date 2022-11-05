import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n, k, pre;
        String num;

        n = scan.nextInt();
        k = scan.nextInt();
        num = scan.next();
        int compare;
        Stack<Integer> stack = new Stack<>();

        stack.push(Character.getNumericValue(num.charAt(0))) ;
        for(int i = 1; i < n; i++ ){
            compare = Character.getNumericValue(num.charAt(i));
            if(stack.peek() < compare && stack.size()+ n - i  > n - k){
                while(stack.size() > 0 && stack.peek() < compare && stack.size()+n-i  > n-k){
                    stack.pop();
                }
                stack.push(compare);
            }
            else
                if(stack.size()< n-k)stack.push(compare);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : stack) {
            sb.append(integer);
        }
        System.out.println(sb.toString());
    }
}