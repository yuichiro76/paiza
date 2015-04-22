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

            for (int i = 0; i < 100000; i++) {
                if (board.check()) {
                    break;
                }
                System.out.println(board.move());
            }
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
        for (int index = success; index < SIZE_X * SIZE_Y - 1; index++) {
            int x = index % SIZE_X;
            int y = index / SIZE_Y;
            if (success == table[x][y] - 1) {
                // 3が入った時4が入ってなければダメ
                if (table[2][0] == 3 && table[3][0] != 4) {
                    break;
                }
                // 7が入った時8がはいってなければダメ
                if (table[2][1] == 7 && table[3][1] != 8) {
                    break;
                }
                // 9が入った時13がはいってなければダメ
                if (table[0][2] == 9 && table[0][3] != 13) {
                    break;
                }
                // 10が入った時14がはいってなければダメ
                if (table[1][2] == 10 && table[1][3] != 14) {
                    break;
                }
                success++;
                continue;
            }
            break;
        }
        return success == SIZE_X * SIZE_Y - 1;
    }

    /**
     * 移動する
     * @return 移動した駒
     */
    int move() {
        int y = -1;
        int count = 0;
        while (y == -1 || y == previous || y <= success) {
            count++;
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
}
