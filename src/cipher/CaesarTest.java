package cipher;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CaesarTest {
	Caesar myCaesar;
	@Before
	public void setUp(){
		myCaesar = new Caesar();  
	}
	
	@Test
	public void testEncipher() {
		assertEquals(myCaesar.encipher(1, "zZ"), "aA");
		assertEquals(myCaesar.encipher(26, "aA"), "aA");
	    assertEquals(myCaesar.encipher(27, "a A%"), "b B%");
		assertEquals(myCaesar.encipher(-1, "a cZzA%"), myCaesar.encipher(25, "a cZzA%"));
		assertEquals(myCaesar.encipher(0, "Shuting Sun"), "Shuting Sun");
	}
	
	@Test
	public void testDecipher() {
		myCaesar.readDictionary(); // We need to read the dictionary first.
		assertTrue(myCaesar.dic.contains("great"));
		assertEquals(myCaesar.decipher("z ahf zookd"), "a big apple");
		assertEquals(myCaesar.decipher("My teacher! is great^(*%$"), "My teacher! is great^(*%$");
		assertEquals(myCaesar.decipher("Mj csy ger vieh xlmw, csy tvsfefpc lezi gsqtpixih xli Geiwev Gmtliv ewwmkrqirx."), 
				"If you can read this, you probably have completed the Caesar Cipher assignment.");

	}
}
