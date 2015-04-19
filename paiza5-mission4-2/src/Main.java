import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            // 1行目の読込
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int n = scanner.nextInt();

            // 表の読み込み
            int[][] table = new int[x][y];
            for (int index2 = 0; index2 < y; index2++) {
                for (int index = 0; index < x; index++) {
                    table[index][index2] = scanner.nextInt();
                }
            }

            // 計算する範囲を取得
            int sum = 0;
            for (int index = 0; index < n; index++) {
                int x1 = scanner.nextInt() - 1;
                int y1 = scanner.nextInt() - 1;
                int x2 = scanner.nextInt() - 1;
                int y2 = scanner.nextInt() - 1;

                for (int xIndex = x1; xIndex <= x2; xIndex++) {
                    for (int yIndex = y1; yIndex <= y2; yIndex++) {
                        // 座標の値を足す
                        sum += table[xIndex][yIndex];
                        // 1度足したら0にする
                        table[xIndex][yIndex] = 0;
                    }
                }
            }

            System.out.println(sum);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
