import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            // 1�s�ڂ̓Ǎ�
            String[] xy = scanner.nextLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);

            // x�̐������z����쐬����
            int[] count = new int[x];

            for (int index = 0; index < y; index++) {
                // �s��ǂݍ���
                String[] tmp = scanner.nextLine().split(" ");
                for (int index2 = 0; index2 < x; index2++) {
                    // ���l�ϊ�
                    int xx = Integer.parseInt(tmp[index2]);
                    if (xx == 1) {
                        // 1�̎�����������
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
