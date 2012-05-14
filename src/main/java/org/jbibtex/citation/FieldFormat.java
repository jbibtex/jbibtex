/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex.citation;

import java.io.*;
import java.util.*;

import org.jbibtex.*;

public class FieldFormat {

	private Key key = null;

	private String separator = null;


	public FieldFormat(Key key, String separator){
		setKey(key);
		setSeparator(separator);
	}

	public String format(Value value, boolean latex, boolean html){
		String string = value.toUserString();

		if(latex){

			try {
				string = printLaTeX(parseLaTeX(string));
			} catch(Exception e){
				throw new IllegalArgumentException(string, e);
			}
		} // End if

		if(html){
			string = StringUtil.escapeXml(string);
		}

		return string;
	}

	public Key getKey(){
		return this.key;
	}

	private void setKey(Key key){
		this.key = key;
	}

	public String getSeparator(){
		return this.separator;
	}

	private void setSeparator(String separator){
		this.separator = separator;
	}

	static
	private List<LaTeXObject> parseLaTeX(String string) throws IOException, ParseException {
		Reader reader = new StringReader(string);

		try {
			LaTeXParser parser = new LaTeXParser();

			return parser.parse(reader);
		} finally {
			reader.close();
		}
	}

	static
	private String printLaTeX(List<LaTeXObject> objects){
		LaTeXPrinter printer = new LaTeXPrinter();

		return printer.print(objects);
	}
}