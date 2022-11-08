import java.util.Scanner;
//
public class Main {
    static int[] parent;

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n, i, count;

        n = scan.nextInt();
        i = scan.nextInt();

        parent = new int[n + 1];
        for (int x = 1; x <= n; x++) {
            parent[x] = x;
        }

        count = 0;
        for (int  z= 0; z < i; z++) {
            int gate = scan.nextInt();
            int emptyGate = find( gate);

            if (emptyGate == 0) {
                break;
            }

            count++;
            union(emptyGate, emptyGate - 1);
        }

        System.out.println(count);
    }

    public static int find( int a){
        if(parent[a] == a ) return a;
        else return parent[a] = find( parent[a]);
    }

    public static void union( int a, int b){
        if(find( a) < find( b)) parent[b] = a;
        else parent[a] = b;
    }
}
