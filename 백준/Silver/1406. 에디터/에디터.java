import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String alphabet = br.readLine();
        int n = Integer.parseInt(br.readLine());

        Stack<String> stack_d = new Stack<>();
        Stack<String> stack_l = new Stack<>();

        for(int i = 0; i < alphabet.length(); i ++){
            stack_d.push("" + alphabet.charAt(i));
        }

        for(int i =0 ; i < n; i ++){
            String inst = br.readLine();
            char command = inst.charAt(0);
            if(command =='P'){
                stack_d.push(""+inst.charAt(2));
            }
            else if(command =='L'){
                if(stack_d.size()>0) {
                    stack_l.push(stack_d.pop());
                }
            }
            else if(command == 'B'){
                if(stack_d.size() > 0 )stack_d.pop();
            }
            else if(command == 'D'){
                if(stack_l.size()>0) stack_d.push(stack_l.pop());
            }

        }


        while(!stack_d.isEmpty())
            stack_l.push(stack_d.pop());

        while(!stack_l.isEmpty())
            bw.write(stack_l.pop());


        bw.flush();
        bw.close();
    }
}
