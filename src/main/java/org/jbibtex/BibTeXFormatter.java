/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BibTeXFormatter {

	private String indent = "\t";
        
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

	public BibTeXFormatter(){
	}

        /**
         * Construct and configure the formatter.
         * 
         * @param insertSpaceBetweenFieldNameAndValue 
         * @param insertBibTeXEntryClosingBracketInNewLine 
         * @param insertCommaAfterLastFieldValueOfBibTeXEntry 
         * @see BibTeXFormatter#insertSpaceBetweenFieldNameAndValue
         * @see BibTeXFormatter#insertBibTeXEntryClosingBracketInNewLine
         * @see BibTeXFormatter#insertCommaAfterLastFieldValueOfBibTeXEntry
         */
	public BibTeXFormatter(boolean insertSpaceBetweenFieldNameAndValue, 
                boolean insertBibTeXEntryClosingBracketInNewLine,
                boolean insertCommaAfterLastFieldValueOfBibTeXEntry){
            this();
            this.insertSpaceBetweenFieldNameAndValue = insertSpaceBetweenFieldNameAndValue;
            this.insertBibTeXEntryClosingBracketInNewLine = insertBibTeXEntryClosingBracketInNewLine;
            this.insertCommaAfterLastFieldValueOfBibTeXEntry = insertCommaAfterLastFieldValueOfBibTeXEntry;
	}

        public void format(BibTeXDatabase database, Writer writer) throws IOException {
		List<BibTeXObject> objects = database.getObjects();

		String separator = "";

		for(BibTeXObject object : objects){
			writer.write(separator);

			if(object instanceof BibTeXComment){
                            format((BibTeXComment)object, writer);
			} else if(object instanceof BibTeXEntry){
                            format((BibTeXEntry)object, writer);
			} else if(object instanceof BibTeXInclude){
                            format((BibTeXInclude)object, writer);
			} else if(object instanceof BibTeXPreamble){
                            format((BibTeXPreamble)object, writer);
			} else if(object instanceof BibTeXString){
                            format((BibTeXString)object, writer);
			} else {
                            throw new IllegalArgumentException();
			}

			separator = "\n\n";
		}

		writer.flush();
	}

	protected void format(BibTeXComment comment, Writer writer) throws IOException {
		writer.write("@Comment");

		format(comment.getValue(), 1, writer);
	}
        
        private String getFieldNameAndValueSeparator(){
            final String separator = "=";
            return (insertSpaceBetweenFieldNameAndValue ? 
                    String.format(" %s ", separator) : 
                    separator);
        }


        private String getBibTeXEntryClosingBracket(){
            final String closing = "}";
            return (insertBibTeXEntryClosingBracketInNewLine ? 
                    String.format("\n%s", closing) : 
                    closing);
        }

        protected void format(BibTeXEntry entry, Writer writer) throws IOException {
		writer.write("@");
		format(entry.getType(), writer);

		writer.write('{');
		format(entry.getKey(), writer);
		writer.write(',');
		writer.write('\n');

		Collection<Map.Entry<Key, Value>> fields = (entry.getFields()).entrySet();
		for(Iterator<Map.Entry<Key, Value>> it = fields.iterator(); it.hasNext(); ){
			Map.Entry<Key, Value> field = it.next();

			writer.write(getIndent());

			format(field.getKey(), writer);
			writer.write(getFieldNameAndValueSeparator());
			format(field.getValue(), 2, writer);

			if(it.hasNext()){
                            writer.write(',');
                            writer.write('\n');
			}
		}
                if(insertCommaAfterLastFieldValueOfBibTeXEntry) {
                    writer.write(',');
                }
		writer.write(getBibTeXEntryClosingBracket());
	}

	protected void format(BibTeXInclude include, Writer writer) throws IOException {
		writer.write("@Include");

		format(include.getValue(), 1, writer);
	}

	protected void format(BibTeXPreamble preamble, Writer writer) throws IOException {
		writer.write("@Preamble");

		writer.write('{');
		format(preamble.getValue(), 1, writer);
		writer.write('}');
	}

	protected void format(BibTeXString string, Writer writer) throws IOException {
		writer.write("@String");

		writer.write('{');
		format(string.getKey(), writer);
		writer.write(" = ");
		format(string.getValue(), 1, writer);
		writer.write('}');
	}

	protected void format(Key key, Writer writer) throws IOException {
		String string = key.getValue();

		writer.write(string);
	}

	protected void format(Value value, int level, Writer writer) throws IOException {
		String string = StringUtil.addIndent(value.format(), level, getIndent());

		writer.write(string);
	}

	public String getIndent(){
		return this.indent;
	}

	public void setIndent(String indent){
		this.indent = indent;
	}

    /**
     * @return the insertSpaceBetweenFieldNameAndValue
     */
    public boolean isInsertSpaceBetweenFieldNameAndValue() {
        return insertSpaceBetweenFieldNameAndValue;
    }

    /**
     * @param insertSpaceBetweenFieldNameAndValue the insertSpaceBetweenFieldNameAndValue to set
     */
    public void setInsertSpaceBetweenFieldNameAndValue(boolean insertSpaceBetweenFieldNameAndValue) {
        this.insertSpaceBetweenFieldNameAndValue = insertSpaceBetweenFieldNameAndValue;
    }

    /**
     * @return the insertBibTeXEntryClosingBracketInNewLine
     */
    public boolean isInsertBibTeXEntryClosingBracketInNewLine() {
        return insertBibTeXEntryClosingBracketInNewLine;
    }

    /**
     * @param insertBibTeXEntryClosingBracketInNewLine the insertBibTeXEntryClosingBracketInNewLine to set
     */
    public void setInsertBibTeXEntryClosingBracketInNewLine(boolean insertBibTeXEntryClosingBracketInNewLine) {
        this.insertBibTeXEntryClosingBracketInNewLine = insertBibTeXEntryClosingBracketInNewLine;
    }

    /**
     * @return the insertCommaAfterLastFieldValueOfBibTeXEntry
     */
    public boolean isInsertCommaAfterLastFieldValueOfBibTeXEntry() {
        return insertCommaAfterLastFieldValueOfBibTeXEntry;
    }

    /**
     * @param insertCommaAfterLastFieldValueOfBibTeXEntry the insertCommaAfterLastFieldValueOfBibTeXEntry to set
     */
    public void setInsertCommaAfterLastFieldValueOfBibTeXEntry(boolean insertCommaAfterLastFieldValueOfBibTeXEntry) {
        this.insertCommaAfterLastFieldValueOfBibTeXEntry = insertCommaAfterLastFieldValueOfBibTeXEntry;
    }
}