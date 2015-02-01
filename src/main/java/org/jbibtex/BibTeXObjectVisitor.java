package org.jbibtex;

/**
 * Defines the interface for the Visitor pattern
 * to be used to implement BibTeX formatters.
 * Each implementation of this interface can 
 * format the BibTeX in a different way for
 * different outputs.
 * @author <a href="http://manoelcampos.com">Manoel Campos</a>
*/
public interface BibTeXObjectVisitor {
    void visit(final BibTeXComment comment);
    void visit(final BibTeXEntry entry);
    void visit(final BibTeXInclude include);
    void visit(final BibTeXPreamble preamble);
    void visit(final BibTeXString string);
}
