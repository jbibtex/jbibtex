/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BibTeXEntry extends BibTeXObject {

	private Key type = null;

	private Key key = null;

	private KeyMap<Value> fields = new KeyMap<>();


	BibTeXEntry(){
	}

	public BibTeXEntry(Key type, Key key){
		setType(type);
		setKey(key);
	}

	@JsonIgnore
	public BibTeXEntry getCrossReference(){
		Value value = this.fields.get(KEY_CROSSREF);

		CrossReferenceValue crossRefValue = (CrossReferenceValue)value;
		if(crossRefValue != null){
			return crossRefValue.getEntry();
		}

		return null;
	}

	public Value getField(Key key){
		Value value = this.fields.get(key);

		if(value == null){
			BibTeXEntry entry = getCrossReference();

			if(entry != null){
				return entry.getField(key);
			}
		}

		return value;
	}

	public void addField(Key key, Value value){
		this.fields.put(key, value);
	}

	public void addAllFields(Map<Key, Value> fields){
		this.fields.putAll(fields);
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

	public static final Key TYPE_ARTICLE = new Key("article");
	public static final Key TYPE_BOOK = new Key("book");
	public static final Key TYPE_BOOKLET = new Key("booklet");
	public static final Key TYPE_CONFERENCE = new Key("conference");
	public static final Key TYPE_INBOOK = new Key("inbook");
	public static final Key TYPE_INCOLLECTION = new Key("incollection");
	public static final Key TYPE_INPROCEEDINGS = new Key("inproceedings");
	public static final Key TYPE_MANUAL = new Key("manual");
	public static final Key TYPE_MASTERSTHESIS = new Key("mastersthesis");
	public static final Key TYPE_MISC = new Key("misc");
	public static final Key TYPE_PHDTHESIS = new Key("phdthesis");
	public static final Key TYPE_PROCEEDINGS = new Key("proceedings");
	public static final Key TYPE_TECHREPORT = new Key("techreport");
	public static final Key TYPE_UNPUBLISHED = new Key("unpublished");

	public static final Key KEY_ADDRESS = new Key("address");
	public static final Key KEY_ANNOTE = new Key("annote");
	public static final Key KEY_AUTHOR = new Key("author");
	public static final Key KEY_BOOKTITLE = new Key("booktitle");
	public static final Key KEY_CHAPTER = new Key("chapter");
	public static final Key KEY_CROSSREF = new Key("crossref");
	public static final Key KEY_DOI = new Key("doi");
	public static final Key KEY_EDITION = new Key("edition");
	public static final Key KEY_EDITOR = new Key("editor");
	public static final Key KEY_EPRINT = new Key("eprint");
	public static final Key KEY_HOWPUBLISHED = new Key("howpublished");
	public static final Key KEY_INSTITUTION = new Key("institution");
	public static final Key KEY_JOURNAL = new Key("journal");
	public static final Key KEY_KEY = new Key("key");
	public static final Key KEY_MONTH = new Key("month");
	public static final Key KEY_NOTE = new Key("note");
	public static final Key KEY_NUMBER = new Key("number");
	public static final Key KEY_ORGANIZATION = new Key("organization");
	public static final Key KEY_PAGES = new Key("pages");
	public static final Key KEY_PUBLISHER = new Key("publisher");
	public static final Key KEY_SCHOOL = new Key("school");
	public static final Key KEY_SERIES = new Key("series");
	public static final Key KEY_TITLE = new Key("title");
	public static final Key KEY_TYPE = new Key("type");
	public static final Key KEY_URL = new Key("url");
	public static final Key KEY_VOLUME = new Key("volume");
	public static final Key KEY_YEAR = new Key("year");
}