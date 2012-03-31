/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class ReferenceValue extends Value {

	private KeyValue value = null;

	private BibTeXString string = null;


	public ReferenceValue(KeyValue value, BibTeXString string){
		setValue(value);
		setString(string);
	}

	@Override
	protected String format(){
		return getValue().format();
	}

	@Override
	public String toString(){
		BibTeXString string = getString();

		if(string != null){
			return (string.getValue()).toString();
		}

		return getValue().toString();
	}

	public KeyValue getValue(){
		return this.value;
	}

	private void setValue(KeyValue value){
		this.value = value;
	}

	public BibTeXString getString(){
		return this.string;
	}

	public void setString(BibTeXString string){
		this.string = string;
	}
}