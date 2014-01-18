package org.jbibtex;

import org.junit.*;

import static org.junit.Assert.*;

public class KeyMapTest {

	@Test
	public void putAndRemove(){
		KeyMap<Object> map = new KeyMap<Object>();

		assertTrue(map.putIfMissing(new Key("Test"), null));
		assertFalse(map.putIfMissing(new Key("test"), null));

		assertTrue(map.removeIfPresent(new Key("test")));
		assertFalse(map.removeIfPresent(new Key("Test")));
	}
}