JBibTeX [![Build Status](https://github.com/jbibtex/jbibtex/workflows/maven/badge.svg)](https://github.com/jbibtex/jbibtex/actions?query=workflow%3A%22maven%22)
=======

Java BibTeX and LaTeX parser and formatter library

# Installation #

The current version of JBibTeX is **1.0.20** (16 March, 2022).

The library JAR file (together with source and javadoc JAR files) is distributed via Maven Central repository:
```xml
<dependency>
	<groupId>org.jbibtex</groupId>
	<artifactId>jbibtex</artifactId>
	<version>1.0.20</version>
</dependency>
```

# Usage #

### Parsing BibTeX ###

Typical scenario:
```java
Reader reader = ...

org.jbibtex.BibTeXParser bibtexParser = new org.jbibtex.BibTeXParser();

org.jbibtex.BibTeXDatabase database = bibtexParser.parse(reader);
```

BibTeX parser provides two parsing modes:
* `#parse(Reader)`. Normal mode. The parser stops when an error condition is detected.
* `#parseFully(Reader)`. Error recovery mode. The parser skips an erroneous object definition and continues with the next object definition. The list of error conditions can be accessed via `#getExceptions()`.

BibTeX parser performs automatic string constants and crossref fields resolution. The default behavior is to prohibit unresolved references by throwing an unchecked exception `org.jbibtex.ObjectResolutionException`. The default behavior can be overriden as follows:
```java
org.jbibtex.BibTeXParser bibtexParser = new org.jbibtex.BibTeXParser(){

	@Override
	public void checkStringResolution(org.jbibtex.Key key, org.jbibtex.BibTeXString string){

		if(string == null){
			System.err.println("Unresolved string: \"" + key.getValue() + "\"");
		}
	}

	@Override
	public void checkCrossReferenceResolution(org.jbibtex.Key key, org.jbibtex.BibTeXEntry entry){

		if(entry == null){
			System.err.println("Unresolved cross-reference: \"" + key.getValue() + "\"");
		}
	}
};
```

**Caution**: Prior to JBibTeX version 1.0.12, methods `org.jbibtex.BibTeXParser#parse(java.io.Reader)` and `org.jbibtex.LaTeXParser#parse(java.io.Reader)` may throw error `org.jbibtex.TokenMgrError` if the input contains illegal characters or is otherwise problematic. Library users are advised to surround the affected portions of their code with appropriate try-catch statements. An unhandled `org.jbibtex.TokenMgrError` could terminate the JVM process.

Library users may use class `org.jbibtex.CharacterFilterReader` to skip illegal characters in the input:
```java
Reader reader = ...

org.jbibtex.CharacterFilterReader filterReader = new org.jbibtex.CharacterFilterReader(reader);

bibtexParser.parse(filterReader);
``` 

### Formatting BibTeX ###

Typical scenario:
```java
Writer writer = ...

org.jbibtex.BibTeXDatabase database = ...

org.jbibtex.BibTeXFormatter bibtexFormatter = new org.jbibtex.BibTeXFormatter();

bibtexFormatter.format(database, writer);
```

### Working with BibTeX databases ###

Iterating over all the BibTeX entries in the BibTeX database and retrieving their title field:
```java
org.jbibtex.BibTeXDatabase database = ...

Map<org.jbibtex.Key, org.jbibtex.BibTeXEntry> entryMap = database.getEntries();

Collection<org.jbibtex.BibTeXEntry> entries = entryMap.values();
for(org.jbibtex.BibTeXEntry entry : entries){
	org.jbibtex.Value value = entry.getField(org.jbibtex.BibTeXEntry.KEY_TITLE);
	if(value == null){
		continue;
	}

	// Do something with the title value
}
```

BibTeX entry values could be in LaTeX data format. The easiest way to distinguish between plain text and LaTeX text values is to look for LaTeX special symbols `\` and `{`:
```java
org.jbibtex.Value value = ...

String string = value.toUserString();
if(string.indexOf('\\') > -1 || string.indexOf('{') > -1){
	// LaTeX string that needs to be translated to plain text string
} else {
	// Plain text string
}
```

### Translating LaTeX strings to plain text strings ###

Typical scenario:
```java
String latexString = ...

org.jbibtex.LaTeXParser latexParser = new org.jbibtex.LaTeXParser();

List<org.jbibtex.LaTeXObject> latexObjects = latexParser.parse(latexString);

org.jbibtex.LaTeXPrinter latexPrinter = new org.jbibtex.LaTeXPrinter();

String plainTextString = latexPrinter.print(latexObjects);
```

# License #

JBibTeX is licensed under the [BSD 3-Clause License](http://opensource.org/licenses/BSD-3-Clause).
