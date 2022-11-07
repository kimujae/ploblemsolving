import java.util.LinkedList;
import java.util.Scanner;

class Main{
    public static int[] stack;
    public static int top = -1;
    public static int size = 0;

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = in.nextInt();

        stack = new int[N];

        for(int i = 0; i < N; i++) {

            String str = in.next();

            switch (str) {
                   
                case "push":
                    push(in.nextInt());
                    break;

                case "pop":
                    sb.append(pop()).append('\n');
                    break;

                case "size":
                    sb.append(size()).append('\n');
                    break;

                case "empty":
                    sb.append(empty()).append('\n');
                    break;

                case "top":
                    sb.append(top()).append('\n');
                    break;
            }

        }
        System.out.println(sb);
    }
    public static void push(int x){
        size++;
        top++;
        stack[top] = x;
    }

    public static int pop(){
        if(size == 0) return -1;
        else {
            int tmp = stack[top];
            stack[top] = 0;
            top--;
            size--;
            return tmp;
        }
    }

    public static int size(){
        return size;
    }

    public static int empty(){
        if(size == 0) return 1;
        else return 0;
    }

    public static int top(){
        if(size == 0){
            return -1;
        }
        else return stack[top];
    }
}