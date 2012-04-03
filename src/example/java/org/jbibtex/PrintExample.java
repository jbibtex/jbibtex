/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;
import java.util.*;

public class PrintExample {

	static
	public void main(String... args) throws Exception {

		if(args.length < 1 || args.length > 2){
			System.err.println("Usage: java " + PrintExample.class + " <Input file> <BibTeX entry field key>?");

			System.exit(-1);
		}

		File input = new File(args[0]);

		BibTeXDatabase database = CopyExample.parseBibTeX(input);

		Key key = new Key(args.length > 1 ? args[1] : "title");

		Collection<BibTeXEntry> entries = (database.getEntries()).values();
		for(BibTeXEntry entry : entries){
			Value value = entry.getField(key);

			// The field is not defined
			if(value == null){
				continue;
			}

			try {
				String latexString = value.toUserString();
				System.out.println(latexString);

				List<LaTeXObject> objects = parseLaTeX(latexString);

				String plainTextString = printLaTeX(objects);
				System.out.println(plainTextString);
			} catch(Exception e){
				e.printStackTrace(System.out);
			}

			System.out.println();
		}
	}

	static
	public List<LaTeXObject> parseLaTeX(String string) throws IOException, ParseException {
		Reader reader = new StringReader(string);

		try {
			LaTeXParser parser = new LaTeXParser();

			return parser.parse(reader);
		} finally {
			reader.close();
		}
	}

	static
	public String printLaTeX(List<LaTeXObject> objects){
		LaTeXPrinter printer = new LaTeXPrinter();

		return printer.print(objects);
	}
}