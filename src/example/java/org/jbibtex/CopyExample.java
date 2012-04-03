/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;

public class CopyExample {

	static
	public void main(String[] args) throws Exception {

		if(args.length < 1 || args.length > 2){
			System.err.println("Usage: java " + CopyExample.class + " <Input file> <Output file>?");

			System.exit(-1);
		}

		File input = new File(args[0]);

		BibTeXDatabase database = parseBibTeX(input);

		File output = (args.length > 1 ? new File(args[1]) : null);

		formatBibTeX(database, output);
	}

	static
	public BibTeXDatabase parseBibTeX(File file) throws IOException, ParseException {
		Reader reader = new FileReader(file);

		try {
			BibTeXParser parser = new BibTeXParser(){

				@Override
				public void checkStringResolution(Key key, BibTeXString string){

					if(string == null){
						System.err.println("Unresolved string: \"" + key.getValue() + "\"");
					}
				}

				@Override
				public void checkCrossReferenceResolution(Key key, BibTeXEntry entry){

					if(entry == null){
						System.err.println("Unresolved cross-reference: \"" + key.getValue() + "\"");
					}
				}
			};

			return parser.parse(reader);
		} finally {
			reader.close();
		}
	}

	static
	public void formatBibTeX(BibTeXDatabase database, File file) throws IOException {
		Writer writer = (file != null ? new FileWriter(file) : new OutputStreamWriter(System.out));

		try {
			BibTeXFormatter formatter = new BibTeXFormatter();

			formatter.format(database, writer);
		} finally {
			writer.close();
		}
	}
}