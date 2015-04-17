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
		String[] result = new String(out.toByteArray()).split(System.getProperty("line.separator"));
		assertEquals(7, result.length);
		assertEquals("60000", result[0]);
		assertEquals("40000", result[1]);
		assertEquals("0", result[2]);
		assertEquals("70000", result[3]);
		assertEquals("15000", result[4]);
		assertEquals("700000", result[5]);
		assertEquals("950000", result[6]);
	}

	@Test
	public void test2() throws Exception {
		System.setIn(new FileInputStream("data/2.txt"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Main.main(null);
		String[] result = new String(out.toByteArray()).split(System.getProperty("line.separator"));
		assertEquals(7, result.length);
		assertEquals("1003", result[0]);
		assertEquals("2302", result[1]);
		assertEquals("421", result[2]);
		assertEquals("32124", result[3]);
		assertEquals("3", result[4]);
		assertEquals("0", result[5]);
		assertEquals("3214", result[6]);
	}
}
