/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class BibTeXPreamble extends BibTeXObject {

	private Value value = null;


	public BibTeXPreamble(Value value){
		setValue(value);
	}

	public Value getValue(){
		return this.value;
	}

	private void setValue(Value value){
		this.value = value;
	}
}