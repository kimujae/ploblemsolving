import java.util.*;

class Main{
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        int[] coin = new int[N];
        int count = 0;
        
        for(int i = 0; i < N ; i ++){
            coin[N-i-1] = scan.nextInt();    
        }
        
        while(K != 0){
            for(int x = 0 ; x < N ; x ++){
                if(K >= coin[x]){
                    count += K/coin[x];
                    K = K%coin[x];
                }
            }
        }
        
        System.out.println(count);
        
        //종류의 개수와 , 총액을 입력받음. 
        //배열에 저장
        
        //배열의 끝에서부터 비교함
    }
    
    
}