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
			new JournalFormat(null),
			new FieldFormat(BibTeXEntry.KEY_YEAR, ","),
			new VolumeFormat(","),
			new FieldFormat(BibTeXEntry.KEY_NUMBER, ","),
			new FieldFormat(BibTeXEntry.KEY_PAGES, ".")
		);

		return new EntryFormat(fields);
	}

	static
	private EntryFormat createBookFormat(){
		List<FieldFormat> fields = Arrays.asList(
			new AuthorFormat(null),
			new BookTitleFormat(";"),
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
			new InBookTitleFormat(";"),
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
			new InBookTitleFormat(";"),
			new EditorFormat(";"),
			new FieldFormat(BibTeXEntry.KEY_ORGANIZATION, ";"),
			new FieldFormat(BibTeXEntry.KEY_YEAR, ".")
		);

		return new EntryFormat(fields);
	}

	static
	private String bold(String string, boolean html){

		if(html){
			string = ("<b>" + string + "</b>");
		}

		return string;
	}

	static
	private String italic(String string, boolean html){

		if(html){
			string = ("<i>" + string + "</i>");
		}

		return string;
	}

	static
	private class AuthorFormat extends FieldFormat {

		public AuthorFormat(String separator){
			super(BibTeXEntry.KEY_AUTHOR, separator);
		}

		@Override
		public String format(Value value, boolean html){
			String string = super.format(value, html);

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
		public String format(Value value, boolean html){
			String string = super.format(value, html);

			return italic(string, html);
		}
	}

	static
	private class EditorFormat extends FieldFormat {

		public EditorFormat(String separator){
			super(BibTeXEntry.KEY_EDITOR, separator);
		}

		@Override
		public String format(Value value, boolean html){
			String string = super.format(value, html);

			string = string.replace(" and ", "; ");

			boolean plural = string.contains("; ");

			string = (string + ", " + (plural ? "Eds." : "Ed."));

			return string;
		}
	}

	static
	private class InBookTitleFormat extends BookTitleFormat {

		public InBookTitleFormat(String separator){
			super(separator);
		}

		@Override
		public String format(Value value, boolean html){
			String string = super.format(value, html);

			string = ("In " + string);

			return string;
		}
	}

	static
	private class JournalFormat extends FieldFormat {

		public JournalFormat(String separator){
			super(BibTeXEntry.KEY_JOURNAL, separator);
		}

		@Override
		public String format(Value value, boolean html){
			String string = super.format(value, html);

			return italic(string, html);
		}
	}

	static
	private class VolumeFormat extends FieldFormat {

		public VolumeFormat(String separator){
			super(BibTeXEntry.KEY_VOLUME, separator);
		}

		@Override
		public String format(Value value, boolean html){
			String string = super.format(value, html);

			return bold(string, html);
		}
	}
}