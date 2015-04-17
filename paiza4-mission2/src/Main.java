import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        int sum = 0;
        for (int i = 0; i < n; i++) {
        	sum += calc(br.readLine().trim().split(" "));
        }
        System.out.println(sum);
    }

    public static int calc(String[] string) {
        int t = Integer.parseInt(string[0]);
        int s = Integer.parseInt(string[1]);
        int p = Integer.parseInt(string[2]);
        return ((t > s ? t - s : 0) * p);
    }
}
