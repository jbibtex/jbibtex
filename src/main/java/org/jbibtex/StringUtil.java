/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class StringUtil {

	private StringUtil(){
	}

	static
	public String addIndent(String string, String indent){

		if(string.indexOf('\n') > -1){
			string = string.replaceAll("\\n", "\n" + indent);
		}

		return string;
	}

	static
	public String removeIndent(String string){

		if(string.indexOf('\n') > -1){
			string = string.replaceAll("\\n([\\ ,\\t])*", "\n");
		}

		return string;
	}
}