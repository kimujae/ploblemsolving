/*
10:41
테이블 위에 놓인 퍼즐 조각 게임 보드의 빈 공간
다음 규칙에 따라 테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈칸에 채우면 됩니다

조각은 한 번에 하나씩 채워
조각을 회전시킬 수 있
조각을 뒤집을 수는 없
게임 보드에 새로 채워 넣은 퍼즐 조각과 인접한 칸이 비어있으면 안

테이블의 조각을 파악 - 1
게임보드의 빈칸을 파악  - 0

테이블의 조각을 파악한다. 
테이블의 조각 att는 좌표, 총길이를 갖는다. 
테이블의 조각을 저장하는 배열리스트를 선언한다. 
테이블은 조각의 총 길이를 기준으로 정렬되는 comparable을 구현한다. 
(테이블의 조각은 최대 25개이다.)

회전 : x = y / y = -x 대입

게임보드의 빈칸을 파악한다.
게임보드의 빈칸은 크기를 갖는다. 
게임보드의 빈칸보다 작은 크기를 갖는 조각을 넣는다. 인접한 빈칸이 없거나, 더이상 넣을 조각이 없을때 까지 진행한다.

경우의 수를 순회하며 최대값을 찾는다. 경우의 수를 순회하는 방법? 

*/
import java.util.*;
class Solution {
    class Puzzle implements Comparable<Puzzle>{
        int len = 0;
        ArrayList<int[]> vector = new ArrayList<>();
        
        void setVector(int[] vec){
            vector.add(vec);
        }
        
        ArrayList<int[]> getVector(){
            return this.vector;
        }
        
        int getLen(){
            return this.len;
        }
        
        void setLen(int len){
            this.len = len;
        }
        
        void sortVector(){
            Collections.sort(vector, new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    // 상단 우선 좌 차선
                    if(o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                }
            });
        }
        
        void rotate(){
            for(int i = 0; i < this.vector.size(); i++){
                int x  = this.vector.get(i)[0];
                int y =  this.vector.get(i)[1];
                
                this.vector.get(i)[0] = -y;
                this.vector.get(i)[1] = x;
            }
            this.sortVector();
        }
        
        @Override
        public int compareTo(Puzzle o){
            return this.len - o.len;
        }
    }
    
    private int[] changeX = {-1, 1, 0, 0};//상 하
    private int[] changeY = {0, 0, -1, 1};//좌 우
    private boolean[][] pVisited, gVisited;
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        ArrayList<Puzzle> empty = new ArrayList<>();
        
        int len = game_board.length;
        pVisited = new boolean[len][len];
        gVisited = new boolean[len][len];
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(table[i][j] == 1 && !pVisited[i][j]) puzzles.add(bfs(i, j, len , table, pVisited, true)); // 퍼즐을 따로 저장해야된다. 
                if(game_board[i][j] == 0 && !gVisited[i][j]) empty.add(bfs(i, j, len , game_board, gVisited, false)); // 빈 공간을 따로 저장해야된다. 
            }
        }
        Collections.sort(puzzles); // 퍼즐 리스트 길이 기준 오름차순 정렬
        Collections.sort(empty);
        
        answer = solve(puzzles, empty);
        return answer;
    }
    
    
    int solve(ArrayList<Puzzle> tables, ArrayList<Puzzle> games){
       int ans = 0; // 채운 영역의 넓이 
        boolean[] visited = new boolean[tables.size()];
        for(Puzzle empty : games){
            // 빈공간 탐색 
            int len = empty.getVector().size();
            c:for(int i = 0; i < tables.size(); i++){
                Puzzle puzzle = tables.get(i);
                
                if(puzzle.getVector().size() > len)
                    break;
                
            
                if(puzzle.getVector().size() < len || visited[i])
                    continue;
                
                
                // 길이가 같은 경우에만 탐색
                ArrayList<int[]> emptyVec = empty.getVector();
                ArrayList<int[]> puzzleVec = puzzle.getVector();
                
                //좌표들의 차이가 모두 같다면 ok, 4번을 모두 검사했는데도 안된다면 not       
                int x = 0;
                a:while(x < 4){
                    x++;
                    int count = 0;
                    int dX = 0;
                    int dY = 0;
                    
                    
                    b:for(int j = 0 ; j < len; j++){
                        int[] e = emptyVec.get(j);
                        int[] p = puzzleVec.get(j);
                        
                        int nowX = e[0];
                        int nowY = e[1];
                        int pX = p[0];
                        int pY = p[1];
                        
                        int diffX = nowX - pX;
                        int diffY = nowY - pY;
                        if(j == 0){
                            dX = diffX;
                            dY = diffY;
                        }
                        
                        if(dX != diffX || dY != diffY) break b;
                        
                        
                        if(j == emptyVec.size()-1) {
                            ans += len;
                            visited[i] = true;
                            break c;
                        }
                    }// 빈공간 좌표 탐색 루프 종료
                    puzzle.rotate();
                }// 빈공간 - 퍼즐 검사 루프 종료
            }// 테이블의 퍼즐 객체 탐색 루프 종료
        }// 빈공간 퍼즐 객체 탐색 루프 종료
        return ans;
    }// 함수 종료
    
    
    
    
    Puzzle bfs(int x, int y, int len, int[][] board, boolean[][] visited, boolean isTable){
        Queue<int[]> bfsQ = new LinkedList<>();
        bfsQ.add(new int[]{x, y});
        Puzzle p = new Puzzle();
        visited[x][y] = true;
        int count = 0;
        
        
        while(!bfsQ.isEmpty()){
            int[] element = bfsQ.poll();
            count++;
            int nowX = element[0];
            int nowY = element[1];

            p.setVector(new int[]{nowX, nowY});
            
            for(int i = 0; i < 4; i++){
                int nextX = nowX + changeX[i];
                int nextY = nowY + changeY[i];
                
                if(nextX < 0 || nextX > len - 1 || nextY < 0 || nextY > len - 1)
                    continue;
                
                if(visited[nextX][nextY] || (isTable && board[nextX][nextY] == 0) || (!isTable && board[nextX][nextY] == 1))
                    continue;
                
                
                bfsQ.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }
        p.setLen(count);
        p.sortVector();

        return p;
    }
}