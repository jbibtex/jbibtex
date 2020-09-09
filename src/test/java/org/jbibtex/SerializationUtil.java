/*
 * Copyright (c) 2018 Villu Ruusmann
 */
package org.jbibtex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationUtil {

	private SerializationUtil(){
	}

	static
	public <E extends Serializable> E clone(E object) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ByteArrayInputStream is;

		ObjectOutputStream oos = new ObjectOutputStream(os);

		try {
			oos.writeObject(object);

			oos.flush();

			is = new ByteArrayInputStream(os.toByteArray());
		} finally {
			oos.close();
		}

		ObjectInputStream ois = new ObjectInputStream(is);

		try {
			return (E)ois.readObject();
		} catch(ClassNotFoundException cnfe){
			throw new RuntimeException(cnfe);
		} finally {
			ois.close();
		}
	}

	static
	public <E extends Serializable> E jsonClone(E object) throws IOException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		String string = objectMapper.writeValueAsString(object);

		return (E)objectMapper.readValue(string, object.getClass());
	}
}