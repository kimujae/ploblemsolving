import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    public static Queue<String> queue = new LinkedList<String>();
    public static String compressure;

    public static int solution(String s) {
        int answer = 0;
        /*
        1. 문자열자르기를 1부터 문자열의길이/2까지 반복수행
        2. 각 반복 수행동안 할 것은
        2-1. 자른 문자열(compare)을 기억하고 있다가 바로 다음(part)에 자른 문자열이 나오는지 확인
        2-2. 나온다면, count++
        2-3. 나오지 않는다면, count == 1 이면 이전 자른 str += 문자열 count > 1이면 count+문자열
             새로 자른 문자열을 변수에 저장하여 수행
        min > str.length 이면 min에 최소값 저장
        3. 예외: 자른 부분 문자열의 길이보다 남은 문자열의 길이가 짧은 경우.
            예외 : 비교하려는 문자열의 길이가 비교 문자열보다 길이가 짧은 경우.
         */


        String part;
        String compare;
        int count = 0;
        int curIndex = 0;
        answer = Integer.MAX_VALUE;
        if(s.length()==1) return 1;

        for(int cutLen = 1; cutLen <= s.length()/2 ;cutLen++) {
            part ="";
            compare ="";
            compressure = "";
            count = 0;
            // 문자열 자르기 반복 수행
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '"');
                else queue.add(s.charAt(i)+"");
            }

            for (int iter = 0; iter < cutLen; iter++) {
                compare += queue.poll();
            }

            for (int iter = 0; iter < cutLen; iter++) {
                part += queue.poll();
            }

            while(true) {
                count = 0;
                while (compare.equals(part)) {
                    // 같은것이 나오지 않을 때 까지 반복 비교
                    count++;
                    if(queue.isEmpty()) break;
                    if (queue.size() < cutLen) break;
                    part = "";
                    for (int iter = 0; iter < cutLen; iter++) {
                        //문자열 자르기 수행
                        part += queue.poll();
                    }
                }
                if(count == 0){
                    // 반복되는 것이 하나도 없을때
                    compressure += compare;
                    compare = part;
                    part ="";
                    if(queue.isEmpty()){
                        compressure += compare;
                        break;
                    }
                    else if (queue.size() < cutLen){
                        compressure += compare;
                        while (!queue.isEmpty()){
                            compressure += queue.poll();
                        }
                        break;
                    }
                    else {
                        for (int iter = 0; iter < cutLen; iter++) {
                            part += queue.poll();
                        }
                    }
                }
                else{
                    // 반복되는 것이 하나라도 있을 때
                    compressure += (count+1) + compare;
                    //
                    if(queue.isEmpty()) break;
                    else if (queue.size() < cutLen) {
                        if(!compare.equals(part)) compressure += part;
                        while (!queue.isEmpty()){
                            compressure += queue.poll();
                        }
                        break;
                    }
                    else {
                        compare = part;
                        part="";
                        for (int iter = 0; iter < cutLen; iter++) {
                            part += queue.poll();
                        }
                    }
                }
            }
            //System.out.println(compressure);
            if(compressure.length() < answer) answer = compressure.length();
        }
        return answer;
    }
}
