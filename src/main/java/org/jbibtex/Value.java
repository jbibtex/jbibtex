/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

abstract
public class Value {

	abstract
	protected String format();

	/**
	 * Returns a string representation of the object which is suitable for displaying to end users.
	 * The result may contain LaTeX language markup.
	 *
	 * @see LaTeXParser
	 * @see LaTeXPrinter
	 */
	abstract
	public String toUserString();
}