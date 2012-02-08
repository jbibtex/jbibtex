/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class ObjectResolutionException extends IllegalArgumentException {

	public ObjectResolutionException(Key key){
		super(key.getValue());
	}
}