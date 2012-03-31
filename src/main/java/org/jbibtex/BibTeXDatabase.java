/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.util.*;

public class BibTeXDatabase {

	private List<BibTeXObject> objects = new ArrayList<BibTeXObject>();

	private List<BibTeXInclude> includes = new ArrayList<BibTeXInclude>();

	private KeyMap<BibTeXString> strings = new KeyMap<BibTeXString>();

	private KeyMap<BibTeXEntry> entries = new KeyMap<BibTeXEntry>();


	public void addObject(BibTeXObject object){
		this.objects.add(object);

		if(object instanceof BibTeXInclude){
			BibTeXInclude include = (BibTeXInclude)object;

			this.includes.add(include);
		} else

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

		if(object instanceof BibTeXInclude){
			BibTeXInclude include = (BibTeXInclude)object;

			this.includes.remove(include);
		} else

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

	public BibTeXString resolveString(Key key){
		BibTeXString string = this.strings.get(key);

		if(string == null){

			for(BibTeXInclude include : this.includes){
				BibTeXDatabase database = include.getDatabase();

				string = database.resolveString(key);
				if(string != null){
					return string;
				}
			}
		}

		return string;
	}

	public Map<Key, BibTeXString> getStrings(){
		return Collections.unmodifiableMap(this.strings);
	}

	public BibTeXEntry resolveEntry(Key key){
		BibTeXEntry entry = this.entries.get(key);

		if(entry == null){

			for(BibTeXInclude include : this.includes){
				BibTeXDatabase database = include.getDatabase();

				entry = database.resolveEntry(key);
				if(entry != null){
					return entry;
				}
			}
		}

		return entry;
	}

	public Map<Key, BibTeXEntry> getEntries(){
		return Collections.unmodifiableMap(this.entries);
	}
}