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
		assertEquals("1700" + System.getProperty("line.separator"), new String(out.toByteArray()));
	}

	@Test
	public void test2() throws Exception {
		System.setIn(new FileInputStream("data/2.txt"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Main.main(null);
		assertEquals("30511" + System.getProperty("line.separator"), new String(out.toByteArray()));
	}
}
