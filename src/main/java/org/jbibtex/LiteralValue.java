/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

abstract
public class LiteralValue extends Value {

	private String string = null;


	public LiteralValue(String string){
		setString(string);
	}

	@Override
	public String toUserString(){
		return getString();
	}

	public String getString(){
		return this.string;
	}

	private void setString(String string){
		this.string = string;
	}
}