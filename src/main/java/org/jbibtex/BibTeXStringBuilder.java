package org.jbibtex;

/**
 *
 * @author <a href="http://manoelcampos.com">Manoel Campos</a>
 */
public class BibTeXStringBuilder {
    public static final String FIELD_SEPARATOR = ",";    
    public static final String OPENNING_BRACKET = "{";
    public static final String CLOSING_BRACKET = "}";
    
    private String indent = "\t";
    private boolean insertBibTeXObjectClosingBracketInNewLine;
    private boolean openBracket = true;
    private boolean closeBracket = true;
    private String objectType = "";
    private final StringBuilder content;
    private BibTeXFormatter formatter;
    
    private BibTeXStringBuilder(){
        content = new StringBuilder();
    }
    
    public static BibTeXStringBuilder getBuilderThatOpensAndClosesBrackets(BibTeXFormatter formatter){
        return new BibTeXStringBuilder()
                .setOpenBracket(true)
                .setCloseBracket(true)
                .setFormatter(formatter)
                .setInsertBibTeXObjectClosingBracketInNewLine(formatter.isInsertBibTeXEntryClosingBracketInNewLine());
    }
    
    public static BibTeXStringBuilder getBuilderWithOpenningBracket(BibTeXFormatter formatter){
        return new BibTeXStringBuilder()
                .setOpenBracket(true)
                .setCloseBracket(false)
                .setFormatter(formatter)
                .setInsertBibTeXObjectClosingBracketInNewLine(formatter.isInsertBibTeXEntryClosingBracketInNewLine());
    }
    
    public static BibTeXStringBuilder getBuilderWithoutOpenningClosingBrackets(BibTeXFormatter formatter){
        return new BibTeXStringBuilder()
                .setOpenBracket(false)
                .setCloseBracket(false)
                .setFormatter(formatter);
    }
    
    public BibTeXStringBuilder addIndent(){
        return addContent(indent);
    }    
    
    public BibTeXStringBuilder addLineSeparator(){
        return addContent(lineSeparator());
    }    

    public String lineSeparator(){
        return "\n";
    }
    
    public BibTeXStringBuilder addKeyValuePair(Key key, String value){
        final String str = String.format("%s%s%s",
                key.getValue(), 
                getFieldNameAndValueSeparator(), 
                value);
        return addContent(str);
    }    

    public BibTeXStringBuilder addKeyValuePair(Key key, Value value, int indentLevel){
        final String str = indentString(value.format(), indentLevel);
        return addKeyValuePair(key, str);
    }    
    
    public BibTeXStringBuilder addKeyValueObject(KeyValueBibTeXObject object){
        return addKeyValuePair(object.getKey(), object.getValue().format());
    }    

    public BibTeXStringBuilder addFieldSeparator(){
        return addContent(FIELD_SEPARATOR);
    }    
    
    public BibTeXStringBuilder addContent(final String value){
        content.append(value);
        return this;
    }

    public BibTeXStringBuilder addIndentedContent(final String value, final int level){
        return addContent(indentString(value, level));
    }
    
    private String indentString(final String value, final int level){
        return StringUtil.addIndent(value, level, indent);
    }
    
    public BibTeXStringBuilder setOpenBracket(final boolean openBracket){
        this.openBracket = openBracket;
        return this;
    }
    
    public BibTeXStringBuilder setCloseBracket(final boolean closeBracket){
        this.closeBracket = closeBracket;
        return this;
    }
    
    @Override
    public String toString(){
        StringBuilder contentBuilt = new StringBuilder(content.length());
        contentBuilt.append(getObjectType());
        if(isOpenBracket())
            contentBuilt.append("{");
        contentBuilt.append(content);
        if(isCloseBracket())
            contentBuilt.append(getBibTeXEntryClosingBracket());
        return contentBuilt.toString();
    }
    
    /**
     * @return the objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectType the objectType to set
     * @return 
     */
    public BibTeXStringBuilder setObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    /**
     * @return the openBracket
     */
    public boolean isOpenBracket() {
        return openBracket;
    }

    /**
     * @return the closeBracket
     */
    public boolean isCloseBracket() {
        return closeBracket;
    }

    /**
     * @return the indent
     */
    public String getIndent() {
        return indent;
    }

    /**
     * @param indent the indent to set
     */
    public void setIndent(String indent) {
        this.indent = indent;
    }

    /**
     * @return the formatter
     */
    public BibTeXFormatter getFormatter() {
        return formatter;
    }

    /**
     * @param formatter the formatter to set
     * @return 
     */
    public BibTeXStringBuilder setFormatter(BibTeXFormatter formatter) {
        this.formatter = formatter;
        return this;
    }
    
    public String getFieldNameAndValueSeparator(){
        final String separator = "=";
        return (formatter.isInsertSpaceBetweenFieldNameAndValue() ? 
                String.format(" %s ", separator) : 
                separator);
    }

    public String getBibTeXEntryClosingBracket(){
        return (insertBibTeXObjectClosingBracketInNewLine ? 
                String.format("%s%s", lineSeparator(), CLOSING_BRACKET) : 
                CLOSING_BRACKET);
    }

    /**
     * @return the insertBibTeXObjectClosingBracketInNewLine
     */
    public boolean isInsertBibTeXObjectClosingBracketInNewLine() {
        return insertBibTeXObjectClosingBracketInNewLine;
    }

    /**
     * @param insertBibTeXObjectClosingBracketInNewLine the insertBibTeXObjectClosingBracketInNewLine to set
     * @return 
     */
    public BibTeXStringBuilder setInsertBibTeXObjectClosingBracketInNewLine(boolean insertBibTeXObjectClosingBracketInNewLine) {
        this.insertBibTeXObjectClosingBracketInNewLine = insertBibTeXObjectClosingBracketInNewLine;
        return this;
    }


    
}
