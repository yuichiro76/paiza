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
        assertEquals(4, result.length);
        assertEquals("0 0 0 0", result[0]);
        assertEquals("0 0 0 0", result[1]);
        assertEquals("1 0 1 0", result[2]);
        assertEquals("1 1 1 1", result[3]);
    }

    @Test
    public void test2() throws Exception {
        System.setIn(new FileInputStream("data/2.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] result = new String(out.toByteArray()).split(System
                .getProperty("line.separator"));
        assertEquals(4, result.length);
        assertEquals("1 0 0", result[0]);
        assertEquals("1 0 0", result[1]);
        assertEquals("1 0 0", result[2]);
        assertEquals("1 0 1", result[3]);
    }

    @Test
    public void test3() throws Exception {
        System.setIn(new FileInputStream("data/3.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] result = new String(out.toByteArray()).split(System
                .getProperty("line.separator"));
        assertEquals(5, result.length);
        assertEquals("0 0 0", result[0]);
        assertEquals("0 0 0", result[1]);
        assertEquals("0 1 0", result[2]);
        assertEquals("1 1 1", result[3]);
        assertEquals("1 1 1", result[4]);
    }
}
