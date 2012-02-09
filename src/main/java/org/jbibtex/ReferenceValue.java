/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class ReferenceValue extends Value {

	private Key key = null;

	private BibTeXString string = null;


	public ReferenceValue(Key key, BibTeXString string){
		setKey(key);
		setString(string);
	}

	@Override
	protected String format(){
		Key key = getKey();

		return key.getValue();
	}

	@Override
	public String toString(){
		Value value = getString().getValue();

		return value.toString();
	}

	public Key getKey(){
		return this.key;
	}

	private void setKey(Key key){
		this.key = key;
	}

	public BibTeXString getString(){
		return this.string;
	}

	public void setString(BibTeXString string){
		this.string = string;
	}
}