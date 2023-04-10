/*
장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시
노래는 고유 번호로 구분
노래를 수록하는 기준

1. 플레이시간의 합이 가장 높은 장르 별 나열
2. 장르 내 노래 나열
3. 같으면 고유번호 오름차순 정렬

장르별 재생횟수 총합
장르 내 고유번호와 재생횟수 -> 우선순위 큐

hashset은 정렬이 된다고 들었다. 
*/
import java.util.*;
class Solution {
    class Music implements Comparable<Music>{
        int num;
        int plays;
        Music(int num, int plays){
            this.num = num;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Music o){
            if(this.plays == o.plays){
                if(this.num > o.num) return 1;
                else return -1;
            }
            if(this.plays > o.plays) return -1;
            else return 1;
        }
    }
    
    class Genre implements Comparable<Genre>{
        String g;
        int plays;
        
        Genre(String g, int plays){
            this.g = g;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Genre o){
            if(this.plays > o.plays) return -1;
            else return 1;
        }
    }
    
    
    
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> ans;
        ans = solve(genres, plays);
        int[] answer = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    
    ArrayList<Integer> solve(String[] genres, int[] plays){
        ArrayList<Integer> ans = new ArrayList<>();
        Map<String, Integer> genreMap = new HashMap<>();
        Map<String, PriorityQueue<Music>> musicMap = new HashMap<>();
        PriorityQueue<Genre> genreQ = new PriorityQueue<>();
        
        for(int i = 0; i < genres.length; i++){
            String genre = genres[i];
            int play = plays[i];
            
            genreMap.put(genre, genreMap.getOrDefault(genre, 0)+ play);
            if(musicMap.get(genre) != null) {
                PriorityQueue<Music> q = musicMap.get(genre);
                q.add(new Music(i, play));
                musicMap.put(genre, q);
            }
            else {
                PriorityQueue<Music> q = new PriorityQueue<>();
                q.add(new Music(i, play));
                musicMap.put(genre, q);
            }
            
        }// 해시 입력 완료
        
        // genreMap을 value기준으로 sort
        for(String k : genreMap.keySet()){
            genreQ.add(new Genre(k, genreMap.get(k)));
        }
        
        
        // sort된 key값을 하나씩 순회하면서 musicMap에서 get
        while(!genreQ.isEmpty()){
            Genre g = genreQ.poll();
            PriorityQueue<Music> pq = musicMap.get(g.g);
            
            int count = 0;
            while(!pq.isEmpty()){
                ans.add(pq.poll().num);
                count++;
                
                if(count == 2) break;
            }
        }
        return ans;
    }
}