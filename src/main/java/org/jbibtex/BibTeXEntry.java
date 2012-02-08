/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.util.*;

public class BibTeXEntry extends BibTeXObject {

	private Key type = null;

	private Key key = null;

	private KeyMap<Value> fields = new KeyMap<Value>();


	public BibTeXEntry(Key type, Key key){
		setType(type);
		setKey(key);
	}

	public void addField(Key key, Value value){
		this.fields.put(key, value);
	}

	public void removeField(Key key){
		this.fields.remove(key);
	}

	public Key getType(){
		return this.type;
	}

	private void setType(Key type){
		this.type = type;
	}

	public Key getKey(){
		return this.key;
	}

	private void setKey(Key key){
		this.key = key;
	}

	public Map<Key, Value> getFields(){
		return Collections.unmodifiableMap(this.fields);
	}
}