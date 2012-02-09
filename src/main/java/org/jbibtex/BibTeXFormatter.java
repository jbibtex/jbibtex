/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;
import java.util.*;

public class BibTeXFormatter {

	private String indent = "\t";


	public BibTeXFormatter(){
	}

	public void format(BibTeXDatabase database, Writer writer) throws IOException {
		List<BibTeXObject> objects = database.getObjects();

		String separator = "";

		for(BibTeXObject object : objects){
			writer.write(separator);

			if(object instanceof BibTeXComment){
				format((BibTeXComment)object, writer);
			} else

			if(object instanceof BibTeXEntry){
				format((BibTeXEntry)object, writer);
			} else

			if(object instanceof BibTeXInclude){
				format((BibTeXInclude)object, writer);
			} else

			if(object instanceof BibTeXPreamble){
				format((BibTeXPreamble)object, writer);
			} else

			if(object instanceof BibTeXString){
				format((BibTeXString)object, writer);
			} else

			{
				throw new IllegalArgumentException();
			}

			separator = "\n\n";
		}

		writer.flush();
	}

	protected void format(BibTeXComment comment, Writer writer) throws IOException {
		writer.write("@Comment");

		format(comment.getValue(), 1, writer);
	}

	protected void format(BibTeXEntry entry, Writer writer) throws IOException {
		writer.write("@");
		format(entry.getType(), writer);

		writer.write('{');
		format(entry.getKey(), writer);
		writer.write(',');
		writer.write('\n');

		Collection<Map.Entry<Key, Value>> fields = (entry.getFields()).entrySet();
		for(Iterator<Map.Entry<Key, Value>> it = fields.iterator(); it.hasNext(); ){
			Map.Entry<Key, Value> field = it.next();

			writer.write(getIndent());

			format(field.getKey(), writer);
			writer.write(" = ");
			format(field.getValue(), 2, writer);

			if(it.hasNext()){
				writer.write(',');
			}

			writer.write('\n');
		}

		writer.write('}');
	}

	protected void format(BibTeXInclude include, Writer writer) throws IOException {
		writer.write("@Include");

		format(include.getValue(), 1, writer);
	}

	protected void format(BibTeXPreamble preamble, Writer writer) throws IOException {
		writer.write("@Preamble");

		writer.write('{');
		format(preamble.getValue(), 1, writer);
		writer.write('}');
	}

	protected void format(BibTeXString string, Writer writer) throws IOException {
		writer.write("@String");

		writer.write('{');
		format(string.getKey(), writer);
		writer.write(" = ");
		format(string.getValue(), 1, writer);
		writer.write('}');
	}

	protected void format(Key key, Writer writer) throws IOException {
		String string = key.getValue();

		writer.write(string);
	}

	protected void format(Value value, int level, Writer writer) throws IOException {
		String string = StringUtil.addIndent(value.format(), level, getIndent());

		writer.write(string);
	}

	public String getIndent(){
		return this.indent;
	}

	public void setIndent(String indent){
		this.indent = indent;
	}
}