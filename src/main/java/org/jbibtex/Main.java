/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;

public class Main {

	static
	public void main(String[] args) throws Exception {

		if(args.length < 1 || args.length > 2){
			System.out.println("Usage: java " + Main.class + " <Input file> <Output file>?");

			System.exit(-1);
		}

		BibTeXDatabase database;

		Reader reader = new FileReader(args[0]);

		try {
			BibTeXParser parser = new BibTeXParser();
			database = parser.parse(reader);
		} finally {
			reader.close();
		}

		Writer writer = (args.length > 1 ? new FileWriter(args[1]) : new OutputStreamWriter(System.out));

		try {
			BibTeXFormatter formatter = new BibTeXFormatter();
			formatter.format(database, writer);
		} finally {
			writer.close();
		}
	}
}