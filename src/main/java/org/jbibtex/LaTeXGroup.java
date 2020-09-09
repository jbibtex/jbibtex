/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.util.Collections;
import java.util.List;

public class LaTeXGroup extends LaTeXObject {

	private List<LaTeXObject> objects = null;


	LaTeXGroup(){
	}

	public LaTeXGroup(List<LaTeXObject> objects){
		setObjects(objects);
	}

	public List<LaTeXObject> getObjects(){
		return Collections.unmodifiableList(this.objects);
	}

	private void setObjects(List<LaTeXObject> objects){
		this.objects = objects;
	}
}