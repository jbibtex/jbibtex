/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.util.*;

public class KeyMap <V> extends LinkedHashMap<Key, V> {

	/**
	 * @return <code>true</code> If the {@link #keySet() key set} of the map was modified, <code>false</code> otherwise.
	 *
	 * @see #removeIfPresent(Key)
	 */
	boolean putIfMissing(Key key, V value){

		if(containsKey(key)){
			return false;
		}

		put(key, value);

		return true;
	}

	/**
	 * @see #putIfMissing(Key, V)
	 */
	boolean removeIfPresent(Key key){

		if(containsKey(key)){
			remove(key);

			return true;
		}

		return false;
	}
}