import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            char[] charArray = scanner.nextLine().toCharArray();
            for (int index = 0; index < charArray.length; index += 2) {
            	System.out.print(charArray[index]);
            }
            System.out.println();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
