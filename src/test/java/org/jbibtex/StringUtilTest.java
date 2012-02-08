/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import org.junit.*;

import static org.junit.Assert.*;

public class StringUtilTest {

	@Test
	public void addIndent(){
		String string = "First\nSecond\nThird";

		assertEquals("First\n Second\n Third", StringUtil.addIndent(string, " "));
		assertEquals("First\n\tSecond\n\tThird", StringUtil.addIndent(string, "\t"));
	}

	@Test
	public void removeIndent(){
		String string = "First\nSecond\nThird";

		assertEquals(string, StringUtil.removeIndent("First\n Second\n Third"));
		assertEquals(string, StringUtil.removeIndent("First\n\tSecond\n\tThird"));
	}
}