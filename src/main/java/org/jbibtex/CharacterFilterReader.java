/*
 * Copyright (c) 2014 University of Tartu
 */
package org.jbibtex;

import java.io.*;

public class CharacterFilterReader extends FilterReader {

	public CharacterFilterReader(Reader reader){
		super(reader);
	}

	/**
	 * @return <code>true</code> If the specified character should be accepted, <code>false</code> otherwise.
	 *
	 * @see BibTeXParserTokenManager
	 * @see LaTeXParserTokenManager
	 */
	protected boolean accept(char c){

		// Whitespace
		if(c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' '){
			return true;
		}

		// ASCII letters, numbers and control characters
		if(c >= '\u0021' && c <= '\u007f'){
			return true;
		}

		// Latin-1 supplement letters
		if((c >= '\u00c0' && c <= '\u00d6') || (c >= '\u00d8' && c <= '\u00f6') || (c >= '\u00f8' && c <= '\u00ff')){
			return true;
		}

		// Unicode letters
		if((c >= '\u0100' && c <= '\u1fff') || (c == '\u20ac' || c == '\u2122')){
			return true;
		}

		return false;
	}

	@Override
	public int read() throws IOException {

		while(true){
			int result = super.read();
			if(result < 0){
				return -1;
			}

			if(accept((char)result)){
				return result;
			}
		}
	}

	@Override
	public int read(char[] buffer, final int offset, final int length) throws IOException {
		int count = super.read(buffer, offset, length);
		if(count < 0){
			return -1;
		}

		int readOffset = offset;
		int writeOffset = offset;

		for(int i = 0; i < count; i++){
			char c = buffer[readOffset];

			readOffset++;

			if(accept(c)){
				buffer[writeOffset] = c;

				writeOffset++;
			}
		}

		return count - (readOffset - writeOffset);
	}
}