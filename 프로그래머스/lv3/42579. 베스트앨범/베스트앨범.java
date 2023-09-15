import java.util.*;
class Solution {
    class Genre{
        int play;
        String genre;
        ArrayList<int[]> list = new ArrayList<>();
        
        public Genre(String genre, int play, ArrayList<int[]> list) {
            this.play = play;
            this.list = list;
            this.genre = genre;
        }
        
        public ArrayList<int[]> getList(){
            return this.list;
        }
        
        public String getGenre() {
            return this.genre;
        }
        
        public void setList(int[] music){
            list.add(music);
        }
        
        public void sumPlay(int p){
            this.play += p;
        }
        
        public int getPlay() {
            return this.play;
        }
        
    }
    Map<String, Genre> map = new HashMap<>();
    public List<Integer> solution(String[] genres, int[] plays) {
        List<Integer> ans = new ArrayList<>();
        for(int music = 0; music < genres.length; music++) {
            String genre = genres[music];
            int play = plays[music];
            
            map.putIfAbsent(genre, new Genre(genre , 0, new ArrayList<>()));
            
            Genre g = map.get(genre);
            g.setList(new int[]{music, play});
            g.sumPlay(play);
        }
        
        ArrayList<Genre> list = new ArrayList<>(); 
        
        for(Genre g : map.values()) {
            list.add(g);
        }
        
        Collections.sort(list, new Comparator<Genre>(){
          @Override
            public int compare(Genre o1, Genre o2) {
                return o2.play - o1.play;
            }
        });
        
        for(Genre g : list) {
            Genre newG = map.get(g.getGenre());
            
            ArrayList<int[]> l = newG.getList();
            Collections.sort(l, new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1] == o2[1]) return o1[0] - o2[0];
                    return o2[1] - o1[1];
                }
            });
            
            for(int i = 0; i < l.size(); i++) {
                if(i == 2) break;
                ans.add(l.get(i)[0]);
            }
        }
        return ans;
    }
}