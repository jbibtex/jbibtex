/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class LaTeXString extends LaTeXObject {

	private String value = null;


	public LaTeXString(String value){
		setValue(value);
	}

	public String getValue(){
		return this.value;
	}

	private void setValue(String value){
		this.value = value;
	}
}