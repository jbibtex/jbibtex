/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Implements the pattern Visitor for format a BibTex objects 
 * to a defined Writer.
 */
public class BibTeXFormatter implements BibTeXObjectVisitor {
    private final Writer writer;

    /**
     * Defines if the formatter must insert blank spaces
     * before and after the signal of equals "="
     * that separate the field name and its value.
     * Some applications that uses another bibtex parser
     * do not understand the spaces around the signal of equals.
     */
    private boolean insertSpaceBetweenFieldNameAndValue = true;

    /**
     * Defines if the formatter must insert the closing bracket "}"
     * alone in a new line for each BibTeX Entry.
     * Some applications that uses another bibtex parser
     * do not understand the closing bracket alone in a new line.
     */
    private boolean insertBibTeXEntryClosingBracketInNewLine = true;

    /**
     * Defines if the formatter must insert a comma
     * after the last field value for each BibTeXEntry.
     * The comma is placed before the entry closing tag.
     * Some applications that uses another bibtex parser
     * expect a comma after the last field value.
     */
    private boolean insertCommaAfterLastFieldValueOfBibTeXEntry = false;

    /**
     *
     * @param writer
     */
    public BibTeXFormatter(final Writer writer){
        this.writer = writer;
    }

    @Override
    public void visit(final BibTeXEntry entry) {
        BibTeXStringBuilder builder = 
                BibTeXStringBuilder.getBuilderThatOpensAndClosesBrackets(this);
        addEntryHeaderToBuilder(builder, entry);
        Collection<Map.Entry<Key, Value>> fields = entry.getFields().entrySet();
        for(Iterator<Map.Entry<Key, Value>> it = fields.iterator(); it.hasNext(); ){
            builder.addContent(formatCurrentEntryField(it));
        }
        write(builder.toString());
        write(builder.lineSeparator());
        write(builder.lineSeparator());
    }

    private String formatCurrentEntryField(Iterator<Map.Entry<Key, Value>> it) {
        Map.Entry<Key, Value> field = it.next();
        
        BibTeXStringBuilder builder = 
                BibTeXStringBuilder.getBuilderWithoutOpenningClosingBrackets(this)
                .addIndent()
                .addKeyValuePair(field.getKey(), field.getValue(), 2);
        
        if(it.hasNext() || insertCommaAfterLastFieldValueOfBibTeXEntry) {
            builder.addFieldSeparator();
        }
        if(it.hasNext()) {
            builder.addLineSeparator();
        }
        return builder.toString();
    }

    private void addEntryHeaderToBuilder(final BibTeXStringBuilder builder, final BibTeXEntry entry) {
        builder
            .setObjectType("@"+entry.getType().getValue())
            .addContent(entry.getKey().getValue())
            .addFieldSeparator()
            .addLineSeparator();
    }
   
    @Override
    public void visit(final BibTeXComment comment) {
        visit(comment, "@Comment");
    }    
    
    @Override
    public void visit(final BibTeXInclude include) {
        visit(include, "@Include");
    }
    
    private void visit(final SingleValueBibTeXObject object, final String bibTeXObjectType) {
        BibTeXStringBuilder builder = 
                BibTeXStringBuilder.getBuilderWithoutOpenningClosingBrackets(this)
                .setObjectType(bibTeXObjectType)
                .addContent(object.getValue().format())
                .setInsertBibTeXObjectClosingBracketInNewLine(false)
                .addLineSeparator();
        write(builder.toString());
    } 
    
    @Override
    public void visit(final BibTeXPreamble preamble) {
        BibTeXStringBuilder builder = 
                BibTeXStringBuilder.getBuilderThatOpensAndClosesBrackets(this)
                .setObjectType("@Preamble")
                .addContent(preamble.getValue().format())
                .setInsertBibTeXObjectClosingBracketInNewLine(false);
        write(builder.toString());
        write(builder.lineSeparator());
    }
        
    @Override
    public void visit(final BibTeXString string) {
        BibTeXStringBuilder builder = 
                BibTeXStringBuilder.getBuilderThatOpensAndClosesBrackets(this)
                .setObjectType("@String")
                .addKeyValuePair(string.getKey(), string.getValue().format())
                .setInsertBibTeXObjectClosingBracketInNewLine(false);
        write(builder.toString());
        write(builder.lineSeparator());
    }   
    
    private void write(String content){
        try {
            writer.write(content);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * @return the insertSpaceBetweenFieldNameAndValue
     */
    public boolean isInsertSpaceBetweenFieldNameAndValue() {
        return insertSpaceBetweenFieldNameAndValue;
    }

    /**
     * @param insertSpaceBetweenFieldNameAndValue the insertSpaceBetweenFieldNameAndValue to set
     * @return 
     */
    public BibTeXFormatter setInsertSpaceBetweenFieldNameAndValue(final boolean insertSpaceBetweenFieldNameAndValue) {
        this.insertSpaceBetweenFieldNameAndValue = insertSpaceBetweenFieldNameAndValue;
        return this;
    }

    /**
     * @return the insertBibTeXEntryClosingBracketInNewLine
     */
    public boolean isInsertBibTeXEntryClosingBracketInNewLine() {
        return insertBibTeXEntryClosingBracketInNewLine;
    }

    /**
     * @param insertBibTeXEntryClosingBracketInNewLine the insertBibTeXEntryClosingBracketInNewLine to set
     * @return 
     */
    public BibTeXFormatter setInsertBibTeXEntryClosingBracketInNewLine(final boolean insertBibTeXEntryClosingBracketInNewLine) {
        this.insertBibTeXEntryClosingBracketInNewLine = insertBibTeXEntryClosingBracketInNewLine;
        return this;
    }

    /**
     * @return the insertCommaAfterLastFieldValueOfBibTeXEntry
     */
    public boolean isInsertCommaAfterLastFieldValueOfBibTeXEntry() {
        return insertCommaAfterLastFieldValueOfBibTeXEntry;
    }

    /**
     * @param insertCommaAfterLastFieldValueOfBibTeXEntry the insertCommaAfterLastFieldValueOfBibTeXEntry to set
     * @return 
     */
    public BibTeXFormatter setInsertCommaAfterLastFieldValueOfBibTeXEntry(final boolean insertCommaAfterLastFieldValueOfBibTeXEntry) {
        this.insertCommaAfterLastFieldValueOfBibTeXEntry = insertCommaAfterLastFieldValueOfBibTeXEntry;
        return this;
    }


}