import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            long[] sum = new long[7];
            for (int index = 0; index < n; index += 7) {
                for (int index2 = 0; index2 < 7; index2++) {
                    sum[index2] += scanner.nextInt();
                }
            }
            for (int index = 0; index < 7; index++) {
                System.out.println(sum[index]);
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
