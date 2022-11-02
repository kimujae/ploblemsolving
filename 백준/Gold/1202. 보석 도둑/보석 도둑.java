import java.util.*;

class Main{

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int n,k;
        long amount;
        //int[] tmp = new int[]{0,0};
        n = scan.nextInt();
        k = scan.nextInt();


        PriorityQueue<int[]> qubic = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                // 만일 2차원 배열의 첫 번째 원소가 같다면, 2번째 원소를 기준으로 내림차순 정렬한다.
                if(o1[0] == o2[0]) {
                    return Integer.compare(o2[1], o1[1]);
                }
                // 2차원 배열의 첫 번째 원소를 기준으로 오름차순 정렬한다.
                return Integer.compare(o1[0], o2[0]);
            }
        });

        PriorityQueue<int[]> qubic1 = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                // 만일 2차원 배열의 첫 번째 원소가 같다면, 2번째 원소를 기준으로 내림차순 정렬한다.
                if(o1[1] == o2[1]) {
                    return Integer.compare(o2[0], o1[0]);
                }
                // 2차원 배열의 첫 번째 원소를 기준으로 오름차순 정렬한다.
                return Integer.compare(o2[1], o1[1]);
            }
        });

        PriorityQueue<Integer> bag = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            qubic.add(new int[]{scan.nextInt(), scan.nextInt()});
        }


        for(int x = 0; x < k ; x++){
            bag.add(scan.nextInt());
        }
        amount =0;

        for(int y = 0; y < k ; y++){
            int tmp = 0;
            int bagk = bag.poll();
            while(qubic.size() >=1 && bagk >=qubic.peek()[0] && qubic.peek()[0]>= tmp){
                qubic1.add(qubic.poll());
            }
            tmp = bagk;
            if(qubic1.size()>=1){
                amount += qubic1.poll()[1];
            }
        }

        System.out.println(amount);


    }
}