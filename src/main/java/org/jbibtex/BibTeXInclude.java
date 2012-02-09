/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class BibTeXInclude extends BibTeXObject {

	private StringValue value = null;

	private BibTeXDatabase database = null;


	public BibTeXInclude(StringValue value, BibTeXDatabase database){
		setValue(value);
		setDatabase(database);
	}

	public StringValue getValue(){
		return this.value;
	}

	private void setValue(StringValue value){
		this.value = value;
	}

	public BibTeXDatabase getDatabase(){
		return this.database;
	}

	private void setDatabase(BibTeXDatabase database){
		this.database = database;
	}
}