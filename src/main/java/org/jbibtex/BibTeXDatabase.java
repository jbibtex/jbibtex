/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.util.*;

public class BibTeXDatabase {

	private List<BibTeXObject> objects = new ArrayList<BibTeXObject>();

	private KeyMap<BibTeXString> strings = new KeyMap<BibTeXString>();

	private KeyMap<BibTeXEntry> entries = new KeyMap<BibTeXEntry>();


	public void addObject(BibTeXObject object){
		this.objects.add(object);

		if(object instanceof BibTeXString){
			BibTeXString string = (BibTeXString)object;

			this.strings.put(string.getKey(), string);
		} else

		if(object instanceof BibTeXEntry){
			BibTeXEntry entry = (BibTeXEntry)object;

			this.entries.put(entry.getKey(), entry);
		}
	}

	public void removeObject(BibTeXObject object){
		this.objects.remove(object);

		if(object instanceof BibTeXString){
			BibTeXString string = (BibTeXString)object;

			this.strings.remove(string.getKey());
		} else

		if(object instanceof BibTeXEntry){
			BibTeXEntry entry = (BibTeXEntry)object;

			this.entries.remove(entry.getKey());
		}
	}

	public List<BibTeXObject> getObjects(){
		return Collections.unmodifiableList(this.objects);
	}

	public BibTeXString getString(Key key){
		return this.strings.get(key);
	}

	public Map<Key, BibTeXString> getStrings(){
		return Collections.unmodifiableMap(this.strings);
	}

	public BibTeXEntry getEntry(Key key){
		return this.entries.get(key);
	}

	public Map<Key, BibTeXEntry> getEntries(){
		return Collections.unmodifiableMap(this.entries);
	}
}