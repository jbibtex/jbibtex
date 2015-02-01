/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

/**
 * Defines a Visitable interface for the Visitor pattern
 * of BibTeX objects.
  */
public interface BibTeXObject {
    /**
     *
     * @param visitor
     */
    void accept(final BibTeXObjectVisitor visitor);
}