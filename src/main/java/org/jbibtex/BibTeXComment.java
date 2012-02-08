/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class BibTeXComment extends BibTeXObject {

	private LiteralValue value = null;


	public BibTeXComment(LiteralValue value){
		setValue(value);
	}

	public LiteralValue getValue(){
		return this.value;
	}

	private void setValue(LiteralValue value){
		this.value = value;
	}
}