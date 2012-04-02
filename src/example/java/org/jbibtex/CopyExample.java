/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;

public class CopyExample {

	static
	public void main(String[] args) throws Exception {

		if(args.length < 1 || args.length > 2){
			System.out.println("Usage: java " + CopyExample.class + " <Input file> <Output file>?");

			System.exit(-1);
		}

		File input = new File(args[0]);

		BibTeXDatabase database = parse(input);

		File output = (args.length > 1 ? new File(args[1]) : null);

		format(database, output);
	}

	static
	private BibTeXDatabase parse(File file) throws IOException, ParseException {
		Reader reader = new FileReader(file);

		try {
			BibTeXParser parser = new BibTeXParser();

			return parser.parse(reader);
		} finally {
			reader.close();
		}
	}

	static
	private void format(BibTeXDatabase database, File file) throws IOException {
		Writer writer = (file != null ? new FileWriter(file) : new OutputStreamWriter(System.out));

		try {
			BibTeXFormatter formatter = new BibTeXFormatter();

			formatter.format(database, writer);
		} finally {
			writer.close();
		}
	}
}