/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class BibTeXComment extends BibTeXObject {

	private StringValue value = null;


	public BibTeXComment(StringValue value){
		setValue(value);
	}

	public StringValue getValue(){
		return this.value;
	}

	private void setValue(StringValue value){
		this.value = value;
	}
}