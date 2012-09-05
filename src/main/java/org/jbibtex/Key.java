/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.util.*;

public class Key {

	private String value = null;

	transient
	private String normalizedValue = null;


	public Key(String value){
		setValue(value);
	}

	@Override
	public String toString(){
		return getValue();
	}

	@Override
	public int hashCode(){
		return (this.getNormalizedValue()).hashCode();
	}

	@Override
	public boolean equals(Object object){

		if(object instanceof Key){
			Key that = (Key)object;

			return (this.getNormalizedValue()).equals(that.getNormalizedValue());
		}

		return false;
	}

	public String getValue(){
		return this.value;
	}

	private void setValue(String key){

		if(key == null){
			throw new IllegalArgumentException();
		}

		this.value = key;
	}

	private String getNormalizedValue(){

		if(this.normalizedValue == null){
			this.normalizedValue = getValue().toLowerCase(Locale.US);
		}

		return this.normalizedValue;
	}
}