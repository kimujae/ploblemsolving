import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main{

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        StringBuilder anssb = new StringBuilder();
        int n = scan.nextInt();
        int max, input;
        Stack<Integer> stack = new Stack<>();

        max = 0;
        
        for(int i = 0; i < n ; i++) {
            input = scan.nextInt();
            if (max < input) {
                for (int z = max + 1; z <= input; z++) {
                    stack.push(z);
                    anssb.append('+').append('\n');
                }
                max = input;
            }
            if (stack.peek() == input) {
                anssb.append('-').append('\n');
                stack.pop();
            } else {
                anssb.setLength(0);
                anssb.append("NO");
                break;
            }
        }
        System.out.println(anssb);
    }
}
