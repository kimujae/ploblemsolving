import java.util.*;

class Main{

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int sum= 0, tmp = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        for(int i = 0 ; i< N ; i ++){
            priorityQueue.add(input.nextInt());
        }
        
        while(priorityQueue.size() > 1){
            tmp = priorityQueue.poll() + priorityQueue.poll();
            sum += tmp;
            priorityQueue.add(tmp);
        }

        System.out.println(sum);

        //누적합을 구하는 것과 마찬가지
        //일단 작은 것을 선택하는게 낫다.
  


    }
}