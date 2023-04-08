import java.util.*;
import java.io.*;
class Solution {
    class Cell{
        int r;
        int c;
        String word;
        Set<Cell> mergeList = new HashSet<>();

        Cell(int r, int c){
            this.r = r;
            this.c = c;
            this.word = "EMPTY";
            this.mergeList.add(this);
        }

        String getWord(){
            return this.word;
        }

        void setWord(String word){
            this.word = word;
        }

        void setMerge(Set<Cell> list){
            this.mergeList = list;
        }

        Set<Cell> getMerge(){
            return mergeList;
        }
    }
    public ArrayList<ArrayList<Cell>> table = new ArrayList<>();

    public String[] solution(String[] commands) {
        ArrayList<String> answer = new ArrayList<>();

        
        for(int r = 0; r < 51 ; r++){
            table.add(new ArrayList<>());
            for(int c = 0; c < 51 ; c++){
                Cell cell = new Cell(r, c);
                table.get(r).add(cell);
            }// 테이블에 cell 리스트 할당
        }

        StringTokenizer st;
        for(int i = 0; i < commands.length; i++){
            st = new StringTokenizer(commands[i]);
            String command = st.nextToken();
            
            
            if(command.equals("UPDATE")){
                if(st.countTokens() == 2){
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    
                    update(value1, value2);
                }
                else {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String word = st.nextToken();
                    
                    update(r, c, word);
                }
            }
            else if(command.equals("MERGE")){
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                
                merge(r1, c1, r2, c2);
            }
            else if(command.equals("UNMERGE")){
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                unmerge(r, c);
            }
            else{
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                Cell cell = table.get(r).get(c);
                answer.add(cell.getWord());
            }
        }



        return answer.toArray(new String[0]);
    }


    void update(int r, int c, String word){
        //빈 셀에 값을 입력 
        //또는 셀의 값 변경
    
        Cell cell =  table.get(r).get(c);
        Set<Cell> mergeList = cell.getMerge();

        for(Cell ce : mergeList){
            ce.setWord(word);
        }
    }

    void update(String value1, String value2){
        //word1을 가진 셀을 word2의 값으로 변경
        
        for(int i = 0; i < 51; i++){
            for(int j = 0; j < 51; j++){
                Cell cell = table.get(i).get(j);

                if(cell.getWord().equals(value1)) cell.setWord(value2);

            }
        }
    }

    void merge(int r1, int c1, int r2, int c2){
        //r1, c1 셀과 r2, c2 셀을 병합
        Cell cell1 = table.get(r1).get(c1);
        Cell cell2 = table.get(r2).get(c2);
        Set<Cell> list1  = cell1.getMerge();
        Set<Cell> list2  = cell2.getMerge();
        if(list1 == list2) return;
        

        if(!cell1.getWord().equals("EMPTY")){
            for(Cell cell : list2){
                list1.add(cell);
            }
            
            for(Cell cell : list1){
                cell.setMerge(list1);
            }
            
            for(Cell cell : list2){
                cell.setMerge(list1);
                cell.setWord(cell1.getWord());
            }
            
            return;
        }
        else if(!cell2.getWord().equals("EMPTY")){
            for(Cell cell : list1){
                list2.add(cell);
            }
            
            for(Cell cell : list1){
                cell.setMerge(list2);
                cell.setWord(cell2.getWord());
            }
            
            for(Cell cell : list2){
                cell.setMerge(list2);
            }
            return;
        }
        else{
            for(Cell cell : list2){
                list1.add(cell);
            }
            
            for(Cell cell : list1){
                cell.setMerge(list1);
            }
            
            for(Cell cell : list2){
                cell.setMerge(list1);
                cell.setWord(cell1.getWord());
            }
        }
    }

    void unmerge(int r, int c){
        // 셀 병합 해제 후 값은 r,c가 가짐 
        //&& 나머지 해제 된 셀은 빈값을 가짐
        
        Cell cell = table.get(r).get(c);
        Set<Cell> mergeList = cell.getMerge();
        String word = cell.getWord();

        for(Cell cells : mergeList){
            Set<Cell> newList = new HashSet<>();
            newList.add(cells);
            cells.setMerge(newList);
            cells.setWord("EMPTY");
        }

        cell.setWord(word);
    }
}