package org.jbibtex;

import java.io.Writer;

/**
 * Implements the Builder pattern for build 
 * different BibTeX objects formatters.
 * @author <a href="http://manoelcampos.com">Manoel Campos</a>
 */
public class BibTeXFormatterBuilder {
    
    public BibTeXFormatterBuilder(){
    }
    
    public BibTeXObjectVisitor buildStandardFormatter(final Writer writer){
        return new BibTeXFormatter(writer);
    } 

    public BibTeXObjectVisitor buildConciseFormatter(final Writer writer){
        BibTeXFormatter visitor = new BibTeXFormatter(writer);
        visitor.setInsertBibTeXEntryClosingBracketInNewLine(false)
               .setInsertCommaAfterLastFieldValueOfBibTeXEntry(true)
               .setInsertSpaceBetweenFieldNameAndValue(false);
        return visitor;
    }
    
}
