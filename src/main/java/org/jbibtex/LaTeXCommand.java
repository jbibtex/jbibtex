/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class LaTeXCommand extends LaTeXObject {

	private String name = null;


	public LaTeXCommand(String name){
		setName(name);
	}

	public String getName(){
		return this.name;
	}

	private void setName(String name){
		this.name = name;
	}
}