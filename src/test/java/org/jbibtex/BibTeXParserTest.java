/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;
import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class BibTeXParserTest {

	@Before
	public void initAckMacros(){
		String[] macros = {"ack-bnb", "ack-bs", "ack-hk", "ack-kl", "ack-kr", "ack-pb", "ack-rfb"};

		for(String macro : macros){
			BibTeXParser.addMacro(macro, macro);
		}
	}

	@Test
	public void parseJava() throws Exception {
		BibTeXDatabase database = parse("/java.bib");

		List<BibTeXObject> objects = database.getObjects();
		assertEquals(4498, objects.size());

		Map<Key, BibTeXString> strings = database.getStrings();
		assertEquals(467, strings.size());

		Map<Key, BibTeXEntry> entries = database.getEntries();
		assertEquals(4030, entries.size());

		Collection<BibTeXEntry> values = entries.values();
		assertEquals(1890, countType(values, BibTeXEntry.TYPE_ARTICLE));
		assertEquals(1675, countType(values, BibTeXEntry.TYPE_BOOK));
		assertEquals(232, countType(values, BibTeXEntry.TYPE_INPROCEEDINGS));
		assertEquals(8, countType(values, BibTeXEntry.TYPE_MANUAL));
		assertEquals(13, countType(values, BibTeXEntry.TYPE_MASTERSTHESIS));
		assertEquals(62, countType(values, BibTeXEntry.TYPE_MISC));
		assertEquals(6, countType(values, new Key("periodical")));
		assertEquals(1, countType(values, BibTeXEntry.TYPE_PHDTHESIS));
		assertEquals(114, countType(values, BibTeXEntry.TYPE_PROCEEDINGS));
		assertEquals(20, countType(values, BibTeXEntry.TYPE_TECHREPORT));
		assertEquals(9, countType(values, BibTeXEntry.TYPE_UNPUBLISHED));
	}

	@Test
	public void parseUnix() throws Exception {
		BibTeXDatabase database = parse("/unix.bib");

		List<BibTeXObject> objects = database.getObjects();
		assertEquals(2632, objects.size());

		Map<Key, BibTeXString> strings = database.getStrings();
		assertEquals(358, strings.size());

		Map<Key, BibTeXEntry> entries = database.getEntries();
		assertEquals(2273, entries.size());

		Collection<BibTeXEntry> values = entries.values();
		assertEquals(645, countType(values, BibTeXEntry.TYPE_ARTICLE));
		assertEquals(1447, countType(values, BibTeXEntry.TYPE_BOOK));
		assertEquals(6, countType(values, BibTeXEntry.TYPE_INCOLLECTION));
		assertEquals(48, countType(values, BibTeXEntry.TYPE_INPROCEEDINGS));
		assertEquals(31, countType(values, BibTeXEntry.TYPE_MANUAL));
		assertEquals(12, countType(values, BibTeXEntry.TYPE_MASTERSTHESIS));
		assertEquals(7, countType(values, BibTeXEntry.TYPE_MISC));
		assertEquals(6, countType(values, new Key("periodical")));
		assertEquals(2, countType(values, BibTeXEntry.TYPE_PHDTHESIS));
		assertEquals(34, countType(values, BibTeXEntry.TYPE_PROCEEDINGS));
		assertEquals(33, countType(values, BibTeXEntry.TYPE_TECHREPORT));
		assertEquals(2, countType(values, BibTeXEntry.TYPE_UNPUBLISHED));
	}

	static
	private BibTeXDatabase parse(String path) throws IOException, ParseException {
		InputStream is = (BibTeXParserTest.class).getResourceAsStream(path);

		try {
			Reader reader = new InputStreamReader(is, "US-ASCII");

			try {
				BibTeXParser parser = new BibTeXParser();

				return parser.parse(reader);
			} finally {
				reader.close();
			}
		} finally {
			is.close();
		}
	}

	static
	private int countType(Collection<BibTeXEntry> entries, Key type){
		int count = 0;

		for(BibTeXEntry entry : entries){

			if((entry.getType()).equals(type)){
				count++;
			}
		}

		return count;
	}
}