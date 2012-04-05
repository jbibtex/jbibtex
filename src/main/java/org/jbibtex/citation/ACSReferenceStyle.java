/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex.citation;

import java.util.*;

import org.jbibtex.*;

public class ACSReferenceStyle extends ReferenceStyle {

	public ACSReferenceStyle(){
		addFormat(BibTeXEntry.TYPE_ARTICLE, createArticleFormat());
		addFormat(BibTeXEntry.TYPE_BOOK, createBookFormat());
		addFormat(BibTeXEntry.TYPE_INCOLLECTION, createInCollectionFormat());
		addFormat(BibTeXEntry.TYPE_INPROCEEDINGS, createInProceedingsFormat());
	}

	static
	private EntryFormat createArticleFormat(){
		List<FieldFormat> fields = Arrays.asList(
			new AuthorFormat(null),
			new FieldFormat(BibTeXEntry.KEY_TITLE, "."),
			new FieldFormat(BibTeXEntry.KEY_JOURNAL, null),
			new FieldFormat(BibTeXEntry.KEY_YEAR, ","),
			new FieldFormat(BibTeXEntry.KEY_VOLUME, ","),
			new FieldFormat(BibTeXEntry.KEY_NUMBER, ","),
			new FieldFormat(BibTeXEntry.KEY_PAGES, ".")
		);

		return new EntryFormat(fields);
	}

	static
	private EntryFormat createBookFormat(){
		List<FieldFormat> fields = Arrays.asList(
			new AuthorFormat(null),
			new FieldFormat(BibTeXEntry.KEY_TITLE, ";"),
			new EditorFormat(";"),
			new FieldFormat(BibTeXEntry.KEY_PUBLISHER, ":"),
			new FieldFormat(BibTeXEntry.KEY_ADDRESS, ";"),
			new FieldFormat(BibTeXEntry.KEY_YEAR, ".")
		);

		return new EntryFormat(fields);
	}

	static
	private EntryFormat createInCollectionFormat(){
		List<FieldFormat> fields = Arrays.asList(
			new AuthorFormat(null),
			new FieldFormat(BibTeXEntry.KEY_TITLE, "."),
			new BookTitleFormat(";"),
			new EditorFormat(";"),
			new FieldFormat(BibTeXEntry.KEY_PUBLISHER, ";"),
			new FieldFormat(BibTeXEntry.KEY_YEAR, ".")
		);

		return new EntryFormat(fields);
	}

	static
	private EntryFormat createInProceedingsFormat(){
		List<FieldFormat> fields = Arrays.asList(
			new AuthorFormat(null),
			new FieldFormat(BibTeXEntry.KEY_TITLE, "."),
			new BookTitleFormat(";"),
			new EditorFormat(";"),
			new FieldFormat(BibTeXEntry.KEY_ORGANIZATION, ";"),
			new FieldFormat(BibTeXEntry.KEY_YEAR, ".")
		);

		return new EntryFormat(fields);
	}

	static
	private class AuthorFormat extends FieldFormat {

		public AuthorFormat(String separator){
			super(BibTeXEntry.KEY_AUTHOR, separator);
		}

		@Override
		public String format(Value value){
			String string = super.format(value);

			string = string.replace(" and ", "; ");

			return string;
		}
	}

	static
	private class BookTitleFormat extends FieldFormat {

		public BookTitleFormat(String separator){
			super(BibTeXEntry.KEY_BOOKTITLE, separator);
		}

		@Override
		public String format(Value value){
			String string = super.format(value);

			string = ("In " + string);

			return string;
		}
	}

	static
	private class EditorFormat extends FieldFormat {

		public EditorFormat(String separator){
			super(BibTeXEntry.KEY_EDITOR, separator);
		}

		@Override
		public String format(Value value){
			String string = super.format(value);

			string = string.replace(" and ", "; ");

			boolean plural = string.contains("; ");

			string = (string + ", " + (plural ? "Eds." : "Ed."));

			return string;
		}
	}
}