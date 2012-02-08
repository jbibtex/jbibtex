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
		BibTeXString string = this.strings.get(key);
		if(string == null){
			string = BibTeXDatabase.macros.get(key);
		}

		return string;
	}

	public Map<Key, BibTeXString> getStrings(){
		return Collections.unmodifiableMap(this.strings);
	}

	public BibTeXEntry getEntry(Key key){
		BibTeXEntry entry = this.entries.get(key);

		return entry;
	}

	public Map<Key, BibTeXEntry> getEntries(){
		return Collections.unmodifiableMap(this.entries);
	}

	private static KeyMap<BibTeXString> macros = new KeyMap<BibTeXString>();

	static
	public void addMacro(String key, String value){
		addMacro(new BibTeXString(new Key(key), new StringValue(value, StringValue.Style.BRACED)));
	}

	static
	public void addMacro(BibTeXString macro){
		BibTeXDatabase.macros.put(macro.getKey(), macro);
	}

	static {
		addMacro("jan", "January");
		addMacro("feb", "February");
		addMacro("mar", "March");
		addMacro("apr", "April");
		addMacro("may", "May");
		addMacro("jun", "June");
		addMacro("jul", "July");
		addMacro("aug", "August");
		addMacro("sep", "September");
		addMacro("oct", "October");
		addMacro("nov", "November");
		addMacro("dec", "December");
	}
}