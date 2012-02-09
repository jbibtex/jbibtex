/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class KeyValue extends Value {

	private String string = null;


	public KeyValue(String string){
		setString(string);
	}

	public Key toKey(){
		Key key = new Key(toString());

		return key;
	}

	@Override
	protected String format(){
		return getString();
	}

	@Override
	public String toString(){
		return getString();
	}

	public String getString(){
		return this.string;
	}

	private void setString(String string){
		this.string = string;
	}
}