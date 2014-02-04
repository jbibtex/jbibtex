/*
 * Copyright (c) 2014 University of Tartu
 */
package org.jbibtex;

import java.io.*;

import org.junit.*;

import static org.junit.Assert.*;

public class CharacterFilterReaderTest {

	@Test
	public void readCharacter() throws IOException {
		Reader reader = new CharacterFilterReader(new CharArrayReader(buffer));

		assertEquals('a', reader.read());
		assertEquals('1', reader.read());

		// EOF
		assertEquals(-1, reader.read());

		reader.close();
	}

	@Test
	public void readCharacterArray() throws IOException {
		Reader reader = new CharacterFilterReader(new CharArrayReader(buffer));

		char[] result = new char[512];

		int count = reader.read(result);

		assertEquals(2, count);

		assertEquals('a', result[0]);
		assertEquals('1', result[1]);

		// EOF
		assertEquals(-1, reader.read());

		reader.close();
	}

	private static final char[] buffer = {(char)0, 'a', (char)0, '1', (char)0};
}