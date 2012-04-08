/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class StringUtil {

	private StringUtil(){
	}

	static
	public String addIndent(String string, String indent){
		return addIndent(string, 1, indent);
	}

	static
	public String addIndent(String string, int level, String indent){

		if(string.indexOf('\n') > -1){
			String levelIndent = indent;

			for(int i = 1; i < level; i++){
				levelIndent += indent;
			}

			string = string.replaceAll("\\n", "\n" + levelIndent);
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

	static
	public String escapeXml(String string){
		StringBuffer sb = new StringBuffer(string.length() * 2);

		for(int i = 0; i < string.length(); i++){
			char c = string.charAt(i);

			switch(c){
				case '\"':
					sb.append("&quot;");
					break;
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				default:
					sb.append(c);
					break;
			}
		}

		return sb.toString();
	}
}