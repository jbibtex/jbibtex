/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class BibTeXPreamble implements SingleValueBibTeXObject {
    private Value value = null;

    public BibTeXPreamble(Value value){
        setValue(value);
    }

    @Override
    public Value getValue(){
        return this.value;
    }

    private void setValue(Value value){
        this.value = value;
    }

    @Override
    public void accept(final BibTeXObjectVisitor visitor) {
        visitor.visit(this);
    }
}