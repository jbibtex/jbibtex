/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BibTeXParserTest {

	@Test
	public void parseBibtex() throws Exception {
		BibTeXParser parser = new BibTeXParser();

		BibTeXDatabase database = parseFully(parser, "/bibtex.bib");

		ensureSerializability(database);
		ensureJsonSerializability(database);

		Map<Key, BibTeXEntry> entries = database.getEntries();

		assertNotNull(entries.get(new Key("first")));
		assertNotNull(entries.get(new Key("last")));

		List<Exception> exceptions = parser.getExceptions();

		assertEquals(1, exceptions.size());
	}

	@Test
	public void parseJava() throws Exception {
		BibTeXParser parser = new BibTeXParser();
		parser.addMacro("ack-bnb", "Barbara N. Beeton");
		parser.addMacro("ack-bs", "Bruce Schneier");
		parser.addMacro("ack-kl", "Ken Lunde");

		BibTeXDatabase database = parse(parser, "/java.bib");

		ensureSerializability(database);
		ensureJsonSerializability(database);

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
	public void parseMendeley() throws Exception {
		BibTeXParser parser = new BibTeXParser();

		BibTeXDatabase database = parse(parser, "/mendeley.bib");

		ensureSerializability(database);
		ensureJsonSerializability(database);
	}

	@Test
	public void parseUnix() throws Exception {
		BibTeXParser parser = new BibTeXParser();
		parser.addMacro("ack-hk", "Hanna K{\\\"o}lodziejska");
		parser.addMacro("ack-kl", "Ken Lunde");
		parser.addMacro("ack-kr", "Karin Remington");
		parser.addMacro("ack-pb", "Preston Briggs");
		parser.addMacro("ack-rfb", "Ronald F. Boisvert");

		BibTeXDatabase database = parse(parser, "/unix.bib");

		ensureSerializability(database);
		ensureJsonSerializability(database);

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

	@Test
	public void parseZotero() throws Exception {
		BibTeXParser parser = new BibTeXParser();

		BibTeXDatabase database = parse(parser, "/zotero.bib");

		ensureSerializability(database);
		ensureJsonSerializability(database);
	}

	static
	private void ensureSerializability(BibTeXDatabase database){
		BibTeXDatabase clonedDatabase;

		try {
			clonedDatabase = SerializationUtil.clone(database);
		} catch(Exception e){
			throw new AssertionError();
		}

		assertEquals((database.getObjects()).size(), (clonedDatabase.getObjects()).size());
	}

	static
	private void ensureJsonSerializability(BibTeXDatabase database){
		BibTeXDatabase clonedDatabase;

		try {
			clonedDatabase = SerializationUtil.jsonClone(database);
		} catch(Exception e){
			throw new AssertionError();
		}

		assertEquals((database.getObjects()).size(), (clonedDatabase.getObjects()).size());
	}

	static
	private BibTeXDatabase parse(BibTeXParser parser, String path) throws Exception {
		InputStream is = (BibTeXParserTest.class).getResourceAsStream(path);

		try {
			Reader reader = new InputStreamReader(is, "US-ASCII");

			try {
				return parser.parse(reader);
			} finally {
				reader.close();
			}
		} finally {
			is.close();
		}
	}

	static
	private BibTeXDatabase parseFully(BibTeXParser parser, String path) throws Exception {
		InputStream is = (BibTeXParserTest.class).getResourceAsStream(path);

		try {
			Reader reader = new InputStreamReader(is, "US-ASCII");

			try {
				return parser.parseFully(reader);
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