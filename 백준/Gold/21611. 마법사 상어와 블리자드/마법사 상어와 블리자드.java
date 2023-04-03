import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, groupCount, ans1, ans2, ans3;
    static int[][] pointMap;// 칸번호가 쓰여져 있는 배열
    static int[][] marbleArr; // 구슬 번호가 쓰여져 있는 배열
    static Map<Integer, Integer> icedMarble = new HashMap<>(); // 칸 번호 - 구슬 번호가 저장 되어 있는 맵
    static Deque<int[]> marbleQ = new LinkedList<>(); // 구슬이 칸 번호 순서대로 저장되어있는 큐
    static Queue<Integer> countAndMarbleQ = new LinkedList<>(); // 구슬의 개수와 구슬의 번호가 순서대로 저장되어있는 큐
    static int[] changeX = {0, 1, 0, -1}; //공격 방향벡터
    static int[] changeY = {1, 0, -1, 0}; //공격 방향벡터
    static int[] vectorX = {-1, 1, 0, 0};
    static int[] vectorY = {0, 0, -1, 1};
    static boolean[][] visited;


    public static void main(String[] args)throws IOException {
        /*
        공격 시 dfs로 탐색 ->  원소 번호를 알고 있어야 할 듯.
        큐에 넣고 폭발 시키고 큐에 다시 집어넣는다.
        원소가 4개 이상 발견 되면 큐에 집어넣지 않는다.
        폭발하는 원소가 발견되지 않을 때 까지 반복

        이 후 큐에 있는 원소들을 칸 순서대로 marblemap에 삽입
        삽입이 끝난 후 칸 순서대로 dfs탐색
        구슬의 개수와 구슬 번호를 반환해야한다.
        새로운 큐에 삽입
         */

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pointMap = new int[N][N];
        visited = new boolean[N][N];
        marbleArr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                marbleArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }//구슬 배열 입력 완료
        setPointMap(0, 0, N*N-1, 0);// point - 구슬 배열 완성

        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());//방향
            int s = Integer.parseInt(st.nextToken());//거리

            icedMarble = new HashMap<>(); // 블리자드 공격 구슬 저장
            visited = new boolean[N][N];
            blizzard(((N+1)/ 2) -1, ((N+1)/ 2) -1, s, d); // 블리자드 공격
            bombMarble(); // 연속구슬 폭발
            updateMarbleArr(); // 폭발 이후 marbleArr 갱신
            groupMarble(); // 구슬 그룹핑
            updateMarbleArr(); // 그룹핑 이후 marbleArr 갱신
        }//공격 입력 완료

        System.out.println(ans1 + (2*ans2) + (3*ans3));
    }



    static void blizzard(int x, int y, int dist, int vec){
        if(dist == -1) return;

        icedMarble.put(pointMap[x][y], 0); // 파괴된 구슬 업데이트
        int nextX = x + vectorX[vec-1];
        int nextY = y + vectorY[vec-1];

        blizzard(nextX, nextY, dist-1, vec);
    }



    static void setPointMap(int x, int y, int pointNum, int vector){
        visited[x][y] = true;
        pointMap[x][y] = pointNum;
        if(marbleArr[x][y] != 0) marbleQ.addFirst(new int[]{pointNum, marbleArr[x][y]}); // 구슬을 순서 거꾸로 넣음

        for(int i = 0; i < 4; i++){
            int nextX = x + changeX[i];
            int nextY = y + changeY[i];

            if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > N-1)
                continue;

            if(vector == 3 && i != 3 && !visited[x+changeX[3]][y+changeY[3]])
                continue;

            if(visited[nextX][nextY])
                continue;


            setPointMap(nextX, nextY, pointNum-1 , i);
        }
    }

    static void updateMarbleArr(){
        int x =  ((N+1)/ 2) -1 , y =  ((N+1)/ 2) -1; // 탐색 시작 point
        int pointNum = 1; //pointNum 1번
        marbleArr = new int[N][N];
        int size = marbleQ.size() , count = 0;

        while(pointNum < N*N) {
            for (int i = 0; i < 4; i++) {
                int nextX = x + changeX[i];
                int nextY = y + changeY[i];

                if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                    continue;

                if (pointNum == pointMap[nextX][nextY]) {
                    x = nextX;
                    y = nextY;
                    break;
                }
            }

            if(size > count){ // 배열에 넣을 구슬이 아직 남아있을때
                int[] now = marbleQ.poll();
                marbleArr[x][y] = now[1]; // 구슬번호
                marbleQ.add(now);
                count++;
            }
            else return; // 배열에 넣을 구슬이 없을때
            pointNum++;
        }
    }

    static void bombMarble(){
        boolean bomb = true , first = true;
        while(bomb){ // 폭발이 일어나지 않을 때 까지 반복
            int pointNum = 1 , continuity = 0, prev = 0;
            int size = marbleQ.size() , count =0;
            bomb = false;
            while(size > count) {
                int[] now = marbleQ.poll(); // 현재 구슬
                int nowPoint = now[0];
                int nowMarble = now[1];
                count++;

                if(icedMarble.get(nowPoint) != null) { // 공격을 받아 파괴 된 구슬이면 pass
                    pointNum++;
                    continue;
                }
                marbleQ.add(now); // 일단 큐에 삽입

                if(nowMarble == prev) continuity++;
                else{ // 연속이 끝났으면, 폭발 조건을 따짐
                    if(continuity >=3){// 폭발 조건일 때
                        marbleQ.pollLast();
                        for(int i = 0; i <= continuity; i++){
                            marbleQ.pollLast(); // 폭발
                        }
                        marbleQ.add(now);
                        if(prev == 1) ans1 += continuity+1;
                        else if(prev ==2) ans2 += continuity+1;
                        else ans3 += continuity+1;
                        bomb = true;

                    }
                    continuity = 0;
                    pointNum++;
                }
                prev = nowMarble;
            }
            if(continuity >= 3) {
                for (int i = 0; i <= continuity; i++) {
                    marbleQ.pollLast(); // 폭발
                }
                if(prev == 1) ans1 += continuity+1;
                else if(prev ==2) ans2 += continuity+1;
                else ans3 += continuity+1;
                bomb = true;
            }
            first = false;
        }
    }


    static void group(int x, int y, int marbleNum, int pointNum){
        visited[x][y] = true;
        groupCount++;

        for(int i = 0; i < 4 ; i++){
            int nextX = x + changeX[i];
            int nextY = y + changeY[i];

            if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > N-1)
                continue;

            if (visited[nextX][nextY] || pointMap[nextX][nextY] != pointNum)
                continue;

            if(marbleNum == marbleArr[nextX][nextY]) {
                group(nextX, nextY, marbleNum , pointNum+1);
            }
        }
    }

    static void groupMarble(){
        //순서대로 각 칸을 순회하는 메소드
        int x =  ((N+1)/ 2) -1;
        int y =  ((N+1)/ 2) -2;
        int point = 0;
        int pointNum = 1;
        marbleQ = new LinkedList<>();
        visited = new boolean[N][N];
        while(pointNum < N*N) {
            for (int i = 0; i < 4; i++) {
                int nextX = x + changeX[i];
                int nextY = y + changeY[i];

                if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                    continue;

                if (pointNum == pointMap[nextX][nextY]) {
                    x = nextX;
                    y = nextY;
                    break;
                }
            }

            if(visited[x][y]){
                pointNum++;
                continue;
            }

            groupCount = 0;
            if(marbleArr[x][y] == 0) return;
            group(x, y, marbleArr[x][y], pointNum+1);
            marbleQ.add(new int[]{++point, groupCount});
            marbleQ.add(new int[]{++point, marbleArr[x][y]});
            if(point > (N*N) -2) return;
            pointNum++;
        }
    }

    static void setMarbleArr(){
        //순서대로 각 칸을 순회하는 메소드
        marbleQ = new LinkedList<>();
        int x =  ((N+1)/ 2) -1;
        int y =  ((N+1)/ 2) -1;
        int pointNum = 1;

        while(pointNum < N*N) {
            for (int i = 0; i < 4; i++) {
                int nextX = x + changeX[i];
                int nextY = y + changeY[i];

                if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1)
                    continue;

                if (pointNum == pointMap[nextX][nextY]) {
                    x = nextX;
                    y = nextY;
                    break;
                }
            }
            if(countAndMarbleQ.isEmpty()) marbleArr[x][y] = 0;
            else marbleArr[x][y] = countAndMarbleQ.poll();
            pointNum++;
        }
        countAndMarbleQ.clear();
    }
}
