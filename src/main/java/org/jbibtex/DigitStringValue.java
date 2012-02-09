/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class DigitStringValue extends LiteralValue {

	public DigitStringValue(String string){
		super(string);
	}

	@Override
	protected String format(){
		return getString();
	}
}