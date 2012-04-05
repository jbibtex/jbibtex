/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex.citation;

import java.util.*;

public class EntryFormat {

	private List<FieldFormat> fields = null;


	public EntryFormat(List<FieldFormat> fields){
		setFields(fields);
	}

	public List<FieldFormat> getFields(){
		return Collections.unmodifiableList(this.fields);
	}

	private void setFields(List<FieldFormat> fields){
		this.fields = fields;
	}
}