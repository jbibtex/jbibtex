/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class StringValue extends LiteralValue {

	private Style style = null;


	public StringValue(String string, Style style){
		super(string);

		setStyle(style);
	}

	@Override
	protected String format(){
		Style style = getStyle();

		return style.getBegin() + getString() + style.getEnd();
	}

	public Style getStyle(){
		return this.style;
	}

	private void setStyle(Style style){
		this.style = style;
	}

	static
	public enum Style {
		BRACED("{", "}"),
		QUOTED("\"", "\""),
		;

		private String begin = null;

		private String end = null;


		Style(String begin, String end){
			setBegin(begin);
			setEnd(end);
		}

		public String getBegin(){
			return this.begin;
		}

		private void setBegin(String begin){
			this.begin = begin;
		}

		public String getEnd(){
			return this.end;
		}

		private void setEnd(String end){
			this.end = end;
		}
	}
}