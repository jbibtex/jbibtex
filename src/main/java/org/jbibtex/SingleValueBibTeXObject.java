package org.jbibtex;

/**
 *
 * @author <a href="http://manoelcampos.com">Manoel Campos</a>
 */
public interface SingleValueBibTeXObject extends BibTeXObject {
    @Override
    public abstract void accept(BibTeXObjectVisitor visitor);
    public abstract Value getValue();
}
