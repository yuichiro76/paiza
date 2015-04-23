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
    private int[] current = { 0, 0 };

    // ����OK�ȃ|�W�V����
    private int success = 0;

    // ���O�̎�
    private int previous = 0;

    public static void main(String[] args) throws Exception {
        Main board = new Main();
        board.run();
    }

    /**
     * �p�Y�����s
     */
    void run() {
//        print();
        int i = 0;
        for (; i < 100000; i++) {
            if (check()) {
                // System.out.println(i);
                break;
            }
            System.out.println(move());
//            move();
//            print();
        }
//        print();
//        System.out.println(i);
    }

    /**
     * �N���A���������`�F�b�N����
     * @return �N���A�������ǂ���
     */
    boolean check() {
        for (int index = success; index < SIZE_X * SIZE_Y - 1; index++) {
            int x = index % SIZE_X;
            int y = index / SIZE_Y;
            if (success == table[x][y] - 1) {
                // 3����������4�������ĂȂ���΃_��
                if (table[SIZE_X - 2][0] == SIZE_X - 1) {
                    if (table[SIZE_X - 1][0] != SIZE_X && !(table[SIZE_X - 1][0] == 0 && table[SIZE_X - 1][1] == SIZE_X)) {
                        break;
                    }
                }
                // 7����������8���͂����ĂȂ���΃_��
                if (table[SIZE_X - 2][1] == SIZE_X * 2 - 1) {
                    if (table[SIZE_X - 1][1] != SIZE_X * 2 && !(table[SIZE_X - 1][1] == 0 && table[SIZE_X - 1][2] == SIZE_X * 2)) {
                        break;
                    }
                }
                // 9����������13���͂����ĂȂ���΃_��
                if (table[0][SIZE_Y - 2] == SIZE_X * (SIZE_Y - 2) + 1) {
                    if (table[0][SIZE_Y - 1] != SIZE_X * (SIZE_Y - 1) + 1 && !(table[0][SIZE_Y - 1] == 0 && table[1][SIZE_Y - 1] == SIZE_X * (SIZE_Y - 1) + 1)) {
                        break;
                    }
                }
                // 10����������14���͂����ĂȂ���΃_��
                if (table[1][SIZE_Y - 2] == SIZE_X * (SIZE_Y - 2) + 2) {
                    if (table[1][SIZE_Y - 1] != SIZE_X * (SIZE_Y - 1) + 2 && !(table[1][SIZE_Y - 1] == 0 && table[2][SIZE_Y - 1] == SIZE_X * (SIZE_Y - 1) + 2)) {
                        break;
                    }
                }
                success++;
                continue;
            }
            break;
        }
        return success == SIZE_X * SIZE_Y - 1;
    }

    /**
     * �ړ�����
     * @return �ړ�������
     */
    int move() {
        int y = -1;
        int count = 0;
        while (y == -1 || y == previous || y <= success) {
            count++;
            int x = (int) (Math.random() * 4);
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
        if (number <= 0) {
            return;
        }
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
     * @return ��
     */
    Main() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            for (int yIndex = 0; yIndex < SIZE_Y; yIndex++) {
                String[] tmp = scanner.nextLine().split(" ");
                for (int xIndex = 0; xIndex < SIZE_X; xIndex++) {
                    if (tmp[xIndex].equals("*")) {
                        table[xIndex][yIndex] = 0;
                        current[X] = xIndex;
                        current[Y] = yIndex;
                    } else {
                        table[xIndex][yIndex] = Integer
                                .parseInt(tmp[xIndex]);
                        piece[table[xIndex][yIndex] - 1][X] = xIndex;
                        piece[table[xIndex][yIndex] - 1][Y] = yIndex;
                    }
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * �Ղ��o�͂��� �f�o�b�O�p
     */
    void print() {
        System.out.println();
        for (int yIndex = 0; yIndex < SIZE_Y; yIndex++) {
            for (int xIndex = 0; xIndex < SIZE_X - 1; xIndex++) {
                System.out.printf("%2d ", table[xIndex][yIndex]);
            }
            System.out.printf("%2d\n", table[SIZE_X - 1][yIndex]);
        }
    }
}
