/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

abstract
public class Value {

	abstract
	protected String format();

	abstract
	public String toUserString();
}