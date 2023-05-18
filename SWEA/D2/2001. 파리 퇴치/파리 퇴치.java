import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    private static int[][] map;
    private static int[] changeX = {1, 0};
    private static int[] changeY = {0 , 1};
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {

            /////////////////////////////////////////////////////////////////////////////////////////////
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                }
            }// 맵 입력 완료


            int max = 0;
            for(int i = 0; i <= N - M; i++){
                for(int j = 0; j <= N - M; j++){
                    max = Math.max(max, bfs(i, j , N, i + M, j + M));
                }
            }



            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(String.valueOf(test_case));
            sb.append(" ");
            sb.append(String.valueOf(max));
            System.out.println(sb.toString());

            /////////////////////////////////////////////////////////////////////////////////////////////

        }

    }
    static int bfs(int x, int y, int N, int targetX, int targetY) {
        Queue<int[]> q= new LinkedList();
        q.add(new int[]{x, y});

        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;
        int answer = map[x][y];

        while(!q.isEmpty()){
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];


            for(int i = 0; i < 2; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];

                if(nextX < 0 || nextY < 0 || nextX > N-1 || nextY > N-1 || nextX > targetX-1 || nextY > targetY-1)
                    continue;

                if(visited[nextX][nextY])
                    continue;

                q.add(new int[]{nextX, nextY});
                answer += map[nextX][nextY];
                visited[nextX][nextY] = true;
            }
        }
       return answer;
    }
}