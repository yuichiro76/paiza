import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.calc(System.in);
    }

    private int[] damage;
    private int position = 0;
    private int old = 0;
    private int current = 0;
    private int max = 0;

    public void calc(InputStream in) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int t = 0;
        int n = 0;
        try {
            String[] line = br.readLine().trim().split(" ");
            t = Integer.parseInt(line[0]);
            n = Integer.parseInt(line[1]);
        } catch (Exception e) {
            System.exit(-1);
        }
        if (t < 1 || t > n) {
            System.exit(-1);
        }
        if (n < t || n > 300000) {
            System.exit(-1);
        }

        damage = new int[t];

        for (int i = 0; i < n; i++) {
            String readLine = br.readLine().trim();
            calc(t, readLine);
        }
        System.out.println(max);
    }

    public void calc(int t, String string) {
        int x = 0;
        try {
            x = Integer.parseInt(string);
        } catch (Exception e) {
            System.exit(-1);
        }

        old = damage[position];
        damage[position++] = x;
        current = current - old + x;

        if (max < current) {
            max = current;
        }
        if (position >= t) {
            position = 0;
        }
    }
}
