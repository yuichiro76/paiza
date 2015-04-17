import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            // 1行目の読込
            String[] xy = scanner.nextLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);

            // xの数だけ配列を作成する
            int[] count = new int[x];

            for (int index = 0; index < y; index++) {
                // 行を読み込む
                String[] tmp = scanner.nextLine().split(" ");
                for (int index2 = 0; index2 < x; index2++) {
                    // 数値変換
                    int xx = Integer.parseInt(tmp[index2]);
                    if (xx == 1) {
                        // 1の時だけ数える
                        count[index2]++;
                    }
                }
            }

            for (int index2 = 0; index2 < y; index2++) {
                for (int index = 0; index < x - 1; index++) {
                    System.out.print((count[index] > y - index2 - 1 ? "1" : "0") + " ");
                }
                System.out.println((count[x - 1] > y - index2 - 1 ? "1" : "0"));
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
