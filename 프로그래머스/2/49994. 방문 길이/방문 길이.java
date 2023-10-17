import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
class Solution {
    private Map<Coord, Boolean> isWalked;
    public int solution(String dirs) {
        isWalked = new HashMap<>();
        int answer = solve(dirs);
        return answer;
    }
    
    public class Coord{
        int x;
        int y;
        int nx;
        int ny;
        
        public Coord(int x, int y, int nx, int ny) {
            this.x =x;
            this.y = y;
            this.nx = nx;
            this.ny = ny;
        }
        
        @Override
        public boolean equals(Object o) {
            if(((Coord)o).x != this.x) return false;
            if(((Coord)o).y != this.y) return false;
            if(((Coord)o).nx != this.nx) return false;
            if(((Coord)o).ny != this.ny) return false;
            
            return  true;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y, nx, ny);
        }
    }
    
    
    
    public int solve(String dirs) {
        int ans = 0;
        
        int[] cx = {-1, 1, 0, 0};
        int[] cy = {0, 0 , 1, -1};
        
        int x = 0;
        int y = 0;
        int nx = 0;
        int ny = 0;
        
        for(int i = 0; i < dirs.length(); i++) {
            char inst = dirs.charAt(i);
            int vec = -1;
            
            if(inst == 'U') vec = 0;
            else if(inst == 'D') vec = 1;
            else if(inst == 'R') vec = 2;
            else vec = 3;
            
            nx = x + cx[vec];
            ny = y + cy[vec];
            
            if(nx > 5 || ny > 5 || nx < -5 || ny < -5) continue;
            
            if(isWalked.get(new Coord(x, y, nx, ny)) == null ) {
                ans ++;
                isWalked.put(new Coord(x, y, nx, ny), true);
                isWalked.put(new Coord(nx, ny, x, y), true);
            }
            
            x = nx;
            y = ny;
        }
        return ans;
    }
}