/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class BibTeXString extends BibTeXObject {

	private Key key = null;

	private Value value = null;


	public BibTeXString(Key key, Value value){
		setKey(key);
		setValue(value);
	}

	public Key getKey(){
		return this.key;
	}

	private void setKey(Key key){
		this.key = key;
	}

	public Value getValue(){
		return this.value;
	}

	private void setValue(Value value){
		this.value = value;
	}
}