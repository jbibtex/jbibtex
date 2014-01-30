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
		TextBuilder builder = new TextBuilder();

		print(objects, builder);

		return builder.buildString();
	}

	private void print(List<LaTeXObject> objects, TextBuilder builder){

		for(LaTeXObject object : objects){

			if(object instanceof LaTeXCommand){
				print((LaTeXCommand)object, builder);
			} else

			if(object instanceof LaTeXGroup){
				print((LaTeXGroup)object, builder);
			} else

			if(object instanceof LaTeXString){
				print((LaTeXString)object, builder);
			} else

			{
				throw new IllegalArgumentException();
			}
		}
	}

	private void print(LaTeXCommand command, TextBuilder builder){
		char accent = getAccent(command.getName());
		if(accent > 0){
			builder.setAccent(accent);

			return;
		}

		String symbol = getSymbol(command.getName());
		if(symbol != null){
			builder.append(symbol);
		}
	}

	private void print(LaTeXGroup group, TextBuilder builder){
		builder.append(print(group.getObjects()));
	}

	private void print(LaTeXString string, TextBuilder builder){
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

		builder.append(value);
	}

	static
	public String today(){
		Calendar now = Calendar.getInstance();

		return LATEX_TODAY.format(now.getTime());
	}

	private static final DateFormat LATEX_TODAY = new SimpleDateFormat("MMMM dd, yyyy");

	static
	private char getAccent(String name){
		char c = (name.length() == 1 ? name.charAt(0) : 0);

		switch(c){
			case '`':
			case '\'':
			case '^':
			case '\"':
			case 'H':
			case '~':
			case 'c':
			case 'k':
			case '=':
			case 'b':
			case '.':
			case 'd':
			case 'r':
			case 'u':
			case 'v':
			case 't':
				return c;
			default:
				break;
		}

		return 0;
	}

	static
	private char applyAccent(char accent, char c){

		switch(accent){
			// Grave
			case '`':
				switch(c){
					case 'A':
						return '\u00c0';
					case 'E':
						return '\u00c8';
					case 'I':
						return '\u00cc';
					case 'O':
						return '\u00d2';
					case 'U':
						return '\u00d9';
					case 'a':
						return '\u00e0';
					case 'e':
						return '\u00e8';
					case 'i':
						return '\u00ec';
					case 'o':
						return '\u00f2';
					case 'u':
						return '\u00f9';
					default:
						break;
				}
				break;
			// Acute
			case '\'':
				switch(c){
					case 'A':
						return '\u00c1';
					case 'E':
						return '\u00c9';
					case 'I':
						return '\u00cd';
					case 'O':
						return '\u00d3';
					case 'U':
						return '\u00da';
					case 'Y':
						return '\u00dd';
					case 'a':
						return '\u00e1';
					case 'e':
						return '\u00e9';
					case 'i':
						return '\u00ed';
					case 'o':
						return '\u00f3';
					case 'u':
						return '\u00fa';
					case 'y':
						return '\u00fd';
					default:
						break;
				}
				break;
			// Diaeresis
			case '\"':
				switch(c){
					case 'A':
						return '\u00c4';
					case 'E':
						return '\u00cb';
					case 'I':
						return '\u00cf';
					case 'O':
						return '\u00d6';
					case 'U':
						return '\u00dc';
					case 'a':
						return '\u00e4';
					case 'e':
						return '\u00eb';
					case 'i':
						return '\u00ef';
					case 'o':
						return '\u00f6';
					case 'u':
						return '\u00fc';
					default:
						break;
				}
				break;
			// Tilde
			case '~':
				switch(c){
					case 'A':
						return '\u00c3';
					case 'N':
						return '\u00d1';
					case 'O':
						return '\u00d5';
					case 'a':
						return '\u00e3';
					case 'n':
						return '\u00f1';
					case 'o':
						return '\u00f5';
					default:
						break;
				}
				break;
			// Ring above
			case 'r':
				switch(c){
					case 'U':
						return '\u016e';
					case 'u':
						return '\u016f';
					default:
						break;
				}
				break;
			// Caron
			case 'v':
				switch(c){
					case 'C':
						return '\u010c';
					case 'D':
						return '\u010e';
					case 'E':
						return '\u011a';
					case 'N':
						return '\u0147';
					case 'R':
						return '\u0158';
					case 'S':
						return '\u0160';
					case 'T':
						return '\u0164';
					case 'Z':
						return '\u017d';
					case 'c':
						return '\u010d';
					case 'd':
						return '\u010f';
					case 'e':
						return '\u011b';
					case 'n':
						return '\u0148';
					case 'r':
						return '\u0159';
					case 's':
						return '\u0161';
					case 't':
						return '\u0165';
					case 'z':
						return '\u017e';
					default:
						break;
				}
				break;
			default:
				break;
		}

		return c;
	}

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

		// Non-ASCII letters
		setSymbol("AA", "\u00c5");
		setSymbol("AE", "\u00c6");
		setSymbol("O", "\u00d8");
		setSymbol("SS", "SS");
		setSymbol("aa", "\u00e5");
		setSymbol("ae", "\u00e6");
		setSymbol("o", "\u00f8");
		setSymbol("ss", "\u00df");

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

	static
	private class TextBuilder {

		private StringBuilder sb = new StringBuilder();

		private char accent = 0;


		public void append(String string){
			char accent = getAccent();

			if(accent > 0){

				if(string.length() > 0){
					string = applyAccent(accent, string.charAt(0)) + string.substring(1);
				}

				setAccent((char)0);
			}

			this.sb.append(string);
		}

		public char getAccent(){
			return this.accent;
		}

		private void setAccent(char accent){
			this.accent = accent;
		}

		public String buildString(){
			return this.sb.toString();
		}
	}
}