package org.jbibtex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class BibTeXJSONTest {

    private BibTeXEntry reference;
    private String expectedString = "{" +
                                        "\"type\":" +
                                            "{\"value\":\"article\"}," +
                                        "\"key\":" +
                                            "{\"value\":\"test-reference\"}," +
                                        "\"fields\":" +
                                            "{\"title\":" +
                                                "{\"type\":\"org.jbibtex.StringValue\"," +
                                                "\"string\":\"Reference to test JSON.\"," +
                                                "\"style\":\"BRACED\"}," +
                                            "\"year\":" +
                                                "{\"type\":\"org.jbibtex.DigitStringValue\"," +
                                                "\"string\":\"2017\"}}," +
                                        "\"crossReference\":null" +
                                    "}";

    @Before
    public void initialize() {
        this.reference = new BibTeXEntry(BibTeXEntry.TYPE_ARTICLE, new Key("test-reference"));
        this.reference.addField(BibTeXEntry.KEY_TITLE, new StringValue("Reference to test JSON.", StringValue.Style.BRACED));
        this.reference.addField(BibTeXEntry.KEY_YEAR, new DigitStringValue("2017"));
    }

    @Test
    public void serializeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(this.reference);
            assertEquals(jsonString, this.expectedString);

        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void deserializeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BibTeXEntry deserializedReference = objectMapper.readValue(this.expectedString, BibTeXEntry.class);

            // check if references are equal
            assertEquals(deserializedReference.getType(), this.reference.getType());
            assertEquals(deserializedReference.getKey(), this.reference.getKey());
            assertNull(deserializedReference.getCrossReference());
            assertEquals(deserializedReference.getFields().size(), this.reference.getFields().size());
            assertEquals(deserializedReference.getField(BibTeXEntry.KEY_TITLE).toUserString(), this.reference.getField(BibTeXEntry.KEY_TITLE).toUserString());

        } catch (IOException e) {
            fail();
        }
    }
}
