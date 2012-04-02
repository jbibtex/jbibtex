/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;
import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class LaTeXPrinterTest {

	@Test
	public void printDash() throws Exception {
		assertEquals("\u2013", print("--"));
		assertEquals("\u2013", print("\\endash"));

		assertEquals("\u2014", print("---"));
		assertEquals("\u2014", print("\\emdash"));
	}

	@Test
	public void printQuote() throws Exception {
		assertEquals("\u201c", print("``"));
		assertEquals("\u2018", print("`"));

		assertEquals("\u201d", print("''"));
		assertEquals("\u2019", print("'"));
	}

	@Test
	public void printToday() throws Exception {
		assertEquals(LaTeXPrinter.today(), print("\\today"));
	}

	static
	private String print(String string) throws Exception {
		LaTeXPrinter printer = new LaTeXPrinter();

		List<LaTeXObject> objects = parse(string);

		return printer.print(objects);
	}

	static
	private List<LaTeXObject> parse(String string) throws Exception {
		Reader reader = new StringReader(string);

		try {
			LaTeXParser parser = new LaTeXParser();

			return parser.parse(reader);
		} finally {
			reader.close();
		}
	}
}