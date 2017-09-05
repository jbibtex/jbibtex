/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
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