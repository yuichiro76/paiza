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
        assertEquals(1, result.length);
        assertEquals("6664", result[0]);
    }

    @Test
    public void test2() throws Exception {
        System.setIn(new FileInputStream("data/2.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(null);
        String[] result = new String(out.toByteArray()).split(System
                .getProperty("line.separator"));
        assertEquals(1, result.length);
        assertEquals("17895", result[0]);
    }
}
