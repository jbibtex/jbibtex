/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.text.*;
import java.util.*;

public class LaTeXPrinter {

	public LaTeXPrinter(){
	}

	public String print(List<LaTeXObject> objects){
		StringBuffer sb = new StringBuffer();

		for(LaTeXObject object : objects){

			if(object instanceof LaTeXCommand){
				sb.append(print((LaTeXCommand)object));
			} else

			if(object instanceof LaTeXGroup){
				sb.append(print((LaTeXGroup)object));
			} else

			if(object instanceof LaTeXString){
				sb.append(print((LaTeXString)object));
			} else

			{
				throw new IllegalArgumentException();
			}
		}

		return sb.toString();
	}

	protected String print(LaTeXCommand command){
		String symbol = getSymbol(command.getName());

		if(symbol != null){
			return symbol;
		}

		// XXX
		return "";
	}

	protected String print(LaTeXGroup group){
		return print(group.getObjects());
	}

	protected String print(LaTeXString string){
		String value = string.getValue();

		if(value.contains("--")){
			value = value.replace("---", "\u2014");
			value = value.replace("--", "\u2013");
		} // End if

		if(value.contains("`")){
			value = value.replace("``", "\u201c");
			value = value.replace("`", "\u2018");
		} // End if

		if(value.contains("'")){
			value = value.replace("''", "\u201d");
			value = value.replace("'", "\u2019");
		}

		return value;
	}

	static
	public String today(){
		Calendar now = Calendar.getInstance();

		return LATEX_TODAY.format(now.getTime());
	}

	private static final DateFormat LATEX_TODAY = new SimpleDateFormat("MMMM dd, yyyy");

	static
	public String getSymbol(String name){
		return COMMAND_SYMBOLS.get(name);
	}

	static
	public void setSymbol(String name, String symbol){
		COMMAND_SYMBOLS.put(name, symbol);
	}

	private static final Map<String, String> COMMAND_SYMBOLS = new LinkedHashMap<String, String>();

	static {
		// Special symbols
		setSymbol("#", "#");
		setSymbol("$", "$");
		setSymbol("%", "%");
		setSymbol("&", "&");
		setSymbol("\\", "\\");
		setSymbol("^", "^");
		setSymbol("_", "_");
		setSymbol("{", "{");
		setSymbol("}", "}");
		setSymbol("~", "~");

		// Text-mode commands
		setSymbol("textasciicircum", "^");
		setSymbol("textasciitilde", "~");
		setSymbol("textbackslash", "\\");
		setSymbol("textbar", "|");
		setSymbol("textbraceleft", "{");
		setSymbol("textbraceright", "}");
		setSymbol("textcopyrigh", "\u00a9");
		setSymbol("textdollar", "$");
		setSymbol("textellipsis", "\u2026");
		setSymbol("textemdash", "\u2014");
		setSymbol("textendash", "\u2013");
		setSymbol("textgreater", ">");
		setSymbol("textless", "<");
		setSymbol("textquotedblleft", "\u201c");
		setSymbol("textquotedblright", "\u201d");
		setSymbol("textquoteleft", "\u2018");
		setSymbol("textquoteright", "\u2019");
		setSymbol("textregistered", "\u00ae");
		setSymbol("texttrademark", "\u2122");
		setSymbol("textunderscore", "_");

		// Math- and text-mode commands
		setSymbol("slash", "/");
		setSymbol("backslash", "\\");
		setSymbol("endash", "\u2013");
		setSymbol("emdash", "\u2014");
		setSymbol("ldots", "\u2026");

		// Ready-made strings
		setSymbol("today", today());
		setSymbol("TeX", "TeX");
		setSymbol("LaTeX", "LaTeX");
		setSymbol("LaTeXe", "LaTeXe");
	}
}