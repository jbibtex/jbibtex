/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex.citation;

import org.jbibtex.*;

public class ReferenceStyle {

	private KeyMap<EntryFormat> formats = new KeyMap<EntryFormat>();


	public EntryFormat getFormat(Key type){
		return this.formats.get(type);
	}

	public void addFormat(Key type, EntryFormat format){
		this.formats.put(type, format);
	}
}