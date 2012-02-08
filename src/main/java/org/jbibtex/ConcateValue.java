/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class ConcateValue extends Value {

	private Value left = null;

	private Value right = null;


	public ConcateValue(Value left, Value right){
		setLeft(left);
		setRight(right);
	}

	public Value getLeft(){
		return this.left;
	}

	private void setLeft(Value left){
		this.left = left;
	}

	public Value getRight(){
		return this.right;
	}

	private void setRight(Value right){
		this.right = right;
	}
}