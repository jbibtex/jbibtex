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
		boolean success;

		if(object instanceof BibTeXInclude){
			BibTeXInclude include = (BibTeXInclude)object;

			success = this.includes.add(include);
		} else

		if(object instanceof BibTeXString){
			BibTeXString string = (BibTeXString)object;

			success = this.strings.putIfMissing(string.getKey(), string);
		} else

		if(object instanceof BibTeXEntry){
			BibTeXEntry entry = (BibTeXEntry)object;

			success = this.entries.putIfMissing(entry.getKey(), entry);
		} else

		{
			success = true;
		} // End if

		if(success){
			this.objects.add(object);
		}
	}

	public void removeObject(BibTeXObject object){
		boolean success;

		if(object instanceof BibTeXInclude){
			BibTeXInclude include = (BibTeXInclude)object;

			success = this.includes.remove(include);
		} else

		if(object instanceof BibTeXString){
			BibTeXString string = (BibTeXString)object;

			success = this.strings.removeIfPresent(string.getKey());
		} else

		if(object instanceof BibTeXEntry){
			BibTeXEntry entry = (BibTeXEntry)object;

			success = this.entries.removeIfPresent(entry.getKey());
		} else

		{
			success = true;
		} // End if

		if(success){
			this.objects.remove(object);
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