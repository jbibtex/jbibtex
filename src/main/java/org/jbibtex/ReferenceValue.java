/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class ReferenceValue extends Value {

	private Key key = null;


	public ReferenceValue(Key key){
		setKey(key);
	}

	public Key getKey(){
		return this.key;
	}

	private void setKey(Key key){
		this.key = key;
	}
}