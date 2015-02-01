/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

public class BibTeXComment implements SingleValueBibTeXObject {
    private StringValue value = null;

    public BibTeXComment(StringValue value){
        setValue(value);
    }

    @Override
    public StringValue getValue(){
        return this.value;
    }

    private void setValue(StringValue value){
        this.value = value;
    }

    @Override
    public void accept(final BibTeXObjectVisitor visitor) {
        visitor.visit(this);
    }
}