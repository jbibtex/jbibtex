/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class CrossReferenceValue extends Value implements Resolvable {

	private Value value = null;

	private BibTeXEntry entry = null;


	public CrossReferenceValue(Value value, BibTeXEntry entry){
		setValue(value);
		setEntry(entry);
	}

	@Override
	protected String format(){
		return getValue().format();
	}

	@Override
	public String toUserString(){
		return getValue().toUserString();
	}

	/**
	 * @see BibTeXParser#checkCrossReferenceResolution(Key, BibTeXEntry)
	 */
	@Override
	public boolean isResolved(){
		return getEntry() != null;
	}

	public Value getValue(){
		return this.value;
	}

	private void setValue(Value value){
		this.value = value;
	}

	public BibTeXEntry getEntry(){
		return this.entry;
	}

	private void setEntry(BibTeXEntry entry){
		this.entry = entry;
	}
}