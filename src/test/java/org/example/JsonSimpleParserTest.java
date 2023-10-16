package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonSimpleParserTest {

    @Test
    void parse() {
        List<String> expextedList = new ArrayList<>();
        expextedList.add("4 + 3 * 4");
        expextedList.add("3 + 2 - 7");
        expextedList.add("4 * 5 - 6 / 3");

        JsonSimpleParser jsp = new JsonSimpleParser();
        List<String> actualList = jsp.parse("json_input.json");

        assertEquals(expextedList, actualList);
    }
}