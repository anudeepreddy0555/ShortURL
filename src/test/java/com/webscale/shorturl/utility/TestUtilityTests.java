package com.webscale.shorturl.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;



class TestUtilityTests {

	@Test
	void testgetHashValue() {
		String text = "https://docs.mongodb.com/manual/tutorial/insert-documents/";
		String result = Utils.getHashvalue(text);
		assertTrue(result.length()==8);
	}
	
	
	@Test
	void testgetHashValue_1() {
		String text = "https://www.google.com/imghp?hl=en&tab=wi&authuser=0&ogbl";
		String result = Utils.getHashvalue(text);
		assertTrue(result.length()==8);
	}
	
	@Test
	void testgetHashValue_2() {
		String text = "https://www.oracle.com/index.html";
		String result = Utils.getHashvalue(text);
		assertTrue(result.length()==8);
	}
	
	
	@Test
	void testgetHashValue_3() {
		String text = "https://www.google.com/search?hl=en&authuser=0&tbm=isch&sxsrf=ACYBGNRkFb-RcO7Nnap2Yqe9d7qTTosPKA%3A1581836656952&source=hp&biw=1242&bih=597&ei=cOlIXtT2N8mp5wKOxJLIDA&q=dog&oq=dog&gs_l=img.3..0l10.2399.3245..3374...2.0..0.60.176.3......0....1..gws-wiz-img.....10..35i362i39j35i39.xqvU8TW-WSI&ved=0ahUKEwjU7pzgwNXnAhXJ1FkKHQ6iBMkQ4dUDCAY&uact=5#imgrc=64hfwoM178jZtM";
		String result = Utils.getHashvalue(text);
		assertTrue(result.length()==8);
	}

}
