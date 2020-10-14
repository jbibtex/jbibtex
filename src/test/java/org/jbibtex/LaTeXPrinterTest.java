/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
	public void printGreek() throws Exception {
		assertEquals("\u03b1", print("\\alpha"));
		assertEquals("\u03b2", print("\\beta"));
		assertEquals("\u03b3", print("\\gamma"));
	}

	@Test
	public void printAccent() throws Exception {
		String string = "H\u00e4id p\u00fchi!";

		assertEquals(string, print("H\\\"aid p\\\"uhi!"));
		assertEquals(string, print("H{\\\"a}id p{\\\"u}hi!"));
		assertEquals(string, print("H\\\"{a}id p\\\"{u}hi!"));
	}

	@Test
	public void printToday() throws Exception {
		assertEquals(LaTeXPrinter.today(), print("\\today"));
	}

	private String print(String string) throws Exception {
		LaTeXPrinter printer = new LaTeXPrinter();

		List<LaTeXObject> objects = parse(string);

		return printer.print(objects);
	}

	private List<LaTeXObject> parse(String string) throws Exception {
		LaTeXParser parser = new LaTeXParser();

		Reader reader = new StringReader(string);

		try {
			return parser.parse(reader);
		} finally {
			reader.close();
		}
	}
}