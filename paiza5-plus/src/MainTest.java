import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;

import org.junit.Test;

public class MainTest {
    @Test
    public void test1() throws Exception {
        System.setIn(new FileInputStream("data/1.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] result = new String(out.toByteArray()).split(System
                .getProperty("line.separator"));
        assertEquals(2, result.length);
        assertEquals("11", result[0]);
        assertEquals("12", result[0]);
    }

    @Test
    public void test2() throws Exception {
        System.setIn(new FileInputStream("data/2.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] result = new String(out.toByteArray()).split(System
                .getProperty("line.separator"));
        assertEquals(15, result.length);
        assertEquals("4", result[0]);
        assertEquals("3", result[1]);
        assertEquals("2", result[2]);
        assertEquals("1", result[3]);
        assertEquals("5", result[4]);
        assertEquals("6", result[5]);
        assertEquals("7", result[6]);
        assertEquals("8", result[7]);
        assertEquals("12", result[8]);
        assertEquals("11", result[9]);
        assertEquals("10", result[10]);
        assertEquals("9", result[11]);
        assertEquals("13", result[12]);
        assertEquals("14", result[13]);
        assertEquals("15", result[14]);
    }
}
