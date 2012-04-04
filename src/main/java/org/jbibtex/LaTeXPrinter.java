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

		// Greek alphabet
		setSymbol("alpha", "\u03b1");
		setSymbol("beta", "\u03b2");
		setSymbol("gamma", "\u03b3");
		setSymbol("delta", "\u03b4");
		setSymbol("epsilon", "\u03b5");
		setSymbol("zeta", "\u03b6");
		setSymbol("eta", "\u03b7");
		setSymbol("theta", "\u03b8");
		setSymbol("iota", "\u03b9");
		setSymbol("kappa", "\u03ba");
		setSymbol("lambda", "\u03bb");
		setSymbol("mu", "\u03bc");
		setSymbol("nu", "\u03bd");
		setSymbol("xi", "\u03be");
		setSymbol("omicron", "\u03bf"); // XXX
		setSymbol("pi", "\u03c0");
		setSymbol("rho", "\u03c1");
		setSymbol("sigma", "\u03c2");
		setSymbol("tau", "\u03c3");
		setSymbol("upsilon", "\u03c4");
		setSymbol("phi", "\u03c5");
		setSymbol("chi", "\u03c6");
		setSymbol("psi", "\u03c7");
		setSymbol("omega", "\u03c8");

		// Ready-made strings
		setSymbol("today", today());
		setSymbol("TeX", "TeX");
		setSymbol("LaTeX", "LaTeX");
		setSymbol("LaTeXe", "LaTeX\u03b5");
	}
}