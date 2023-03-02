import java.util.Arrays;

public class Main{
    static int[] minwin, maxwin, minres, maxres;
    static int N, num, min, max;


    public static void main(String[] args) throws Exception {
        N = read();
        minwin = new int[3];
        maxwin = new int[3];
        minres = new int[3];
        maxres = new int[3];

        for (int i = 0; i < 3; i++) {
            minwin[i] = maxwin[i] = maxres[i] = minres[i] = read();
        }


        for (int i = 0; i < N - 1; i++) {
            //n-1번 반복실행
            for (int j = 0; j < 3; j++) {
                num = read();
                if (j == 0) {
                    min = Math.min(minwin[0], minwin[1]);
                    max = Math.max(maxwin[0], maxwin[1]);
                    minres[j] = num + min;
                    maxres[j] = num + max;
                } else if (j == 2) {
                    min = Math.min(minwin[1], minwin[2]);
                    max = Math.max(maxwin[1], maxwin[2]);
                    minres[j] = num + min;
                    maxres[j] = num + max;
                } else {
                    min = Math.min(min, minwin[2]);
                    max = Math.max(max, maxwin[2]);
                    minres[j] = num + min;
                    maxres[j] = num + max;
                }
            }
            min = 0;
            max = 0;
            for (int x = 0; x < 3; x++) {
                minwin[x] = minres[x];
                maxwin[x] = maxres[x];
            }
        }

        Arrays.sort(minres);
        Arrays.sort(maxres);

        System.out.println(maxres[2] + " " + minres[0]);
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}