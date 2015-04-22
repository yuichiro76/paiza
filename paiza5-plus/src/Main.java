import java.util.Scanner;

public class Main {
    // �Ղ̃T�C�Y��
    private static final int SIZE_X = 4;
    // �Ղ̃T�C�Y�c
    private static final int SIZE_Y = 4;
    // X��^����
    private static final int X = 0;
    // Y��^����
    private static final int Y = 1;

    // ��
    private int[][] table = new int[SIZE_X][SIZE_Y];
    // ��
    private int[][] piece = new int[SIZE_X * SIZE_Y - 1][2];

    // ���݋󂢂Ă���ꏊ
    private int[] current = {0, 0};

    // ����OK�ȃ|�W�V����
    private int success = 0;

    // OK�ɂȂ��Ă���菇��
    private int successCount = 0;

    // ���O�̎�
    private int previous = 0;

    public static void main(String[] args) throws Exception {
        Main board = new Main();

        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            // �Ղ̓Ǎ�
            // �u*�v��0�Ƃ���
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

                // �����Ƃ��납��30��肪�i�܂Ȃ����1��߂�
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
     * �N���A���������`�F�b�N����
     * @return �N���A�������ǂ���
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
     * �ړ�����
     * @return �ړ�������
     */
    int move() {
        int y = -1;
        // ���̃��W�b�N����
        // 1 2 3 4
        // 5 6 7 8
        // 9 10 15 11
        // 0 13 14 12
        // �Ƃ�
        // 1 2 3 11
        // 4 10 7 14
        // 0 13 9 6
        // 12 8 15 5
        // �Ŏ�l�܂�ɂȂ�
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
                // 30�����ă_������������߂Ė߂�
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
     * ��Ɉړ��o���邩�ǂ���
     * @return -1: �ړ��o���Ȃ� ���̑�: �ړ���̔ԍ�
     */
    int canMoveUp() {
        if (current[Y] == 0) {
            return -1;
        }
        return table[current[X]][current[Y] - 1];
    }

    /**
     * ���Ɉړ��o���邩�ǂ���
     * @return -1: �ړ��o���Ȃ� ���̑�: �ړ���̔ԍ�
     */
    int canMoveDown() {
        if (current[Y] == SIZE_Y - 1) {
            return -1;
        }
        return table[current[X]][current[Y] + 1];
    }

    /**
     * ���Ɉړ��o���邩�ǂ���
     * @return -1: �ړ��o���Ȃ� ���̑�: �ړ���̔ԍ�
     */
    int canMoveLeft() {
        if (current[X] == 0) {
            return -1;
        }
        return table[current[X] - 1][current[Y]];
    }

    /**
     * �E�Ɉړ��o���邩�ǂ���
     * @return -1: �ړ��o���Ȃ� ���̑�: �ړ���̔ԍ�
     */
    int canMoveRight() {
        if (current[X] == SIZE_X - 1) {
            return -1;
        }
        return table[current[X] + 1][current[Y]];
    }

    /**
     * ����ړ�����
     * @param number �ړ������̔ԍ�
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
     * �Ղ�ǂݍ���
     * @param scanner �X�L���i�[
     * @return ��
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
     * �Ղ��o�͂���
     * �f�o�b�O�p
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
