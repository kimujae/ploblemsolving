import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        String[] words = new String[N];
        int[] alphabet = new int[26];

        for(int i = 0; i < N; i ++){
            words[i] = scan.next();

        }

        for(int x = 0; x < N; x++){
            for(int y = 0; y < words[x].length(); y++){
                alphabet[words[x].charAt(y) - 65] += (int)Math.pow(10, words[x].length() - y -1);
                }
            }

        Arrays.sort(alphabet);

        int sum = 0;
        for(int z =1; z < 11; z++){
            sum += alphabet[26-z] * (10-z);
        }
        System.out.println(sum);

        }
    }


