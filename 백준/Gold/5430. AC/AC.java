import javax.swing.text.Style;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{

    public static Deque<Integer> deque = new LinkedList<Integer>();
    public static boolean toggle;

    public static void main(String[] args) throws IOException{
        /*
        함수 : R/D
        R : 뒤집기는 head와 tail을 서로 바꾸면 된다.
        기본값 : head : pollFirst(), tail : pollLast()
        R값 : 기본값 반대

        D :
        기본값 : pollFirst()
        R값 : pollLast()


         */


        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        for(int iter = 0; iter < testCase ; iter++) {
            //testcase개수만큼 실행
            String function = scan.next(); // 함수명령어
            int size = scan.nextInt();
            String array = scan.next();
            toggle = false;
            String num = "";

            for (int index = 0; index < array.length(); index++) {
                if (array.charAt(index) == '[') {
                    continue;
                }
                if (array.charAt(index) == ',') {
                    deque.add(Integer.parseInt(num));
                    num = "";
                    deque.add(-1);
                    continue;
                }
                if (array.charAt(index) == ']') {
                    if(!num.equals("")) deque.add(Integer.parseInt(num));
                    continue;
                }
                num = num + array.charAt(index) + "";
            }// 큐 완성
            //while(!deque.isEmpty()) System.out.print(deque.poll());
            //System.out.println();

            boolean result = false;
            for (int i = 0; i < function.length(); i++) {
                if (result = ExecFunction(function.charAt(i))) {
                    System.out.println("error");
                    break;
                }
            }// 함수 실행
            if (result == true) continue;

            //결과 출력
            printResult();
        }
    }



    public static boolean ExecFunction(char function){

        //boolean toggle = false;

        if(function == 'R'){
            toggle = !toggle;
        }
        else if(function == 'D'){
            if(deque.isEmpty()) return true;
            getArray(toggle);
        }
        return false;
    }


    public static void getArray(boolean toggle){

        if(toggle) {
            while(!deque.isEmpty() && deque.getLast() != -1) {
                deque.pollLast(); // 정수 삭제
            }
            deque.pollLast(); //공백 제거
        }
        else {
            while(!deque.isEmpty() && deque.peek() != -1) {
                deque.pollFirst(); // 정수 삭제
            }
            deque.pollFirst(); // -1 삭제
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void printResult() throws IOException {
            bw.append('[');

            while (!deque.isEmpty()) {
                if (!toggle) {
                    if (deque.peek() == -1) {
                        bw.append(',');
                        deque.poll();
                    } else {
                        bw.append(String.valueOf(deque.poll()));
                    }
                } else {
                    if (deque.getLast() == -1) {
                        bw.append(',');
                        deque.pollLast();
                    } else {
                        bw.append(String.valueOf(deque.pollLast()));
                    }
                }
            }
            bw.append(']');
            bw.append('\n');
            bw.flush();
    }
}
