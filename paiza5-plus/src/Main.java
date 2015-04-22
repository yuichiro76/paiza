import java.util.Scanner;

public class Main {
    // 盤のサイズ横
    private static final int SIZE_X = 4;
    // 盤のサイズ縦
    private static final int SIZE_Y = 4;
    // Xを与える
    private static final int X = 0;
    // Yを与える
    private static final int Y = 1;

    // 盤
    private int[][] table = new int[SIZE_X][SIZE_Y];
    // 駒
    private int[][] piece = new int[SIZE_X * SIZE_Y - 1][2];

    // 現在空いている場所
    private int[] current = {0, 0};

    // 現在OKなポジション
    private int success = 0;

    // OKになってから手順数
    private int successCount = 0;

    // 直前の手
    private int previous = 0;

    public static void main(String[] args) throws Exception {
        Main board = new Main();

        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            // 盤の読込
            // 「*」は0とする
            board.read(scanner);

            board.print();

//            int[] hand = new int[10000000];
            for (long i = 0; i < 100000; i++) {
//                board.print();
                if (board.check()) {
                    break;
                }
//                hand[i] = board.move();
                board.move();
//                board.print();
//                System.out.println("H: " + hand[i]);
                if (i % 1000 == 0) {
//                    board.print();
                }

                // 同じところから30回手が進まなければ1手戻す
                if (board.successCount++ > 30 && board.success != 0) {
                    board.success--;
                    board.successCount = 0;
                }
            }
            board.print();

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * クリアしたかをチェックする
     * @return クリアしたかどうか
     */
    boolean check() {
//        System.out.println("S1: " + success);
        for (int index = success; index < SIZE_X * SIZE_Y - 1; index++) {
            int x = index % SIZE_X;
            int y = index / SIZE_Y;
            if (success == table[x][y] - 1) {
                success++;
                successCount = 0;
                continue;
            }
            break;
        }
//        for (int index2 = y; index2 < SIZE_Y; index2++) {
//            for (int index = x; index < SIZE_X; index++) {
//                System.out.println(table[index][index2] + " " + (index + 1 + 4 * index2));
//                if (table[index][index2] == (index + 1 + 4 * index2)) {
//                    continue;
//                }
//                success = index + 1 + 4 * index2;
//                break;
//            }
//        }
//        System.out.println("S2: " + success);
        return success == SIZE_X * SIZE_Y - 1;
//        boolean result = true;
//        for (int y = 0; y < SIZE_Y; y++) {
//            for (int x = 0; x < SIZE_X; x++) {
//                if (x == SIZE_X - 1 && y == SIZE_Y - 1) {
//                    result = result && table[x][y] == 0;
//                    continue;
//                }
//                result = result && table[x][y] == x + 1 + 4 * y;
//            }
//        }
//        return result;
    }

    /**
     * 移動する
     * @return 移動した駒
     */
    int move() {
        int y = -1;
        // このロジックだと
        // 1 2 3 4
        // 5 6 7 8
        // 9 10 15 11
        // 0 13 14 12
        // とか
        // 1 2 3 11
        // 4 10 7 14
        // 0 13 9 6
        // 12 8 15 5
        // で手詰まりになる
        int count = 0;
        while (y == -1 || y == previous || y <= success) {
            count++;
//            System.out.println(success + " " + previous + " " + y);
//            print();
            int x = (int)(Math.random() * 10);
            switch (x) {
            case 0:
                y = canMoveUp();
                break;
            case 1:
                y = canMoveDown();
                break;
            case 2:
                y = canMoveLeft();
                break;
            default:
                y = canMoveRight();
                break;
            }
            if (count > 30 && y != -1) {
                // 30回やってダメだったら諦めて戻る
                y = previous;
                break;
            }
        }
        move(y);
//        print();
        previous = y;
        return y;
    }

    /**
     * 上に移動出来るかどうか
     * @return -1: 移動出来ない その他: 移動先の番号
     */
    int canMoveUp() {
        if (current[Y] == 0) {
            return -1;
        }
        return table[current[X]][current[Y] - 1];
    }

    /**
     * 下に移動出来るかどうか
     * @return -1: 移動出来ない その他: 移動先の番号
     */
    int canMoveDown() {
        if (current[Y] == SIZE_Y - 1) {
            return -1;
        }
        return table[current[X]][current[Y] + 1];
    }

    /**
     * 左に移動出来るかどうか
     * @return -1: 移動出来ない その他: 移動先の番号
     */
    int canMoveLeft() {
        if (current[X] == 0) {
            return -1;
        }
        return table[current[X] - 1][current[Y]];
    }

    /**
     * 右に移動出来るかどうか
     * @return -1: 移動出来ない その他: 移動先の番号
     */
    int canMoveRight() {
        if (current[X] == SIZE_X - 1) {
            return -1;
        }
        return table[current[X] + 1][current[Y]];
    }

    /**
     * 駒を移動する
     * @param number 移動する駒の番号
     */
    void move(int number) {
        int tmpX = piece[number - 1][X];
        int tmpY = piece[number - 1][Y];
        piece[number - 1][X] = current[X];
        piece[number - 1][Y] = current[Y];
        current[X] = tmpX;
        current[Y] = tmpY;

        table[current[X]][current[Y]] = 0;
        table[piece[number - 1][X]][piece[number - 1][Y]] = number;
    }

    /**
     * 盤を読み込む
     * @param scanner スキャナー
     * @return 盤
     */
    void read(Scanner scanner) {
        for (int yIndex = 0; yIndex < SIZE_Y; yIndex++) {
            String[] tmp = scanner.nextLine().split(" ");
            for (int xIndex = 0; xIndex < SIZE_X; xIndex++) {
                if (tmp[xIndex].equals("*")) {
                    table[xIndex][yIndex] = 0;
                    current[X] = xIndex;
                    current[Y] = yIndex;
                } else {
                    table[xIndex][yIndex] = Integer.parseInt(tmp[xIndex]);
                    piece[table[xIndex][yIndex] - 1][X] = xIndex;
                    piece[table[xIndex][yIndex] - 1][Y] = yIndex;
                }
            }
        }
    }

    /**
     * 盤を出力する
     * デバッグ用
     */
    void print() {
        System.out.println();
        for (int yIndex = 0; yIndex < SIZE_Y; yIndex++) {
            for (int xIndex = 0; xIndex < SIZE_X - 1; xIndex++) {
                System.out.printf("%2d ", table[xIndex][yIndex]);
            }
            System.out.printf("%2d\n", table[SIZE_X - 1][yIndex]);
        }
//        System.out.println(current[X] + " " + current[Y]);
//        for (int index = 0; index < SIZE_X * SIZE_Y - 1; index++) {
//            System.out.println(index + 1 + ": " + piece[index][X] + " " + piece[index][Y]);
//        }
    }
}
