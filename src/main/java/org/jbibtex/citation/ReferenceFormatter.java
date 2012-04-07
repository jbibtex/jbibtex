/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex.citation;

import java.util.*;

import org.jbibtex.*;

public class ReferenceFormatter {

	private ReferenceStyle style = null;


	public ReferenceFormatter(ReferenceStyle style){
		setStyle(style);
	}

	public String format(BibTeXEntry entry, boolean html){
		StringBuffer sb = new StringBuffer();

		ReferenceStyle style = getStyle();

		Key type = entry.getType();

		EntryFormat format = style.getFormat(type);
		if(format == null){
			throw new IllegalArgumentException(type.getValue());
		}

		List<FieldFormat> fields = format.getFields();
		for(FieldFormat field : fields){
			Key key = field.getKey();
			Value value = entry.getField(key);

			if(value == null){
				continue;
			}

			if(sb.length() > 0){
				sb.append(" ");
			}

			String string = field.format(value, html);
			sb.append(string);

			ensureSuffix(sb, field.getSeparator());
		}

		ensureSuffix(sb, (fields.get(fields.size() - 1)).getSeparator());

		return sb.toString();
	}

	private void ensureSuffix(StringBuffer sb, String separator){

		if(separator != null && !hasSuffix(sb, separator)){
			sb.append(separator);
		}
	}

	public ReferenceStyle getStyle(){
		return this.style;
	}

	private void setStyle(ReferenceStyle style){
		this.style = style;
	}

	static
	private boolean hasSuffix(StringBuffer sb, String string){
		return (sb.length() >= string.length()) && (sb.substring(sb.length() - string.length(), sb.length())).equals(string);
	}
}