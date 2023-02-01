import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        PriorityQueue <int[]> gasStationQ = new  PriorityQueue<>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1]; // 연료의 양은 오름차순
                return o1[0] - o2[0]; // 거리는 오름차순
            }
        });

        PriorityQueue <int[]> fuelPriorityQ = new  PriorityQueue<>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o2[0] - o1[0]; // 거리는 내림차순
                return o2[1] - o1[1]; // 연료의 양 내림차순
            }
        });

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); // 입력받을 주유소정보 개수
        int[][] gasStation = new int[N][2]; //N개의 주유소 정보[위치 : 연료]
        int[] initInfo = new int[2]; //초기 사용자의 정보[잔여거리 : 잔여연료]
        int answer = 0; //최소 방문 수
        int destination = 0;
        int point = 0;

        for(int input = 0; input < N; input++){
            gasStation[input][0] = scan.nextInt();
            gasStation[input][1] = scan.nextInt();
            gasStationQ.add(gasStation[input]);
        }


        initInfo[0] = destination = scan.nextInt();
        initInfo[1] = scan.nextInt();


        while(destination -(point + initInfo[1]) > 0){
            while(gasStationQ.peek()!= null &&
                    point + initInfo[1] >= gasStationQ.peek()[0]) {
                // 현재 연료로 갈 수 있는 범위 내 주유소
                fuelPriorityQ.add(gasStationQ.poll());
            }
            if(fuelPriorityQ.isEmpty()) {
                answer = -1;
                break;
            }
                initInfo[1] = initInfo[1] - (fuelPriorityQ.peek()[0] - point)
                        + fuelPriorityQ.peek()[1]; // 주유소까지 가서 얻을 수 있는 연료의 양
                initInfo[0] = initInfo[0] - (fuelPriorityQ.peek()[0] - point);
                point = fuelPriorityQ.peek()[0];
                fuelPriorityQ.poll();
                answer++;

        }


        System.out.println(answer);
    }
}
